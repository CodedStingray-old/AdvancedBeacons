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
