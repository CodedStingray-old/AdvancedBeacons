package net.codedstingray.advancedbeacons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Data {

    private static final ItemStack beaconActivator;
    private static final Set<Material> containers;
    private static final Set<Material> powerBlocks;

    static {
        //beacon activator
        beaconActivator = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = beaconActivator.getItemMeta();

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Advanced Beacon Activator");
        LinkedList<String> lore = new LinkedList<>();
        lore.add(ChatColor.RESET + "Activates an Advanced Beacon.");
        lore.add(ChatColor.RESET + "Consumed after use");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
        beaconActivator.setItemMeta(meta);


        //containers
        Set<Material> _containers = new HashSet<>();
        _containers.add(Material.CHEST);
        _containers.add(Material.TRAPPED_CHEST);
        _containers.add(Material.SHULKER_BOX);
        _containers.add(Material.DROPPER);
        _containers.add(Material.DISPENSER);
        _containers.add(Material.HOPPER);
        _containers.add(Material.FURNACE);
        containers = Collections.unmodifiableSet(_containers);

        //power blocks
        Set<Material> _powerBlocks = new HashSet<>();
        _powerBlocks.add(Material.IRON_BLOCK);
        _powerBlocks.add(Material.GOLD_BLOCK);
        _powerBlocks.add(Material.EMERALD_BLOCK);
        _powerBlocks.add(Material.DIAMOND_BLOCK);
        powerBlocks = Collections.unmodifiableSet(_containers);
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
}
