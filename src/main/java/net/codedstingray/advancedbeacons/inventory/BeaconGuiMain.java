package net.codedstingray.advancedbeacons.inventory;

import net.codedstingray.advancedbeacons.Data;
import net.codedstingray.advancedbeacons.beacon.AdvancedBeacon;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BeaconGuiMain extends AbstractBeaconGui {


    public BeaconGuiMain(AdvancedBeacon beacon) {
        super(9 * 6, "Advanced Beacon", beacon);
    }

    @Override
    protected void initializeItems() {
        inv.addItem(Data.getMenuItem("Main_Header"));
    }

    @Override
    public void update() {

    }

    @Override
    protected void handleInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
