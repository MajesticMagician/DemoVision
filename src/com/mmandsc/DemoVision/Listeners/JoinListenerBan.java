package com.mmandsc.DemoVision.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mmandsc.DemoVision.config.utils.ConfigManager;

public class JoinListenerBan implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		ConfigManager cm1 = new ConfigManager(e.getPlayer());
		FileConfiguration f = cm1.getConfig();
		if (cm1.exists()) {
			String a = f.getString("banned");
			String b = f.getString("reason");
			if(a.equals("yes")){
				e.getPlayer().kickPlayer("YOU HAVE BEEN BANNED FOR " + b);
			}
		}
	}

}
