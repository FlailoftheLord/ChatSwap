package me.flail.ChatSwap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Chat {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	public String m(String s, String player, String loc) {

		FileConfiguration config = plugin.getConfig();

		String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));

		return ChatColor.translateAlternateColorCodes('&', s).replace("%prefix%", prefix).replace("%player%", player)
				.replace("%player_location%", loc);

	}

}
