package com.mmandsc.DemoVision.commands;

import com.mmandsc.DemoVision.check.Level;
import com.mmandsc.DemoVision.config.utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.mmandsc.DemoVision.DemoVision;

import java.util.Set;

public class CommandList implements CommandExecutor {

    private Plugin plugin = DemoVision.getPlugin(DemoVision.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("dv.cmds.perms.list")) {
            if (args.length == 0) {
                sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">" + " Please type a player name after 'list'");
            } else {
                ConfigManager mc = new ConfigManager("/players/" + "data");
                FileConfiguration fmc = mc.getConfig();

                Set<String> keys = fmc.getConfigurationSection("Users").getKeys(false);
                if (args.length == 0) {
                    ConfigurationSection usersSection = fmc.getConfigurationSection("Users");
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + "> " + keys.toString());
                } else {
                    if (!mc.exists()) {
                        sender.sendMessage("PLEASE CONTACT A SERVER ADMIN, AND TELL THEM THIS ERROR MSG: " + ChatColor.RED + Level.FILENOTFOUND.toString());
                    } else {
                        ConfigurationSection usersSection = fmc.getConfigurationSection("Users");

                        String a = usersSection.getString("Users");

                        sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">" + " Player: " + args[0] + " has this data: ");

                        ConfigManager cm1 = new ConfigManager("/players/" + args[0] + "/data");
                        FileConfiguration f = cm1.getConfig();

                        String name = f.getString("Data: Name ");
                        String uuid = f.getString("Data: UUID ");
                        String ws = f.getString("Data: Walk Speed ");
                        String rt = f.getString("Reported Times: ");
                        String ip = f.getString("Data: IP ");
                        sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">" + " Data: Name of " + name);
                        sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">" + " Data: IP of " + ip);
                        sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">" + " Data: UUID of " + uuid);
                        sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">" + " Data: Walk Speed of " + ws);
                        sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">" + " Data: " + args[0] + " has been reported " + rt + " times");
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "YOU HAVE NO PERMS FOR THIS COMMAND, PLEASE CONTACT A ADMIN IF THIS IS IN ERROR");
        }
        return true;
    }
}
