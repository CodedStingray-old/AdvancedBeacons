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
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

    //Handling block breaking and placing
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        Player player = event.getPlayer();
        if(advancedBeacons.get(location) != null) {
            //remove the beacon from the list and drop an activator if player is not in creative
            advancedBeacons.remove(location);
            if(player.getGameMode() != GameMode.CREATIVE) {
                location.getWorld().dropItem(location, Data.getBeaconActivator());
            }
        } else {
            checkLocation(location);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
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

    //Handling Beacon opening
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBeaconOpen(PlayerInteractEvent event) {
        if(event.getPlayer().isSneaking() || !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            //We're only interested in the event if it's a right click and the player isn't sneaking
            return;
        }
        Block block = event.getClickedBlock();

        if(block != null && block.getType().equals(Material.BEACON)) {
            Location location = block.getLocation();
            AdvancedBeacon beacon = advancedBeacons.get(location);
            if(beacon != null) {
                event.setCancelled(true);
                //TODO: open advanced beacon ui
            }
        }
    }

    public Collection<AdvancedBeacon> getAdvancedBeacons() {
        return advancedBeacons.values();
    }
}
