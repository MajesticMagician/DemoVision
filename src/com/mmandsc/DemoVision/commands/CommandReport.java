package com.mmandsc.DemoVision.commands;

import com.mmandsc.DemoVision.check.Level;
import com.mmandsc.DemoVision.config.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.mmandsc.DemoVision.DemoVision;

import java.util.Set;

public class CommandReport implements CommandExecutor {

	private Plugin plugin = DemoVision.getPlugin(DemoVision.class);

	int a = 1;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("<" + ChatColor.LIGHT_PURPLE + "DV" + ChatColor.RESET + ">"
					+ " Please type a player name after 'report'");
		} else {

			ConfigManager mcReports = new ConfigManager("/players/" + args[1] + "data");
			FileConfiguration fmc = mcReports.getConfig();

			if (args.length >= 1) {

				String reportReason = "";

				for (String arg : args) {
					reportReason = reportReason + " " + arg;
				}

				if (!mcReports.getConfig().contains(args[0])) {
					fmc.set(args[0] + " Report by " + sender.getName(), " " + reportReason);
					mcReports.saveConfig();
				} else {
					sender.sendMessage("Player already submitted!");
				}
			}
		}
		return true;
	}

}
