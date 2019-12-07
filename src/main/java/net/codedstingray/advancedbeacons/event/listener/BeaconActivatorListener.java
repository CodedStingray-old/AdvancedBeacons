package net.codedstingray.advancedbeacons.event.listener;

import net.codedstingray.advancedbeacons.Data;
import net.codedstingray.advancedbeacons.beacon.BeaconManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BeaconActivatorListener implements Listener {

    @EventHandler
    public void onActivatorUsed(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if(item != null && action == Action.RIGHT_CLICK_BLOCK && item.isSimilar(Data.getBeaconActivator())) {
            BeaconManager.createBeacon(event.getPlayer(), event.getClickedBlock().getLocation());
        }
    }
}
