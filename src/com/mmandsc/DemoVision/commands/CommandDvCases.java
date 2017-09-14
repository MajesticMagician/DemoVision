package com.mmandsc.DemoVision.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class CommandDvCases implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, 
			String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("YOU MUST BE A PLAYER TO PERFORM THIS COMMAND.");
			return false;
		}
		
		Player player = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 54, "CASES MENU SELECTOR");
		ItemStack spawnItem1 = nameItem(new ItemStack(Material.WOOL), ChatColor.GREEN + "ACCEPTED");
		ItemStack spawnItem2 = nameItem(new ItemStack(Material.WOOL), ChatColor.GREEN + "REJECTED");
		ItemStack spawnItem3 = nameItem(new ItemStack(Material.WOOL), ChatColor.GREEN + "PENDING");
		ItemStack spawnItem4 = nameItem(new ItemStack(Material.WOOL), ChatColor.GREEN + "UNREAD");
		ItemStack spawnItem5 = nameItem(new ItemStack(Material.WOOL), ChatColor.GOLD + "Profile Settings");
		inv.setItem(1, spawnItem1);
		inv.setItem(3, spawnItem2);
		inv.setItem(5, spawnItem3);
		inv.setItem(7, spawnItem4);
		inv.setItem(49, spawnItem5);

		player.openInventory(inv);
		
		return true;
	}
	
	private ItemStack nameItem(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack nameItem(Material item, String name) {
		return nameItem(new ItemStack(item), name);
	}
}
