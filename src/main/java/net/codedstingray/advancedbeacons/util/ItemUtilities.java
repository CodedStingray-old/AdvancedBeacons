package net.codedstingray.advancedbeacons.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemUtilities {

    public static ItemStack createGuiItem(Material material, String name, EnchantmentData[] enchantments, String... lore) {
        ItemStack item = new ItemStack(material, 1);

        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));

            for(EnchantmentData e: enchantments) {
                meta.addEnchant(e.enchantment, e.level, e.ignoreLevelCap);
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    public static void setLore(ItemStack item, String... lore) {
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
        }
    }

}
