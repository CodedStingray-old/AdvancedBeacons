package net.codedstingray.advancedbeacons.beacon;

import net.codedstingray.advancedbeacons.AdvancedBeacons;
import net.codedstingray.advancedbeacons.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class BeaconUtilities {

    public static BeaconStructure validateBeacon(AdvancedBeacon beacon) {
        return validateBeacon(beacon.getLocation());
    }

    public static BeaconStructure validateBeacon(Location location) {
        AdvancedBeacons.getInstance().getLogger().info("Validating beacon at " + location);

        if(!location.getBlock().getType().equals(Material.BEACON)) {
            return BeaconStructure.noBeacon();
        }

        World world = location.getWorld();
        if(world == null)
            return BeaconStructure.invalid();

        //the total power of the beacon
        int totalPower = 0;
        int tier = 0;

        pyramidCheck:
        for(int y = -1; y >= -4; y--) {

            int layerPower = 0;
            for(int x = y; x <= -y; x++) {
                for(int z = y; z <= -y; z++) {
                    Location checkPos = location.clone().add(new Vector(x, y, z));
                    Block block = world.getBlockAt(checkPos);
                    Material type = block.getType();

                    if(y == -1) {
                        //checking top layer
                        if((x == 0 && z != 0) || (x != 0 && z == 0)) {
                            //container positions
                            if(!Data.isContainer(type))
                                return BeaconStructure.invalid();
                        } else {
                            int power = checkPowerBlock(type);
                            if(power > 0) {
                                layerPower += power;
                            } else {
                                return BeaconStructure.invalid();
                            }
                        }
                    } else {
                        //checking other layers
                        int power = checkPowerBlock(type);
                        if(power > 0) {
                            layerPower += power;
                        } else {
                            //the lower levels don't need to be there, so we only cancel the pyramid check
                            break pyramidCheck;
                        }
                    }
                }
            }
            //layer is valid
            //add this layer's power tot he total power level of the beacon
            totalPower += layerPower;
            //increase the beacon's tier
            tier++;
        }

        return BeaconStructure.valid(tier, totalPower);
    }

    private static int checkPowerBlock(Material mat) {
        switch(mat) {
            case IRON_BLOCK:
                return 50;
            case GOLD_BLOCK:
                return 75;
            case EMERALD_BLOCK:
                return 125;
            case DIAMOND_BLOCK:
                return 150;
            default:
                return -1;
        }
    }
}
