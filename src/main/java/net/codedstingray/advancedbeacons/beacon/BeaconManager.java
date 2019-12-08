package net.codedstingray.advancedbeacons.beacon;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BeaconManager {

    public static boolean createBeacon(Player player, Location location) {
        player.sendMessage(ChatColor.AQUA + "Advanced Beacon activated");
        return true;
    }
}
