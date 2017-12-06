package com.mmandsc.DemoVision.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import com.mmandsc.DemoVision.DemoVision;
import com.mmandsc.DemoVision.config.utils.ConfigManager;

import net.md_5.bungee.api.ChatColor;

public class AntiSpeed implements Listener{

	private Plugin plugin = DemoVision.getPlugin(DemoVision.class);

	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event) {
		Player target = event.getPlayer();
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
		} else if (cm1.exists()) {

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
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {

		if(e.getPlayer().getWalkSpeed() < 1.5){
			e.getPlayer().kickPlayer("Speeding is not allowed!");
		}

	}
	
}
