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

package net.codedstingray.advancedbeacons.inventory;

import net.codedstingray.advancedbeacons.AdvancedBeacons;
import net.codedstingray.advancedbeacons.beacon.AdvancedBeacon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractBeaconGui implements InventoryHolder, Listener {

    protected Inventory inventory;
    protected AdvancedBeacon beacon;

    public AbstractBeaconGui(int size, String title, AdvancedBeacon beacon) {
        inventory = Bukkit.createInventory(this, size, title);
        this.beacon = beacon;

        AdvancedBeacons.getInstance().getServer().getPluginManager().registerEvents(this, AdvancedBeacons.getInstance());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public AdvancedBeacon getBeacon() {
        return beacon;
    }

    public void openInventory(Player p) {
        p.openInventory(inventory);
    }

    protected void setItem(int row, int column, ItemStack item) {
        setItem(row, column, item, 1);
    }

    protected void setItem(int row, int column, ItemStack item, int amount) {
        item.setAmount(amount);
        int index = 9 * row + column;
        inventory.setItem(index, item);
    }

    protected ItemStack getItem(int row, int column) {
        int index = 9 * row + column;
        return inventory.getItem(index);
    }

    //TODO: for optimization I can probably condense this into a sincle listener instead of one for each inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory() != inventory) {
            return;
        }

        handleInventoryClick(event);
    }

    public abstract void initializeItems();
    public abstract void update();

    protected abstract void handleInventoryClick(InventoryClickEvent event);
}
