package net.codedstingray.advancedbeacons.effect;

public enum FuelLevel {
    IRON,
    GOLD,
    EMERALD,
    DIAMOND,
    NETHER_STAR;

    public int nextLevelValue() {
        return (ordinal() + 1) % 5;
    }

    public FuelLevel nextLevel() {
        return FuelLevel.values()[nextLevelValue()];
    }

    public int previousLevelValue() {
        return (ordinal() + 4) % 5;
    }

    public FuelLevel previousLevel() {
        return FuelLevel.values()[previousLevelValue()];
    }
}
