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
