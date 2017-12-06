package com.mmandsc.DemoVision.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.mmandsc.DemoVision.config.utils.ConfigManager;

public class CommandDvBan implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] args) {
		Player target = Bukkit.getServer().getPlayer(args[0]);
		ConfigManager cm1 = new ConfigManager(target.getDisplayName());
		if (!cm1.exists()) {
			FileConfiguration f = cm1.getConfig();
			f.set("banned", "yes");
			f.set("reason", args[1]);
			target.kickPlayer(args[1]);
			cm1.saveConfig();
		}else if(cm1.exists()){
			FileConfiguration f = cm1.getConfig();
			f.set("banned", "yes");
			f.set("reason", args[1]);
			target.kickPlayer(args[1]);
			cm1.saveConfig();
		}
		return false;
	}

}
