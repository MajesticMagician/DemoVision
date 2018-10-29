package com.mmandsc.DemoVision.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import com.mmandsc.DemoVision.DemoVision;

public class BlockBreakListener implements Listener{
	
	private Plugin plugin = DemoVision.getPlugin(DemoVision.class);
	private int count;
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		org.bukkit.block.Block block = event.getBlock();
		Player player = event.getPlayer();

		Location b_loc = block.getLocation();

		//Check if block type is TNT
		if (block.getType() == Material.TNT) {
			//Get the users section.
			ConfigurationSection usersSection = plugin.getConfig().getConfigurationSection("Users." + player.getUniqueId().toString());
			//If the user's section is null, create a section.
			if (usersSection == null) {
				usersSection = plugin.getConfig().createSection("Users." + player.getUniqueId().toString());
			}

			int count = 0;
			//If the user's section doesn't have a count attribute, make one and set it to 0.
			if (!usersSection.contains("count")) {
				usersSection.set("count", count);
			} 
			//If it does, get the value and add 1.
			else {
				count = usersSection.getInt("count");
				count++;
			}
			//Set the count to the now bigger count value.
			usersSection.set("count", count);
			// Create a block section using the count.
			ConfigurationSection blockSection = usersSection.createSection("Block_" + count + "");
			//Set values
			blockSection.set("x", b_loc.getBlockX());
			blockSection.set("y", b_loc.getBlockY());
			blockSection.set("z", b_loc.getBlockZ());
			blockSection.set("It_was_a_place", b_loc.getWorld().getName());
			plugin.saveConfig();
		}else if (block.getType() == org.bukkit.Material.ACACIA_FENCE) {
			//Get the users section.
			ConfigurationSection usersSection = plugin.getConfig().getConfigurationSection("Users." + player.getUniqueId().toString());
			//If the user's section is null, create a section.
			if (usersSection == null) {
				usersSection = plugin.getConfig().createSection("Users." + player.getUniqueId().toString());
			}

			int count = 0;
			//If the user's section doesn't have a count attribute, make one and set it to 0.
			if (!usersSection.contains("count")) {
				usersSection.set("count", count);
			} 
			//If it does, get the value and add 1.
			else {
				count = usersSection.getInt("count");
				count++;
			}
			//Set the count to the now bigger count value.
			usersSection.set("count", count);
			// Create a block section using the count.
			ConfigurationSection blockSection = usersSection.createSection("Block_" + count + "");
			//Set values
			blockSection.set("x", b_loc.getBlockX());
			blockSection.set("y", b_loc.getBlockY());
			blockSection.set("z", b_loc.getBlockZ());
			blockSection.set("It_was_a_place", b_loc.getWorld().getName());
			plugin.saveConfig();
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		org.bukkit.block.Block block = event.getBlock();
		Player player = event.getPlayer();

		Location b_loc = block.getLocation();

		//Check if block type is TNT
		if (block.getType() == org.bukkit.Material.TNT) {
			//Get the users section.
			ConfigurationSection usersSection = plugin.getConfig().getConfigurationSection("Users." + player.getUniqueId().toString());
			//If the user's section is null, create a section.
			if (usersSection == null) {
				usersSection = plugin.getConfig().createSection("Users." + player.getUniqueId().toString());
			}

			int count = 0;
			//If the user's section doesn't have a count attribute, make one and set it to 0.
			if (!usersSection.contains("count")) {
				usersSection.set("count", count);
			}
			//If it does, get the value and add 1.
			else {
				count = usersSection.getInt("count");
				count++;
			}
			//Set the count to the now bigger count value.
			usersSection.set("count", count);
			// Create a block section using the count.
			ConfigurationSection blockSection = usersSection.createSection("Block_" + count + "");
			//Set values
			blockSection.set("x", b_loc.getBlockX());
			blockSection.set("y", b_loc.getBlockY());
			blockSection.set("z", b_loc.getBlockZ());
			blockSection.set("It_was_a_break", b_loc.getWorld().getName() + event.getPlayer().getItemInHand().toString());
			plugin.saveConfig();
		}
	}


}