package com.mmandsc.DemoVision.Listeners;

import com.mmandsc.DemoVision.config.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class ClickGuiAccepted implements Listener {

    private Inventory inv;

    @EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (item.getItemMeta().getDisplayName().contains("ACCEPTED")) {
            inv = Bukkit.getServer().createInventory(null, 9, "ACEEPTED PLAYERS");

            ConfigManager mc = new ConfigManager("/players/" + "data");

            FileConfiguration fmc = mc.getConfig();

            ConfigurationSection usersSection = fmc.getConfigurationSection("Users");

            String a = (String) usersSection.get("Users");

			ArrayList<String> users = new ArrayList<String>();

			users.add(fmc.getConfigurationSection("Users").toString());

            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

            SkullMeta meta = (SkullMeta) skull.getItemMeta();

            meta.setOwner(a);

            meta.setDisplayName(org.bukkit.ChatColor.GREEN + a);

            skull.setItemMeta(meta);

            player.openInventory(inv);
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
