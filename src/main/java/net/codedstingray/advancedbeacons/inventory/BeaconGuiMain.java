package net.codedstingray.advancedbeacons.inventory;

import net.codedstingray.advancedbeacons.AdvancedBeacons;
import net.codedstingray.advancedbeacons.Data;
import net.codedstingray.advancedbeacons.beacon.AdvancedBeacon;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.codedstingray.advancedbeacons.util.ItemUtilities.setLore;

public class BeaconGuiMain extends AbstractBeaconGui {

    public BeaconGuiMain(AdvancedBeacon beacon) {
        super(9 * 6, "Advanced Beacon", beacon);
    }

    @Override
    public void initializeItems() {
        setItem(0, 4, Data.getMenuItem("Header_Main"));

        for(int i = 0; i < 6; i++) {
            setItem(i, 3, Data.getMenuItem("Spacer"));
            setItem(i, 5, Data.getMenuItem("Spacer"));

            setItem(i, 0, Data.getMenuItem("Effect_Placeholder"));
            setItem(i, 6, Data.getMenuItem("Effect_Placeholder"));

            setItem(i, 1, Data.getMenuItem("Min_Max_Placeholder"));
            setItem(i, 2, Data.getMenuItem("Min_Max_Placeholder"));
            setItem(i, 7, Data.getMenuItem("Min_Max_Placeholder"));
            setItem(i, 8, Data.getMenuItem("Min_Max_Placeholder"));
        }

        setItem(1, 4, Data.getMenuItem("Iron_Indicator"));
        setItem(2, 4, Data.getMenuItem("Gold_Indicator"));
        setItem(3, 4, Data.getMenuItem("Emerald_Indicator"));
        setItem(4, 4, Data.getMenuItem("Diamond_Indicator"));
        setItem(5, 4, Data.getMenuItem("Nether_Star_Indicator"));
    }

    @Override
    public void update() {
        int fIron = beacon.getFuelIron();
        setLore(getItem(1, 4), (fIron > 0 ? ChatColor.GREEN : ChatColor.RED).toString() + fIron);
        int fGold = beacon.getFuelGold();
        setLore(getItem(2, 4), (fGold > 0 ? ChatColor.GREEN : ChatColor.RED).toString() + fGold);
        int fEmerald = beacon.getFuelEmerald();
        setLore(getItem(3, 4), (fEmerald > 0 ? ChatColor.GREEN : ChatColor.RED).toString() + fEmerald);
        int fDiamond = beacon.getFuelDiamond();
        setLore(getItem(4, 4), (fDiamond > 0 ? ChatColor.GREEN : ChatColor.RED).toString() + fDiamond);
        int fNetherStar = beacon.getFuelNetherStar();
        setLore(getItem(5, 4), (fNetherStar > 0 ? ChatColor.GREEN : ChatColor.RED).toString() + fNetherStar);
    }

    @Override
    protected void handleInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
