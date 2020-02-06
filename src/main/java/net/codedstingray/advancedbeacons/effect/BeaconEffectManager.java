package net.codedstingray.advancedbeacons.effect;

import net.codedstingray.advancedbeacons.AdvancedBeacons;
import net.codedstingray.advancedbeacons.beacon.AdvancedBeacon;
import net.codedstingray.advancedbeacons.effect.logic.LogicFasterPlantGrowth;
import net.codedstingray.advancedbeacons.util.EnchantmentData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static net.codedstingray.advancedbeacons.util.ItemUtilities.createGuiItem;

public class BeaconEffectManager {

    private HashMap<String, BeaconEffect> registeredEffects = new HashMap<>();

    public void registerBeaconEffect(BeaconEffect effect, BeaconEffectLogic logic, String... args) {

        if(registeredEffects.get(effect.name) != null) {
            throw new IllegalArgumentException("Beacon effect with name \"" + effect.name + "\" already exists");
        }

        registeredEffects.put(effect.name, effect);
        effect.setLogic(logic);
        logic.init(args);

        AdvancedBeacons.getInstance().getServer().getPluginManager().registerEvents(logic, AdvancedBeacons.getInstance());
    }

    //TODO: prolly a really temporary method
    public void createEffects() {
        //TODO special method for effect icons that add the name and "Enabled"/"Disabled" & "Left-Click"/Right-Click" Lore automatically
        ItemStack iconDisabled = createGuiItem(Material.WHEAT, ChatColor.RESET + "Faster Crop Growth",
                EnchantmentData.NO_ENCHANTMENT, ChatColor.RESET + "Increases the growth rate of crops",
                ChatColor.YELLOW + "Disabled");

        ItemStack iconEnabled = createGuiItem(Material.WHEAT, ChatColor.RESET + "Faster Crop Growth",
                EnchantmentData.NO_ENCHANTMENT, ChatColor.RESET + "Increases the growth rate of crops",
                ChatColor.GREEN + "Enabled");

        BeaconEffect effect = new BeaconEffect("Faster Crop Growth", iconDisabled, iconEnabled,
                FuelLevel.IRON, 1, 50);

        registerBeaconEffect(effect, new LogicFasterPlantGrowth());

        AdvancedBeacons.getInstance().getLogger().info("Effect \"Faster Plant Growth\" successfully registered");
    }
}
