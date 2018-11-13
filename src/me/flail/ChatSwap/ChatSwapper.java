package me.flail.ChatSwap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.netty.util.internal.ThreadLocalRandom;

@SuppressWarnings("unused")
public class ChatSwapper {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	private ConsoleCommandSender console = Bukkit.getConsoleSender();

	private Chat tools = new Chat();

	public String swap(String message, boolean exact, Player player) {

		FileConfiguration config = plugin.getConfig();

		String replacement = "";

		ConfigurationSection cm = config.getConfigurationSection("ChatMessages");

		Set<String> chatMessages = cm.getKeys(false);

		Iterator<String> sections = chatMessages.iterator();

		while (sections.hasNext()) {

			String nextSection = sections.next();

			ConfigurationSection section = cm.getConfigurationSection(nextSection);

			List<String> words = section.getStringList("Words");

			List<String> replacements = section.getStringList("Replacements");

			boolean caseSensitive = section.getBoolean("CaseSensitive");

			boolean ignoreSpaces = section.getBoolean("IgnoreSpaces");

			boolean checkExact = section.getBoolean("CheckExact");

			if (exact) {

				if (ignoreSpaces) {

					String noSpaceMessage = message.replace(" ", "");

					List<String> noSpaceWords = new ArrayList<>();

					for (String word : words) {
						word.replaceAll(" ", "");
						noSpaceWords.add(word);
					}

					if (caseSensitive) {

						if (checkExact) {

							for (String word : noSpaceWords) {

								if (noSpaceMessage.equals(word)) {

									replacement = replaceExact(message, replacements, word);

								}

							}

						} else {

							for (String word : noSpaceWords) {

								if (noSpaceMessage.contains(word)) {

									replacement = replaceExact(message, replacements, word);

								}

							}

						}

					} else {

						if (checkExact) {

							for (String word : noSpaceWords) {

								if (noSpaceMessage.equalsIgnoreCase(word)) {

									replacement = replaceExact(message, replacements, word);

								}

							}

						} else {

							for (String word : noSpaceWords) {

								if (noSpaceMessage.toLowerCase().contains(word.toLowerCase())) {

									replacement = replaceExact(message, replacements, word);

								}

							}

						}

					}

				} else {

				}

			}

		}

		return replacement;

	}

	public String replaceExact(String message, List<String> responses, String w) {

		int responseSize = responses.size();

		int randomNumber = ThreadLocalRandom.current().nextInt(0, responseSize);

		String response = responses.get(randomNumber);

		String replacement = message.replaceAll("(?i)" + Pattern.quote(w), response);

		return replacement;

	}

	public String repalceAll(String message, List<String> responses) {

		int responseSize = responses.size();

		int randomNumber = ThreadLocalRandom.current().nextInt(0, responseSize);

		String response = responses.get(randomNumber);

		return response;
	}

}
