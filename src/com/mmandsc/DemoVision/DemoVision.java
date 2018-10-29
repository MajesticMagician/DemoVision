package com.mmandsc.DemoVision;

import com.mmandsc.DemoVision.Listeners.*;
import com.mmandsc.DemoVision.check.AntiFlight;
import com.mmandsc.DemoVision.check.CheckResult;
import com.mmandsc.DemoVision.check.KillAura;
import com.mmandsc.DemoVision.commands.*;
import com.mmandsc.DemoVision.config.utils.ConfigMaker;
import com.mmandsc.DemoVision.config.utils.ConfigManager;
import com.mmandsc.DemoVision.config.utils.User;
import com.mysql.jdbc.Connection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

public class DemoVision extends JavaPlugin{

	private CommandDvCases menu;

    public OutputStream os;

    public DataOutputStream dos;

    public InputStream is;

    public InputStreamReader isr;

	private String host;

	public ConfigMaker cfgm;

    public static Connection connection;

    public String hostm, database, username, password, table;

    public String port;

	public File cfile;

	public static BukkitScheduler bs;

	public static HashMap<UUID, User> USERS = new HashMap<>();

	@Override
	public void onEnable() {

		getLogger().info("Starting commands api...");
		getLogger().info("Registering Users into the config...");
		getLogger().info("ACTIVATING CASES MENUS");
		getCommand("dv").setExecutor(new CommandDv());
		getCommand("dvban").setExecutor(new CommandDvBan());
		getCommand("dvunban").setExecutor(new CommandDvUnBan());
		getCommand("cases").setExecutor(new CommandDvCases());
		getCommand("report").setExecutor(new CommandReport());
		getCommand("list").setExecutor(new CommandList());
		getCommand("MysqlReset").setExecutor(new CommandDvMysqlReset(this));
		getCommand("listsql").setExecutor(new CommandListSql());
		getCommand("listreports").setExecutor(new CommandListReports());
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(new JoinListenerTitle(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new AntiFlight(), this);
		getServer().getPluginManager().registerEvents(new KillAura(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListenerSql(), this);
		getServer().getPluginManager().registerEvents(new MoveListener(), this);
		getServer().getPluginManager().registerEvents(new ClickGuiAccepted(), this);
		loadConfigManager();

		for (Player player : Bukkit.getOnlinePlayers()){
		    USERS.put(player.getUniqueId(), new User(player));
        }
	}

	public void loadConfigManager(){
		cfgm = new ConfigMaker();
		cfgm.setup();
		cfgm.savePlayers();
		cfgm.reloadPlayers();
	}

    @Override
	public void onDisable() {

	}

	public static void log(CheckResult cr, User u){
	    String message = ChatColor.DARK_RED.toString() + ChatColor.BOLD + "DVAC" + " > " + "User " + u.getPlayer().getDisplayName() + " has been susspected of " + cr.getMessage() + ", the level was indicated as " + cr.getLevel();

	    for(Player p : Bukkit.getOnlinePlayers()){
	        if (p.hasPermission("com.demovision.notifier.speed")){
	            p.sendMessage(message);
            }
            Bukkit.getConsoleSender().sendMessage(message);
        }
    }

}