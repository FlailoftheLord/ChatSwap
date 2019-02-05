package net.flail.chatswap.Chat;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.flail.chatswap.ChatSwap;

public class Swapper {

	private ChatSwap plugin = JavaPlugin.getPlugin(ChatSwap.class);

	public String swap(Player player, String message, String configSection) {
		FileConfiguration config = plugin.getConfig();

		boolean randomReplace = config.getBoolean("RandomReplacements", true);

		boolean ignoreCase = config.getBoolean(configSection + ".ignore-case", true);
		boolean ignoreSpaces = config.getBoolean(configSection + ".ignore-spaces", true);
		boolean ignorePunctuation = config.getBoolean(configSection + ".ignore-punctuation", false);

		List<String> messages = config.getStringList(configSection + ".messages");

		for (String m : messages) {

			if (ignoreCase) {
				m = m.toLowerCase();
				message = message.toLowerCase();
			}

			if (ignoreSpaces) {
				m = m.trim().replace(" ", "");
				message = message.trim().replace(" ", "");
			}

			if (ignorePunctuation) {
				m = plugin.chat.simplifyText(m, true);
				message = plugin.chat.simplifyText(message, true);
			}

			if (message.contains(m)) {

				List<String> replacements = config.getStringList(configSection + ".replacements");

				int random = (int) Math.random() * replacements.size();

				if (randomReplace) {
					return plugin.chat.chat(replacements.get(random - 1));

				} else {
					int index = messages.indexOf(m);

					if (index < replacements.size()) {
						return plugin.chat.chat(replacements.get(index));

					} else {
						return plugin.chat.chat(replacements.get(random - 1));

					}

				}

			}

		}

		return message;

	}

}
