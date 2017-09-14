package com.mmandsc.DemoVision.Listeners;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.mmandsc.DemoVision.DemoVision;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R1.PlayerConnection;

public class JoinListenerTitle implements Listener {

	private Plugin plugin = DemoVision.getPlugin(DemoVision.class);

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	Player player = event.getPlayer();
	String welcomeTitleString = "Welcome to our server!";
	// String welcomemessage = ChatColor.translateAlternateColorCodes('&', usersSection.getCurrentPath());
	PacketPlayOutTitle welcometitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + welcomeTitleString + "\",\"italic\":false,\"underlined\":false}"), 20 , 40, 30);
	
	PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
	
	connection.sendPacket(welcometitle);
	}
}
