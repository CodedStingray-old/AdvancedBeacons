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

import net.codedstingray.advancedbeacons.beacon.BeaconManager;
import net.codedstingray.advancedbeacons.beacon.BeaconUpdater;
import net.codedstingray.advancedbeacons.commands.CommandInitializer;
import net.codedstingray.advancedbeacons.event.listener.BeaconActivatorListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedBeacons extends JavaPlugin {

    private BeaconManager beaconManager;
    private BeaconUpdater beaconUpdater;

    @Override
    public void onEnable() {
        instance = this;
        beaconManager = new BeaconManager();
        beaconUpdater = new BeaconUpdater();
        beaconUpdater.runTaskTimer(this, 0, 1);

        CommandInitializer.initCommands(this);

        getServer().getPluginManager().registerEvents(beaconManager, this);
        getServer().getPluginManager().registerEvents(new BeaconActivatorListener(), this);

        NamespacedKey key = new NamespacedKey(this, "recipe_advanced_beacon_activator");
        ShapedRecipe recipe = new ShapedRecipe(key, Data.getBeaconActivator());

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

    public BeaconManager getBeaconManager() {
        return beaconManager;
    }


    private static AdvancedBeacons instance;

    public static AdvancedBeacons getInstance() {
        return instance;
    }
}
