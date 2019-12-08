package net.codedstingray.advancedbeacons.commands;

import net.codedstingray.advancedbeacons.Data;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdAdvancedBeaconActivator implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players");
            return true;
        }

        int amount = 1;
        if(args.length > 0) {
            try {
                amount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Cannot parse " + args[0] + "into a number");
                return false;
            }
        }

        Player player = (Player) sender;
        ItemStack beaconActivator = Data.getBeaconActivator();
        beaconActivator.setAmount(amount);
        player.getInventory().addItem(beaconActivator);
        player.sendMessage(ChatColor.GREEN + "Added " + amount + " Advanced Beacon Activators to your inventory");

        return true;
    }
}
