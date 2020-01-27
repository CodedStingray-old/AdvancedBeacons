package net.codedstingray.advancedbeacons.effect;

import java.util.HashMap;

public class BeaconEffectManager {

    private HashMap<String, BeaconEffect> registeredEffects = new HashMap<>();

    public void registerBeaconEffect(BeaconEffect effect, BeaconEffectLogic logic, String... args) {

        if(registeredEffects.get(effect.name) != null) {
            throw new IllegalArgumentException("Beacon effect with name \"" + effect.name + "\" already exists");
        }

        registeredEffects.put(effect.name, effect);
        effect.setLogic(logic);
        logic.init(args);
    }
}
