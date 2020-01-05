/*
 * AdvancedBeacons, a Minecraft plugin for a better beacon experience
 * Copyright (C) CodedStingray <http://codedstingray.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.codedstingray.advancedbeacons.beacon;

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

    /**
     * Determines if the given position is in the AABB region of the given advanced beacon.
     * The AABB region is defined as ([-4,4], [-1,-4], [-4,4])
     * @param beacon The beacon to do the AABB check on
     * @param location The location to do the AABB check on
     * @return true if the location is within the AABB region of the beacon, false otherwise
     */
    static boolean AABB(AdvancedBeacon beacon, Location location) {
        int dx = Math.abs(location.getBlockX() - beacon.getLocation().getBlockX());
        int dy = location.getBlockY() - beacon.getLocation().getBlockY();
        int dz = Math.abs(location.getBlockZ() - beacon.getLocation().getBlockZ());

        return dx <= 4 &&
                dy <= -1 && dy >= -4 &&
                dz <= 4;
    }
}
