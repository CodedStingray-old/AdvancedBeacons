package net.codedstingray.advancedbeacons.commands;

import net.codedstingray.advancedbeacons.AdvancedBeacons;

public class CommandInitializer {

    private static boolean initialized = false;

    public static void initCommands(AdvancedBeacons plugin) {
        if(initialized) {
            throw new IllegalStateException("Commands are alraedy initialized");
        }

        initialized = true;

        plugin.getLogger().info("Initializing Advanced Beacons commands");

        CmdAdvancedBeaconActivator cmdAdvancedBeaconActivator = new CmdAdvancedBeaconActivator();
        plugin.getCommand("advancedbeaconactivator").setExecutor(cmdAdvancedBeaconActivator);

        plugin.getLogger().info("Command initialization finished");
    }
}
