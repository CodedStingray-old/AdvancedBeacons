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

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Collection;
import java.util.HashMap;

public class BeaconManager implements Listener {

    //TODO: Chunk referenced storing of beacons for optimization
    private HashMap<Location, AdvancedBeacon> advancedBeacons = new HashMap<>();

    public boolean createBeacon(Player player, Location location) {
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

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        checkLocation(location);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Location location = event.getBlock().getLocation();
        checkLocation(location);
    }

    private void checkLocation(Location location) {
        for(AdvancedBeacon beacon: advancedBeacons.values()) {
            if(BeaconUtilities.AABB(beacon, location)) {
                //tell beacon to check the beacon structure on next update call
                beacon.checkStructure();
            }
        }
    }

    public Collection<AdvancedBeacon> getAdvancedBeacons() {
        return advancedBeacons.values();
    }
}
