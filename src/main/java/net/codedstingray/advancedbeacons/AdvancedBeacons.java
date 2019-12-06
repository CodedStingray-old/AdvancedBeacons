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

package net.codedstingray.advancedbeacons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;

public final class AdvancedBeacons extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        ItemStack beaconActivator = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = beaconActivator.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "Advanced Beacon Activator");
        LinkedList<String> lore = new LinkedList<>();
        lore.add(ChatColor.RESET + "Activates an Advanced Beacon.");
        lore.add(ChatColor.RESET + "Consumed after use");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
        beaconActivator.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "recipe_advanced_beacon_activator");
        ShapedRecipe recipe = new ShapedRecipe(key, beaconActivator);

        recipe.shape(
                "DCE",
                "CNC",
                "ECD"
        );

        recipe.setIngredient('D', Material.DRAGON_EGG);
        recipe.setIngredient('C', Material.END_CRYSTAL);
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('N', Material.NETHER_STAR);

        Bukkit.addRecipe(recipe);

        getLogger().info("Created and added recipe " + recipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
