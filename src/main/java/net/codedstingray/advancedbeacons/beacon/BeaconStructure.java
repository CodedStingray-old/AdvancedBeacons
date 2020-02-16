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

public class BeaconStructure {

    private static final BeaconStructure NO_BEACON = new BeaconStructure(-1, 0, false);
    private static final BeaconStructure INVALID = new BeaconStructure(0, 0, false);

    /**
     * Represents the number of pyramid layers the beacon has. Between 1 and 4 for valid beacons, 0 representing
     * an invalid structure and -1 representing no beacon being present.
     */
    private int tier;

    /**
     * The beacon power calculated from the numbers and types of blocks making up the beacon pyramid.
     */
    private int power;

    private boolean valid;

    private BeaconStructure(int tier, int power, boolean valid) {
        this.tier = tier;
        this.power = power;
        this.valid = valid;
    }

    public int getTier() {
        return tier;
    }

    public int getPower() {
        return power;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        return "BeaconStructure [tier=" + tier + " | power = " + power + " | valid = " +valid + "]";
    }


    public static BeaconStructure noBeacon() {
        return NO_BEACON;
    }

    public static BeaconStructure invalid() {
        return INVALID;
    }

    public static BeaconStructure valid(int tier, int power) {
        //TODO: implement object pooling / caching
        return new BeaconStructure(tier, power, true);
    }
}
