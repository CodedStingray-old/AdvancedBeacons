package net.codedstingray.advancedbeacons.effect;

import org.bukkit.event.Listener;

/**
 * Interface for classes providing the central logic of beacon effects.
 *
 * This interface extends {@link Listener} and will be automatically registered with the Bukkit event system
 * when the effect is registered.
 */
public interface BeaconEffectLogic extends Listener {

    void init(String... args);

    default void update() {}
}
