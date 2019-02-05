package net.flail.chatswap;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.flail.chatswap.Events.ChatListener;
import net.flail.chatswap.Events.PlayerHandler;
import net.flail.chatswap.Tools.ChatUtils;
import net.flail.chatswap.Tools.Logger;
import net.flail.chatswap.Tools.Logger.LogType;

public class ChatSwap extends JavaPlugin {

	public BufferedWriter logs;
	public Server server = this.getServer();
	public ConsoleCommandSender console = server.getConsoleSender();
	public PluginManager pm = server.getPluginManager();
	public Logger logger = new Logger(this);
	public ChatUtils chat = new ChatUtils();

	public List<Player> players = new ArrayList<>();

	public Map<Player, Boolean> recentChat = new HashMap<>();

	public String version = this.getDescription().getVersion();

	@Override
	public void onEnable() {
		LogType c = LogType.CONSOLE;

		saveDefaultConfig();

		this.getCommand("chatswap").setExecutor(new Commands());
		this.registerEvents();

		logger.log(chat.chat("&3------====|||====------"), c);
		logger.log(chat.chat(" &aChatSwap &7v" + version), c);
		logger.log(chat.chat("   &2by FlailoftheLord."), c);
		logger.log(chat.chat("  &aThe final solution to chat moderation."), c);
		logger.log(chat.chat("&3------====|||====------"), c);
	}

	@Override
	public void onDisable() {

		logger.log("&3Bye bye!...  &o*slips and falls down stairs*", LogType.CONSOLE);

	}

	private void registerEvents() {
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new PlayerHandler(), this);
	}

	public boolean reload(CommandSender operator) {

		if (operator != null) {

			this.reloadConfig();

			String reloadMsg = getConfig().get("ReloadMessage").toString();
			operator.sendMessage(chat.chat(reloadMsg));

			return true;
		} else {

			this.reloadConfig();
			return false;
		}

	}

}
