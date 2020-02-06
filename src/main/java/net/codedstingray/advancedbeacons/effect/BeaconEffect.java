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
