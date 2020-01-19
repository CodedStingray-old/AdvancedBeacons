package net.codedstingray.advancedbeacons.util;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentData {

    public static final EnchantmentData[] NO_ENCHANTMENT = {};


    public final Enchantment enchantment;
    public final int level;
    public final boolean ignoreLevelCap;

    public EnchantmentData(Enchantment enchantment, int level, boolean ignoreLevelCap) {
        this.enchantment = enchantment;
        this.level = level;
        this.ignoreLevelCap = ignoreLevelCap;
    }
}
