package com.mmandsc.DemoVision.commands;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.mmandsc.DemoVision.DemoVision;

import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.EnumParticle;
import net.minecraft.server.v1_8_R1.PacketPlayOutWorldParticles;

public class CommandDv implements CommandExecutor {

	private Plugin plugin = DemoVision.getPlugin(DemoVision.class);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 12 || args.length == 2) {
			// Check if Material is TNT
			if (args[0].equals("block")) {
				OfflinePlayer offPlayer = Bukkit.getServer().getOfflinePlayer(args[1]);
				// Check if the offlineplayer found is null. If so it means the player is
				// invalid. This should not occur.
				if (offPlayer == null) {
					sender.sendMessage("The player " + args[1] + " could not be found!");
					return true;
				}
				// Get offline player's UUID.
				UUID offPlayerUUID = offPlayer.getUniqueId();
				ConfigurationSection usersSection = plugin.getConfig().getConfigurationSection("Users");
				// Check if the section for users is null or has no values. If either is true
				// then the section
				// has no offenses logged.
				if (usersSection == null || plugin.getConfig().isSet("Users") == false) {
					sender.sendMessage(ChatColor.RED + "There have been no logged offenses.");
					return true;
				}

				// Get the offline player's section itself.
				// If null the offline player has no stored data
				ConfigurationSection UUIDSection = usersSection.getConfigurationSection(offPlayerUUID.toString());
				if (UUIDSection == null) {
					sender.sendMessage(ChatColor.RED + "The player you selected has no stored data!");
					return true;
				}

				// Create variables
				int xArg = 0;
				int yArg = 0;
				int zArg = 0;
				boolean usingCoords = false;

				if (args.length == 5) {
					usingCoords = true;
					try {
						// Translate arguments from strings to integers
						xArg = Integer.parseInt(args[2]);
						yArg = Integer.parseInt(args[3]);
						zArg = Integer.parseInt(args[4]);
					} catch (NumberFormatException e) {
						sender.sendMessage(ChatColor.RED + "Please put in valid coordinates!");
						return true;
					}
				}

				// Look through all of the keys in the player's section. These will be:
				// Block_0:
				// Block_1:
				// Etc.
				for (String key : UUIDSection.getKeys(true)) {
					// Get the block's section.
					ConfigurationSection blockSection = UUIDSection.getConfigurationSection(key);
					if (blockSection == null || blockSection.getValues(true) == null) {
						continue;
					}

					// getValues(true). The "true" means to deep-search. This means go through every
					// possible sub-section
					// we want this
					// A map is just a super class of "HashMap"
					Map<String, Object> blockSectValues = blockSection.getValues(true);

					int x = 0;
					int y = 0;
					int z = 0;
					try {
						// Set x, y, and z to be the values in the block's section's values.
						x = (int) blockSectValues.get("x");
						y = (int) blockSectValues.get("y");
						z = (int) blockSectValues.get("z");
					} catch (NullPointerException e) {
						sender.sendMessage(ChatColor.RED + "The config is not set up properly!");
						continue;
					}

					// Check if the filter is on, and if it is make sure the coordinates are the
					// same.
					if (usingCoords) {
						if (x != xArg && y != yArg && z != zArg) {
							continue;
						}
					}

					String worldName = "";
					boolean place = false;
					if (blockSectValues.containsKey("It_was_a_place")) {
						place = true;
						worldName = (String) blockSectValues.get("It_was_a_place");
					} else if (blockSectValues.containsKey("It_was_a_break")) {
						place = false;
						worldName = (String) blockSectValues.get("It_was_a_break");
						if (sender instanceof Player) {
							Player player = (Player) sender;
							// Run the method to show particles
							doBreak(player, worldName, x, y, z);
						}
					}
					sender.sendMessage(ChatColor.BLUE + "DV> " + ChatColor.GRAY + "The player, " + offPlayer.getName()
							+ ", " + ChatColor.RED + (place == true ? "placed" : "broke") + ChatColor.GRAY
							+ " a block of tnt at " + x + ", " + y + ", " + z + " in the world: " + ChatColor.GREEN
							+ worldName);
				}
			}else
			{
				sender.sendMessage(ChatColor.RED + "Invalid Material!");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Invalid arguments. Correct commands are:");
			sender.sendMessage(ChatColor.GREEN + "/" + command.getName() + " <material> <player_name>");
			sender.sendMessage(ChatColor.GREEN + "/" + command.getName()
					+ " <material> <player_name> <x_filter> <y_filter>" + " <z_filter>");
		}
		return true;
	}

	private void doBreak(Player player, String worldName, int x, int y, int z) {
		// Create location
		Location location = new Location(Bukkit.getServer().getWorld(worldName), x, y, z);
		// Check if the location is not air. If it isn't we can't see the particle
		// anyway
		if (location.getBlock().getType() != Material.AIR) {
			return;
		}
		// Get the config values for the rgb colors. The ints after the strings are just
		// default (incase it isn't set)
		float r = (float) plugin.getConfig().getDouble("red", 0.77);
		float g = (float) plugin.getConfig().getDouble("green", 0d);
		float b = (float) plugin.getConfig().getDouble("blue", 1d);

		// Create packet object (instance)
		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, false,
				(float) x + 0.5f, // Add .5 to coordinates so it will be in the middle of the block
				(float) y + 0.5f, (float) z + 0.5f, r, // R
				g, // G
				b, // B
				1, // Speed of 1
				0, // Notice the count of particles is set to 0. This alerts the packet that it
					// will change the x,y, and z offset
					// values and use them as rgb colors. There can be no particle with a count of
					// zero, so it changes to 1 as well.
				new int[0]); // This data is only for certain particles
		// Loop 10 times so the particle will be placed 10 times. (We can't change
		// particle amount in the packet,
		// because we are using the amount section to tell the packet to use RGB colors.
		for (int i = 0; i < 10; i++) {
			// Send packet
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
	}
}