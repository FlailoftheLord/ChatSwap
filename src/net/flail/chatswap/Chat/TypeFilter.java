package net.flail.chatswap.Chat;

import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.flail.chatswap.ChatSwap;

public class TypeFilter {

	private ChatSwap plugin = JavaPlugin.getPlugin(ChatSwap.class);

	public boolean chatSwapType(Player player, String message) {

		FileConfiguration config = plugin.getConfig();

		boolean valid = false;

		Set<String> keys = config.getConfigurationSection("ChatSwap").getKeys(false);

		if (!plugin.recentChat.get(player).booleanValue()) {

			for (String section : keys) {

				List<String> messages = config.getStringList(section + ".messages");

				for (String msg : messages) {

					if (plugin.chat.condensedText(message).contains(plugin.chat.condensedText(msg))) {

						String type = config.get(section + ".type").toString();

						switch (type) {

						case "respond":
							player.chat(new Respond().responder(player, message, section));
							valid = true;
							return valid;
						case "swap":
							player.chat(new Swapper().swap(player, message, section));
							valid = true;
							return valid;
						case "replace":

						case "censor":

						case "bleep":

						}

						valid = true;

					} else {
						valid = false;
					}

				}

			}

		} else {
			plugin.recentChat.remove(player);
			return valid;
		}

		if (!valid) {
			player.chat(message);
			plugin.recentChat.remove(player);
		}

		return valid;
	}

}
