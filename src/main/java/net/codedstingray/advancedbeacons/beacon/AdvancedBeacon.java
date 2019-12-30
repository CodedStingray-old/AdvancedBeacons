package net.codedstingray.advancedbeacons.beacon;

import org.bukkit.Location;

public class AdvancedBeacon {

    private BeaconStructure structure;
    private Location location;

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
}
