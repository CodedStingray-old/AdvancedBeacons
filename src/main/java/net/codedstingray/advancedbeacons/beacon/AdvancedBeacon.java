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

import net.codedstingray.advancedbeacons.inventory.BeaconGuiMain;
import org.bukkit.Location;

public class AdvancedBeacon {

    private BeaconStructure structure;
    private Location location;

    private boolean checkStructure = false;

    public final BeaconGuiMain guiMain = new BeaconGuiMain(this);

    public AdvancedBeacon(BeaconStructure structure, Location location) {
        this.structure = structure;
        this.location = location.clone();
    }

    public Location getLocation() {
        return location.clone();
    }

    public BeaconStructure getStructure() {
        return structure;
    }

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
    }
}
