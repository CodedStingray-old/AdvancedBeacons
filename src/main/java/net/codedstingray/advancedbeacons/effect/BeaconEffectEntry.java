package net.codedstingray.advancedbeacons.effect;

import static net.codedstingray.advancedbeacons.util.GeneralUtils.smaller;
import static net.codedstingray.advancedbeacons.util.GeneralUtils.greater;

/**
 * Represents an effect entry inside a beacon. Each beacon has 12 pre-created entries who's values get updated.
 */
//TODO: add ability to swap entries? would require a utility #copyValuesFromEntry(BeaconEffectEntry) method
public class BeaconEffectEntry {

    private BeaconEffect effect;

    private FuelLevel currentMinFuelLevel;
    private FuelLevel currentMaxFuelLevel;

    public BeaconEffectEntry() {
        currentMinFuelLevel = FuelLevel.IRON;
        currentMaxFuelLevel = FuelLevel.IRON;
    }

    //getters
    public BeaconEffect getEffect() {
        return effect;
    }

    public FuelLevel getCurrentMinFuelLevel() {
        return currentMinFuelLevel;
    }

    public FuelLevel getCurrentMaxFuelLevel() {
        return currentMaxFuelLevel;
    }

    //setters
    public void setEffect(BeaconEffect effect) {
        this.effect = effect;
    }

    //TODO: change so only the clicked level ever gets modified
    public void increaseMinFuelLevel() {
        currentMinFuelLevel = currentMinFuelLevel.nextLevel();
        if(smaller(currentMinFuelLevel, effect.minFuelLevel)) {
            currentMinFuelLevel = effect.minFuelLevel;
        }

        if(greater(currentMinFuelLevel, currentMaxFuelLevel)) {
            //if we exceeded max level, adjust the max level
            currentMaxFuelLevel = currentMinFuelLevel;
        }
    }

    public void decreaseMinFuelLevel() {
        currentMinFuelLevel = currentMinFuelLevel.previousLevel();
        if(smaller(currentMinFuelLevel, effect.minFuelLevel)) {
            currentMinFuelLevel = effect.minFuelLevel;
        }

        if(greater(currentMinFuelLevel, currentMaxFuelLevel)) {
            //rollover rolls to nether star, so clamp to max level
            currentMinFuelLevel = currentMaxFuelLevel;
        }
    }

    public void increaseMaxFuelLevel() {
        currentMaxFuelLevel = currentMaxFuelLevel.nextLevel();
        if(smaller(currentMaxFuelLevel, currentMinFuelLevel)) {
            //rollover rolls to iron, so clamp to min Level
            currentMaxFuelLevel = currentMinFuelLevel;
        }
    }

    public void decreaseMaxFuelLevel() {
        currentMaxFuelLevel = currentMaxFuelLevel.previousLevel();
        if(smaller(currentMaxFuelLevel, currentMinFuelLevel)) {
            //if we drop below min level, adjust the max level (yes, max level)
            currentMaxFuelLevel = currentMinFuelLevel;
        }
    }
}
