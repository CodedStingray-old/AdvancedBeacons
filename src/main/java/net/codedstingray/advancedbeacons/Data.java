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

import net.codedstingray.advancedbeacons.util.EnchantmentData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static net.codedstingray.advancedbeacons.util.EnchantmentData.NO_ENCHANTMENT;
import static net.codedstingray.advancedbeacons.util.ItemUtilities.createGuiItem;

public class Data {

    private static final ItemStack beaconActivator;
    private static final HashMap<String, ItemStack> menuItems = new HashMap<>();
    private static final Set<Material> containers;
    private static final Set<Material> powerBlocks;

    static {
        //<editor-fold desc="Items" defaultstate="collapsed">
        //<editor-fold desc="Activator" defaultstate="collapsed">
        beaconActivator = createGuiItem(
                Material.NETHER_STAR,
                ChatColor.LIGHT_PURPLE + "Advanced Beacon Activator",
                new EnchantmentData[] {
                        new EnchantmentData(Enchantment.FIRE_ASPECT, 1, true)
                },
                ChatColor.RESET + "Activates an Advanced Beacon.",
                ChatColor.RESET + "Consumed after use"
        );
        //</editor-fold>
        //<editor-fold desc="Menu Items" defaultstate="collapsed">
        menuItems.put("Header_Main", createGuiItem(
                Material.BEACON,
                ChatColor.AQUA + "Stats",
                NO_ENCHANTMENT,
                ChatColor.RESET + "Status: Unknown"
        ));
        menuItems.put("Spacer", createGuiItem(
                Material.BARRIER,
                ChatColor.RESET + "",
                NO_ENCHANTMENT
        ));
        menuItems.put("Effect_Placeholder", createGuiItem(
                Material.COAL,
                ChatColor.RESET + "No Effect",
                NO_ENCHANTMENT
        ));
        menuItems.put("Min_Max_Placeholder", createGuiItem(
                Material.BRICK,
                ChatColor.RESET + "--",
                NO_ENCHANTMENT
        ));

        menuItems.put("Iron_Indicator", createGuiItem(
                Material.IRON_INGOT,
                ChatColor.RESET + "Iron Remaining",
                NO_ENCHANTMENT,
                ChatColor.RED + "0"
        ));
        menuItems.put("Gold_Indicator", createGuiItem(
                Material.GOLD_INGOT,
                ChatColor.RESET + "Gold Remaining",
                NO_ENCHANTMENT,
                ChatColor.RED + "0"
        ));
        menuItems.put("Emerald_Indicator", createGuiItem(
                Material.EMERALD,
                ChatColor.RESET + "Emeralds Remaining",
                NO_ENCHANTMENT,
                ChatColor.RED + "0"
        ));
        menuItems.put("Diamond_Indicator", createGuiItem(
                Material.DIAMOND,
                ChatColor.RESET + "Diamonds Remaining",
                NO_ENCHANTMENT,
                ChatColor.RED + "0"
        ));
        menuItems.put("Nether_Star_Indicator", createGuiItem(
                Material.NETHER_STAR,
                ChatColor.YELLOW + "Nether Stars Remaining",
                NO_ENCHANTMENT,
                ChatColor.RED + "0"
        ));
        //</editor-fold>
        //</editor-fold>

        //<editor-fold desc="Containers" defaultstate="collapsed">
        Set<Material> _containers = new HashSet<>();
        _containers.add(Material.CHEST);
        _containers.add(Material.TRAPPED_CHEST);
        _containers.add(Material.SHULKER_BOX);
        _containers.add(Material.WHITE_SHULKER_BOX);
        _containers.add(Material.ORANGE_SHULKER_BOX);
        _containers.add(Material.MAGENTA_SHULKER_BOX);
        _containers.add(Material.LIGHT_BLUE_SHULKER_BOX);
        _containers.add(Material.YELLOW_SHULKER_BOX);
        _containers.add(Material.LIME_SHULKER_BOX);
        _containers.add(Material.PINK_SHULKER_BOX);
        _containers.add(Material.GRAY_SHULKER_BOX);
        _containers.add(Material.LIGHT_GRAY_SHULKER_BOX);
        _containers.add(Material.CYAN_SHULKER_BOX);
        _containers.add(Material.PURPLE_SHULKER_BOX);
        _containers.add(Material.BLUE_SHULKER_BOX);
        _containers.add(Material.BROWN_SHULKER_BOX);
        _containers.add(Material.GREEN_SHULKER_BOX);
        _containers.add(Material.RED_SHULKER_BOX);
        _containers.add(Material.BLACK_SHULKER_BOX);
        _containers.add(Material.DROPPER);
        _containers.add(Material.DISPENSER);
        _containers.add(Material.HOPPER);
        _containers.add(Material.FURNACE);
        containers = Collections.unmodifiableSet(_containers);
        //</editor-fold>

        //<editor-fold desc="Power Blocks" defaultstate="collapsed">
        Set<Material> _powerBlocks = new HashSet<>();
        _powerBlocks.add(Material.IRON_BLOCK);
        _powerBlocks.add(Material.GOLD_BLOCK);
        _powerBlocks.add(Material.EMERALD_BLOCK);
        _powerBlocks.add(Material.DIAMOND_BLOCK);
        powerBlocks = Collections.unmodifiableSet(_containers);
        //</editor-fold>
    }


    public static ItemStack getBeaconActivator() {
        return beaconActivator.clone();
    }


    public static Set<Material> getContainers() {
        return containers;
    }

    public static boolean isContainer(Material blockType) {
        return containers.contains(blockType);
    }


    public static Set<Material> getPowerBlocks() {
        return powerBlocks;
    }

    public static boolean isPowerBlock(Material blockType) {
        return powerBlocks.contains(blockType);
    }


    public static ItemStack getMenuItem(String id) {
        ItemStack item = menuItems.get(id);
        if(item  != null)
            return item.clone();

        return null;
    }
}
