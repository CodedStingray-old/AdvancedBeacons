package net.codedstingray.advancedbeacons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Data {

    private static final ItemStack beaconActivator;
    private static final HashMap<String, ItemStack> menuItems = new HashMap<>();
    private static final Set<Material> containers;
    private static final Set<Material> powerBlocks;

    private static final EnchantmentData[] NO_ENCHANTMENT = {};

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
        menuItems.put("Main_Header", createGuiItem(
                Material.BEACON,
                ChatColor.AQUA + "Advanced Beacon",
                NO_ENCHANTMENT,
                ChatColor.RESET + "Status: Unknown"
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

    private static ItemStack createGuiItem(Material material, String name, EnchantmentData[] enchantments, String... lore) {
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
        return menuItems.get(id);
    }


    private static class EnchantmentData {
        public final Enchantment enchantment;
        public final int level;
        public final boolean ignoreLevelCap;

        public EnchantmentData(Enchantment enchantment, int level, boolean ignoreLevelCap) {
            this.enchantment = enchantment;
            this.level = level;
            this.ignoreLevelCap = ignoreLevelCap;
        }
    }
}
