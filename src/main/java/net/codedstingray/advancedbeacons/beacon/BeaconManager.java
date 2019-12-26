package net.codedstingray.advancedbeacons.beacon;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BeaconManager {

    public static boolean createBeacon(Player player, Location location) {
        BeaconStructure structure = BeaconUtilities.validateBeacon(location);

        player.sendMessage(structure.toString());

        if(structure.isValid()) {
            player.sendMessage(ChatColor.AQUA + "Advanced beacon created with tier " + structure.getTier() + " and a power of " + structure.getPower());
            return true;
        }

        if(structure.equals(BeaconStructure.invalid())) {
            player.sendMessage(ChatColor.RED + "Invalid advanced beacon structure");
        } else {
            player.sendMessage(ChatColor.YELLOW + "Click on a beacon with a valid advanced beacon structure to create an advanced beacon.");
        }

        return false;
    }
}
