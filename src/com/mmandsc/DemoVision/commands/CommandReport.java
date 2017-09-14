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

public class CommandReport implements CommandExecutor {

	private Plugin plugin = DemoVision.getPlugin(DemoVision.class);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player target = Bukkit.getServer().getPlayer(args[0]);
		ConfigurationSection cm = plugin.getConfig().getConfigurationSection(args[0]);
		ConfigManager cm1 = new ConfigManager(target.getDisplayName());
		if (!cm1.exists()) {
			FileConfiguration f = cm1.getConfig();
			f.set("name", target.getDisplayName());
			f.set("uuid", target.getUniqueId().toString());
			f.set("ip", target.getAddress().getAddress().toString());
			f.set("flight", target.getAllowFlight());
			f.set("active_potions", target.getActivePotionEffects().toString());
			f.set("can_pickup", target.getCanPickupItems());
			f.set("compass_target", target.getCompassTarget());
			f.set("permissions", target.getEffectivePermissions().toString());
			f.set("equipment", target.getEquipment().toString());
			cm1.saveConfig();
		}else if(cm1.exists()){
			
			FileConfiguration f = cm1.getConfig();
			f.set("name", target.getDisplayName());
			f.set("uuid", target.getUniqueId().toString());
			f.set("ip", target.getAddress().getAddress().toString());
			f.set("flight", target.getAllowFlight());
			f.set("active_potions", target.getActivePotionEffects().toString());
			f.set("can_pickup", target.getCanPickupItems());
			f.set("compass_target", target.getCompassTarget());
			f.set("permissions", target.getEffectivePermissions().toString());
			f.set("equipment", target.getEquipment().toString());
			cm1.saveConfig();
		}
		return true;
	}
}
