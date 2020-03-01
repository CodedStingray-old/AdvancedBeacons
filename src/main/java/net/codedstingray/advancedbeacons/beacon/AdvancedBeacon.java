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

package net.codedstingray.advancedbeacons.beacon;

import net.codedstingray.advancedbeacons.effect.BeaconEffectEntry;
import net.codedstingray.advancedbeacons.inventory.BeaconGuiMain;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdvancedBeacon {

    private BeaconStructure structure;
    private Location location;

    private Block[] chests = new Block[4];

    private boolean checkStructure = false;

    public final BeaconGuiMain guiMain;

    private int fuelIron = 0;
    private int fuelGold = 0;
    private int fuelEmerald = 0;
    private int fuelDiamond = 0;
    private int fuelNetherStar = 0;

    private int range = 20;

    private BeaconEffectEntry[] effects = new BeaconEffectEntry[12];

    public AdvancedBeacon(BeaconStructure structure, Location location) {
        this.structure = structure;
        this.location = location.clone();

        chests[0] = location.getWorld().getBlockAt(new Location(location.getWorld(), location.getBlockX() - 1, location.getBlockY() - 1, location.getBlockZ()));
        chests[1] = location.getWorld().getBlockAt(new Location(location.getWorld(), location.getBlockX(), location.getBlockY() - 1, location.getBlockZ() - 1));
        chests[2] = location.getWorld().getBlockAt(new Location(location.getWorld(), location.getBlockX(), location.getBlockY() - 1, location.getBlockZ() + 1));
        chests[3] = location.getWorld().getBlockAt(new Location(location.getWorld(), location.getBlockX() + 1, location.getBlockY() - 1, location.getBlockZ()));

        guiMain = new BeaconGuiMain(this);
        guiMain.initializeItems();

        for(int i = 0; i < effects.length; i++) {
            effects[i] = new BeaconEffectEntry();
        }
    }

    public Location getLocation() {
        return location.clone();
    }

    public BeaconStructure getStructure() {
        return structure;
    }

    public int getRange() {
        return range;
    }

    //<editor-fold desc="Fuel Getters" defaultstate="collapsed">
    public int getFuelIron() {
        return fuelIron;
    }

    public int getFuelGold() {
        return fuelGold;
    }

    public int getFuelEmerald() {
        return fuelEmerald;
    }

    public int getFuelDiamond() {
        return fuelDiamond;
    }

    public int getFuelNetherStar() {
        return fuelNetherStar;
    }
    //</editor-fold>

    public void setStructure(BeaconStructure structure) {
        this.structure = structure;
    }

    public void checkStructure() {
        checkStructure = true;
    }

    public void update() {
        if(checkStructure) {
            checkStructure = false;
            BeaconStructure structure = BeaconUtilities.validateBeacon(this);
            setStructure(structure);
        }

        if(structure.isValid()) {
            updateItemCounts();
        }

        guiMain.update();
    }

    private void updateItemCounts() {
        int fIron = 0, fGold = 0, fEmerald = 0, fDiamond = 0, fNetherStar = 0;

        for(Block chest: chests) {
            BlockState state = chest.getState();
            if(!(state instanceof Container)) {
                checkStructure();
                return;
            }

            Inventory inventory;
            if(state instanceof Chest) {
                inventory = ((Chest) state).getBlockInventory();
            } else {
                inventory = ((Container) state).getInventory();
            }

            ItemStack[] contents = inventory.getContents();
            for(ItemStack itemStack: contents) {
                if(itemStack != null) {
                    switch (itemStack.getType()) {
                        case IRON_INGOT:
                            fIron += itemStack.getAmount();
                            break;
                        case IRON_BLOCK:
                            fIron += (9 * itemStack.getAmount());
                            break;
                        case GOLD_INGOT:
                            fGold += itemStack.getAmount();
                            break;
                        case GOLD_BLOCK:
                            fGold += (9 * itemStack.getAmount());
                            break;
                        case EMERALD:
                            fEmerald += itemStack.getAmount();
                            break;
                        case EMERALD_BLOCK:
                            fEmerald += (9 * itemStack.getAmount());
                            break;
                        case DIAMOND:
                            fDiamond += itemStack.getAmount();
                            break;
                        case DIAMOND_BLOCK:
                            fDiamond += (9 * itemStack.getAmount());
                            break;
                        case NETHER_STAR:
                            fNetherStar += itemStack.getAmount();
                            break;
                    }
                }
            }
        }

        fuelIron = fIron;
        fuelGold = fGold;
        fuelEmerald = fEmerald;
        fuelDiamond = fDiamond;
        fuelNetherStar = fNetherStar;
    }
}
