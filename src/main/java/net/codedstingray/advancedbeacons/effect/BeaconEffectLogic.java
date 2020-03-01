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

import org.bukkit.event.Listener;

/**
 * Interface for classes providing the central logic of beacon effects.
 *
 * This interface extends {@link Listener} and will be automatically registered with the Bukkit event system
 * when the effect is registered.
 */
public interface BeaconEffectLogic extends Listener {

    /**
     * Sets the effect this logic is bound to. Each logic may only be bound to a single logic.
     */
    void setEffect(BeaconEffect effect);

    void init(String... args);

    default void update() {}
}
