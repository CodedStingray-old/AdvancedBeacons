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

package net.codedstingray.advancedbeacons.effect;

import org.bukkit.inventory.ItemStack;

/**
 * A data holding class that defines the static properties and logic of a beacon effect
 */
public class BeaconEffect {

    public final String name;

    public final ItemStack disabledIcon;
    public final ItemStack enabledIcon;
    //TODO inactive for when out of fuel?

    public final FuelLevel minFuelLevel;
    public final int minBeaconTier;

    /**
     * The beacon power taken up by this effect.
     */
    public final int beaconPower;

    private BeaconEffectLogic logic;

    /*package*/ BeaconEffect(String name, ItemStack disabledIcon, ItemStack enabledIcon, FuelLevel minFuelLevel,
                        int minBeaconTier, int beaconPower) {

        this.name = name;

        this.disabledIcon = disabledIcon;
        this.enabledIcon = enabledIcon;

        this.minFuelLevel = minFuelLevel;
        this.minBeaconTier = minBeaconTier;

        this.beaconPower = beaconPower;
    }

    /*package*/ void setLogic(BeaconEffectLogic logic) {
        this.logic = logic;
    }
}
