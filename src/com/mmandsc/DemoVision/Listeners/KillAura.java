package com.mmandsc.DemoVision.Listeners;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.mmandsc.DemoVision.config.utils.ConfigManager;

public class KillAura implements Listener {

	@EventHandler
	public void onHit(EntityDamageEvent event) {
		ConfigManager cm1 = new ConfigManager(event.getEntity().getName());
		String a = event.getEntity().getName().toString();
		FileConfiguration f = cm1.getConfig();
		f.set("hit", "true");
		cm1.saveConfig();
	}
}
