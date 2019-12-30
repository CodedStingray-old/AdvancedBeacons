package net.codedstingray.advancedbeacons.beacon;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BeaconManager {

    private static HashMap<Location, AdvancedBeacon> advancedBeacons = new HashMap<>();

    public static boolean createBeacon(Player player, Location location) {
        if(advancedBeacons.get(location) != null) {
            BeaconStructure str = advancedBeacons.get(location).getStructure();
            player.sendMessage(ChatColor.AQUA + "There's already an advanced beacon in this location with tier " + str.getTier() + " and a power of " + str.getPower());
            return false;
        }

        //structure validation
        BeaconStructure structure = BeaconUtilities.validateBeacon(location);
        if(structure.equals(BeaconStructure.invalid()) && !player.isSneaking()) {
            player.sendMessage(ChatColor.RED + "Invalid advanced beacon structure. Sneak right-click to force advanced beacon creation.");
            return false;
        }

        if(structure.equals(BeaconStructure.noBeacon())) {
            player.sendMessage(ChatColor.YELLOW + "Click on a beacon with a valid advanced beacon structure to create an advanced beacon.");
            return false;
        }

        //beacon registration
        AdvancedBeacon beacon = new AdvancedBeacon(structure, location);
        advancedBeacons.put(beacon.getLocation(), beacon);

        if (structure.isValid()) {
            player.sendMessage(ChatColor.AQUA + "Advanced beacon created with tier " + structure.getTier() + " and a power of " + structure.getPower());
        } else {
            player.sendMessage(ChatColor.AQUA + "Advanced beacon created with invalid structure");
        }
        return true;
    }
}
