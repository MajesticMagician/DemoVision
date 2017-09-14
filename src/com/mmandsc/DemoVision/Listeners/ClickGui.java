package com.mmandsc.DemoVision.Listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ClickGui implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		if (!inv.getTitle().equals("CASES MENU SELECTOR"))
			return;
		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (item.getItemMeta().getDisplayName().contains("ACCEPTED")) {
			if (item.getType() == Material.WOOL) {
				ItemMeta user = item.getItemMeta();
				player.sendMessage("OPENING GUI: " + ChatColor.GREEN + "ACCEPTED");

				event.setCancelled(true);
				player.closeInventory();
				Inventory inv1 = Bukkit.createInventory(null, 54, "ACCEPTED MENU");
				player.openInventory(inv1);
				ItemStack spawnItem1 = nameItem(new ItemStack(Material.WOOL), ChatColor.GREEN + "ACCEPTED1");
				inv1.setItem(1, spawnItem1);

			} else if (item.getItemMeta().getDisplayName().contains("REJECTED")) {
				if (item.getType() == Material.WOOL) {
					player.sendMessage("OPENING GUI: " + ChatColor.RED + "REJECTED");

					event.setCancelled(true);
					player.closeInventory();
				}
			} else if (item.getItemMeta().getDisplayName().contains("PENDING")) {
				if (item.getType() == Material.WOOL) {
					player.sendMessage("OPENING GUI: " + ChatColor.YELLOW + "PENDING");

					event.setCancelled(true);
					player.closeInventory();
				}
			} else if (item.getItemMeta().getDisplayName().contains("UNREAD")) {
				if (item.getType() == Material.WOOL) {
					player.sendMessage("OPENING GUI: " + ChatColor.GRAY + "UNREAD");

					event.setCancelled(true);
					player.closeInventory();
				}
			} else if (item.getItemMeta().getDisplayName().contains("Profile Settings")) {
				player.sendMessage(ChatColor.GOLD + "Opening Profile Settings");

				Inventory profile = Bukkit.createInventory(null, 54, "ACCEPTED MENU");
			}
		}
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
