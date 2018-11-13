package me.flail.ChatSwap;

import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("unused")
public class ChatResponder {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	private Chat tools = new Chat();

	public String respond(String message) {

		FileConfiguration config = plugin.getConfig();

		String response = "";

		ConfigurationSection cm = config.getConfigurationSection("ChatMessages");

		Set<String> sections = cm.getKeys(true);

		for (String sec : sections) {

			ConfigurationSection section = cm.getConfigurationSection(sec);

			List<String> words = section.getStringList("Words");

			List<String> responses = section.getStringList("Replacements");

			boolean ignoreSpaces = section.getBoolean("IgnoreSpaces");
			boolean caseSensitive = section.getBoolean("CaseSensitive");
			boolean checkExact = section.getBoolean("CheckExact");

			if (ignoreSpaces) {

				if (caseSensitive) {

					if (checkExact) {

					} else {

					}

				} else {

					if (checkExact) {

					} else {

					}

				}

			} else {

				if (caseSensitive) {

					if (checkExact) {

					} else {

					}

				} else {

					if (checkExact) {

					} else {

					}

				}

			}

		}

		return response;

	}

	private String reply(String message, List<String> words, List<String> responses) {

		String response = "";

		return response;

	}

}
