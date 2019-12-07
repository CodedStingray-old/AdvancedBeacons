package net.codedstingray.advancedbeacons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;

public class Data {

    static {
        ItemStack _beaconActivator = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = _beaconActivator.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "Advanced Beacon Activator");
        LinkedList<String> lore = new LinkedList<>();
        lore.add(ChatColor.RESET + "Activates an Advanced Beacon.");
        lore.add(ChatColor.RESET + "Consumed after use");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
        _beaconActivator.setItemMeta(meta);

        beaconActivator = _beaconActivator;
    }

    private static final ItemStack beaconActivator;

    public static ItemStack getBeaconActivator() {
        return beaconActivator.clone();
    }
}
