package net.codedstingray.advancedbeacons.event.listener;

import net.codedstingray.advancedbeacons.Data;
import net.codedstingray.advancedbeacons.beacon.BeaconManager;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();

        if(item != null && action == Action.RIGHT_CLICK_BLOCK && item.isSimilar(Data.getBeaconActivator())) {
            boolean success = BeaconManager.createBeacon(player, event.getClickedBlock().getLocation());
            if(success) {
                item.setAmount(item.getAmount() - 1);
            }
        }
    }
}
