package com.mmandsc.DemoVision.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDvRecord implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		Player player = (Player) sender;
		
		if (sender instanceof Player) {
			
		} else {
			sender.sendMessage("You need to be a player to do that command!");
		}

		return false;
	}

}
