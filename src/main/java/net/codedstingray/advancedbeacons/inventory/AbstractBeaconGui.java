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
import org.bukkit.inventory.ItemStack;

public abstract class AbstractBeaconGui implements InventoryHolder, Listener {

    protected Inventory inventory;
    protected AdvancedBeacon beacon;

    public AbstractBeaconGui(int size, String title, AdvancedBeacon beacon) {
        inventory = Bukkit.createInventory(this, size, title);
        initializeItems();
        this.beacon = beacon;

        AdvancedBeacons.getInstance().getServer().getPluginManager().registerEvents(this, AdvancedBeacons.getInstance());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public AdvancedBeacon getBeacon() {
        return beacon;
    }

    public void openInventory(Player p) {
        p.openInventory(inventory);
    }

    protected void setItem(int row, int column, ItemStack item) {
        int index = 9 * row + column;
        inventory.setItem(index, item);
    }

    //TODO: for optimization I can probably condense this into a sincle listener instead of one for each inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory() != inventory) {
            return;
        }

        handleInventoryClick(event);
    }

    protected abstract void initializeItems();
    public abstract void update();

    protected abstract void handleInventoryClick(InventoryClickEvent event);
}
