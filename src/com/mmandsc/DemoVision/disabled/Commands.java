package com.mmandsc.DemoVision.disabled;

//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mmandsc.DemoVision.DemoVision;

public class Commands implements CommandExecutor {

	public DemoVision target;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://192.168.1.138/minecraft";

		// Database credentials
		final String USER = "majestic";
		final String PASS = "Characters12?12";

		Connection conn = null;
		Statement stmt = null;
		try {

			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating table in given database...");

			// String sql = "CREATE TABLE REGISTRATION " +
			// "(id INTEGER not NULL, " +
			// " first VARCHAR(255), " +
			// " last VARCHAR(255), " +
			// " age INTEGER, " +
			// " PRIMARY KEY ( id ))";

			// stmt.executeUpdate(sql);

			// create the java mysql update preparedstatement
			Player player = (Player) sender;
			String args1 = args[0];
			String users = "1";
			String Name = player.getDisplayName();
			Statement stmt1 = conn.createStatement();
			String sql = ("SELECT * FROM users where Name='" + args1 + "';");
			ResultSet rs1 = stmt1.executeQuery(sql);
			if (args1.length() == 0){
				sender.sendMessage("LENGTH OF NAME WAS 0, PLEASE TRY TO TYPE A NAME IN");
			} else if (rs1.next()) {
				if (rs1.next()) {
					sender.sendMessage(args1 + " : " + users);
				} else {
					stmt1.executeUpdate("INSERT INTO `users`(Name,users) VALUES ('" + Name + "','" + users
							+ "') ON DUPLICATE KEY UPDATE users = users + 1");
				}
			}
			player.sendMessage("Executed UPDATE");
			// execute the java preparedstatement
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
		return true;
	}
}// end JDBCExample