package com.mmandsc.DemoVision;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.mmandsc.DemoVision.Listeners.AntiFlight;
import com.mmandsc.DemoVision.Listeners.BlockBreakListener;
import com.mmandsc.DemoVision.Listeners.ClickGui;
import com.mmandsc.DemoVision.Listeners.ClickGuiAccepted;
import com.mmandsc.DemoVision.Listeners.JoinListenerTitle;
import com.mmandsc.DemoVision.commands.CommandDv;
import com.mmandsc.DemoVision.commands.CommandDvCases;
import com.mmandsc.DemoVision.commands.CommandList;
import com.mmandsc.DemoVision.commands.CommandReport;

public class DemoVision extends JavaPlugin{

	private CommandDvCases menu;
	

	@Override
	public void onEnable() {

		getLogger().info("Starting commands api...");
		getLogger().info("Registering Users into the config...");
		getLogger().info("ACTIVATING CASES MENUS");
		getCommand("dv").setExecutor(new CommandDv());
		getCommand("cases").setExecutor(new CommandDvCases());
		getCommand("report").setExecutor(new CommandReport());
		getCommand("list").setExecutor(new CommandList());
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(new JoinListenerTitle(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new ClickGui(), this);
		getServer().getPluginManager().registerEvents(new ClickGuiAccepted(), this);
		getServer().getPluginManager().registerEvents(new AntiFlight(), this);
	}

	@Override
	public void onDisable() {

	}
}