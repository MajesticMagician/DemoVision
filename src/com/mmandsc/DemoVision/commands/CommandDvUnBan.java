package com.mmandsc.DemoVision.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.mmandsc.DemoVision.config.utils.ConfigManager;

public class CommandDvUnBan implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] args) {
		ConfigManager cm1 = new ConfigManager(args[0]);
		if (!cm1.exists()) {
			FileConfiguration f = cm1.getConfig();
			f.set("banned", "no");
			f.set("reason", args[1]);
			cm1.saveConfig();
		}else if(cm1.exists()){
			FileConfiguration f = cm1.getConfig();
			f.set("banned", "no");
			f.set("reason", args[1]);
			cm1.saveConfig();
		}
		return false;
	}

}
