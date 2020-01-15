package net.codedstingray.advancedbeacons.inventory;

import net.codedstingray.advancedbeacons.AdvancedBeacons;
import net.codedstingray.advancedbeacons.beacon.AdvancedBeacon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class AbstractBeaconGui implements InventoryHolder, Listener {

    protected Inventory inv;
    protected AdvancedBeacon beacon;

    public AbstractBeaconGui(int size, String title, AdvancedBeacon beacon) {
        inv = Bukkit.createInventory(this, size, title);
        initializeItems();
        this.beacon = beacon;

        AdvancedBeacons.getInstance().getServer().getPluginManager().registerEvents(this, AdvancedBeacons.getInstance());
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    public AdvancedBeacon getBeacon() {
        return beacon;
    }

    public void openInventory(Player p) {
        p.openInventory(inv);
    }

    //TODO: for optimization
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory() != inv) {
            AdvancedBeacons.getInstance().getLogger().info("Not same inventory");
            return;
        }
        AdvancedBeacons.getInstance().getLogger().info("Same inventory");

        handleInventoryClick(event);
    }

    protected abstract void initializeItems();
    public abstract void update();

    protected abstract void handleInventoryClick(InventoryClickEvent event);
}
