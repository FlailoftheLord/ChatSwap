package me.flail.ChatSwap;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatSwap extends JavaPlugin {

	private ConsoleCommandSender console = Bukkit.getConsoleSender();

	@Override
	public void onEnable() {

		// save files
		saveDefaultConfig();

		// Register Events and Commands
		getServer().getPluginManager().registerEvents(new ChatReplacer(), this);

		getCommand("chatswap").setExecutor(new CsCommands());

		// Friendly console spam :>
		String version = getDescription().getVersion();

		console.sendMessage(new Chat().m("&3------====|||====------", "", ""));
		console.sendMessage(new Chat().m(" &aChatSwap &7v" + version, "", ""));
		console.sendMessage(new Chat().m("   &2by FlailoftheLord.", "", ""));
		console.sendMessage(new Chat().m("  &aThe final solution to chat moderation.", "", ""));
		console.sendMessage(new Chat().m("&3------====|||====------", "", ""));

	}

	@Override
	public void onDisable() {

		console.sendMessage(new Chat().m("&3Bye bye!...  &o*slips and falls down stairs*", "", ""));

	}

}
