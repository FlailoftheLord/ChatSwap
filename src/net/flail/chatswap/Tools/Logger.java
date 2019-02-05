package net.flail.chatswap.Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.flail.chatswap.ChatSwap;

public class Logger {

	private ChatSwap plugin;

	private ChatUtils chatUtils;

	public Logger(ChatSwap instance) {
		plugin = instance;
		chatUtils = plugin.chat;
	}

	public enum LogType {
		CONSOLE, FILE, PLAYER, CHAT;
	}

	public LogType fromString(String value) {

		switch (value.toLowerCase().trim()) {

		case "console":
			return LogType.CONSOLE;

		case "chat":
			return LogType.CHAT;

		case "player":
			return LogType.PLAYER;

		case "file":
			return LogType.FILE;

		case "logs":
			return LogType.FILE;

		default:
			return LogType.CONSOLE;

		}

	}

	public boolean log(String log, LogType type) {

		plugin = JavaPlugin.getPlugin(ChatSwap.class);
		chatUtils = plugin.chat;

		switch (type) {

		case CONSOLE:
			plugin.console.sendMessage(chatUtils.chat(log));

			return true;
		case PLAYER:

			return true;
		case CHAT:

			for (Player p : plugin.players) {
				if ((p != null) && p.isOnline() && p.hasPermission("chatswap.notify.logs")) {
					p.sendMessage(chatUtils.chat(log));
				}

			}

			return true;
		case FILE:
			return this.logToFile(log);

		default:
			return false;

		}

	}

	private boolean logToFile(String msg) {

		plugin = JavaPlugin.getPlugin(ChatSwap.class);
		chatUtils = plugin.chat;
		Time time = new Time();

		try {
			// create a temporary file
			String timeLog = time.monthName(Calendar.MONTH) + " "
					+ new SimpleDateFormat("dd_yyyy").format(Calendar.getInstance().getTime()).toString();

			boolean createFile = new File(plugin.getDataFolder() + "/logs").mkdirs();

			if (createFile || (createFile == false)) {

				File logFile = new File(plugin.getDataFolder() + "/logs/" + timeLog + ".txt");

				plugin.logs = new BufferedWriter(new FileWriter(logFile, true));
				plugin.logs.newLine();
				plugin.logs.write(time.currentDayTime() + " " + ChatColor.stripColor(msg));

				// console.sendMessage("Logging worked!");

			}

			return true;
		} catch (Exception e) {

			e.printStackTrace();

			return true;
		} finally {

			try {

				plugin.logs.close();

				return true;
				// console.sendMessage("logs closed");

			} catch (Exception e) {
			}

		}

	}

}
