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
