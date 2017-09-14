package com.mmandsc.DemoVision.commands;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
import com.mmandsc.DemoVision.config.utils.ConfigManager;

public class CommandList implements CommandExecutor {

	private Plugin plugin = DemoVision.getPlugin(DemoVision.class);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player target = Bukkit.getServer().getPlayer(args[0]);
		ConfigManager cm1 = new ConfigManager(target.getDisplayName());
		FileConfiguration f = cm1.getConfig();
		if (cm1.exists()) {
			sender.sendMessage("True");
			String a = f.getString("name");
			String b = f.getString("uuid");
			String c = f.getString("ip");
			String d = f.getString("flight");
			String e = f.getString("permissions");
			sender.sendMessage(a);
			sender.sendMessage(b);
			sender.sendMessage(c);
			sender.sendMessage("Is flight allowed? " + d);
			sender.sendMessage("Perms for " + cm1 + "are " + e);
		}else {
			sender.sendMessage("Please type a player name!");
		}
		return true;
	}
}
