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

package net.codedstingray.advancedbeacons.effect.logic;

import net.codedstingray.advancedbeacons.AdvancedBeacons;
import net.codedstingray.advancedbeacons.beacon.AdvancedBeacon;
import net.codedstingray.advancedbeacons.beacon.BeaconUtilities;
import net.codedstingray.advancedbeacons.effect.BeaconEffect;
import net.codedstingray.advancedbeacons.effect.BeaconEffectLogic;
import net.codedstingray.advancedbeacons.util.scheduler.SetBlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockGrowEvent;

public class LogicFasterPlantGrowth implements BeaconEffectLogic {
    private BeaconEffect effect;

    @Override
    public void setEffect(BeaconEffect effect) {
        this.effect = effect;
    }

    @Override
    public void init(String... args) {

    }

    @EventHandler
    public void onPlantGrowth(BlockGrowEvent event) {
        Block block = event.getBlock();
        Material blockType = block.getType();

        switch(blockType) {
            //TODO replace with collection check
            case WHEAT:
            case BEETROOTS:
            case POTATOES:
            case CARROTS:
                //TODO: replace with getting beacons that actually have the effect
                boolean apply = false;
                for(AdvancedBeacon beacon: AdvancedBeacons.getInstance().getBeaconManager().getAdvancedBeacons()) {
                    if(BeaconUtilities.inRange(beacon, block.getLocation())) {
                        apply = true;
                    }
                }

                if(apply) {
                    BlockData origData = event.getBlock().getState().getBlockData();
                    BlockData newData = event.getNewState().getBlockData();
                    if(!(origData instanceof Ageable) || !(newData instanceof Ageable)) {
                        AdvancedBeacons.getInstance().getLogger().warning("Plant block data is not of instance Ageable");
                        return;
                    }
                    Ageable origAgeable = (Ageable) origData;
                    Ageable newAgeable = (Ageable) newData;

                    int oldAge = origAgeable.getAge();
                    int newAge = newAgeable.getAge();
                    int diff = newAge - oldAge; //the amount aged by; this will be added once more to the new age

                    newAgeable.setAge(Math.min(newAgeable.getMaximumAge(), newAge + diff));

                    //event.getBlock().setBlockData(newAgeable);
                    //TODO: find nicer way
                    new SetBlockData(event.getBlock(), newAgeable).runTaskLater(AdvancedBeacons.getInstance(), 1);

                    AdvancedBeacons.getInstance().getLogger().info("Doubled aging from " + diff + " to " + (2*diff));
                }
        }
    }
}
