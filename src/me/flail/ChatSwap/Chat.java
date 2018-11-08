package me.flail.ChatSwap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Chat {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	public String chat(String s, Player player) {

		FileConfiguration config = plugin.getConfig();

		/*
		 * lol, for some reason i put this here... then realized i didn't need it...
		 * String colorCode = "§";
		 */

		String message = null;

		try {

			String pName = player.getName();

			int x = player.getLocation().getBlockX();
			int y = player.getLocation().getBlockY();
			int z = player.getLocation().getBlockZ();

			String pLoc = x + " " + y + " " + z;

			String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));

			message = ChatColor.translateAlternateColorCodes('&', s).replace("%prefix%", prefix)
					.replace("%player%", pName).replace("%player_location%", pLoc);

		} catch (NullPointerException e) {

			String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));

			message = ChatColor.translateAlternateColorCodes('&', s.replace("%prefix%", prefix));

		}

		return message;

	}

}
