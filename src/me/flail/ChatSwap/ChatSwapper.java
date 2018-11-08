package me.flail.ChatSwap;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

	public String replacer(String message, List<String> responses, String w) {

		int responseSize = responses.size();

		int randomNumber = ThreadLocalRandom.current().nextInt(0, responseSize);

		String response = responses.get(randomNumber);

		String replacement = message.toLowerCase().replace(w.toLowerCase(), response);

		return replacement;

	}

	public String swap(String message, boolean exact, Player player) {

		FileConfiguration config = plugin.getConfig();

		String replacement = "";

		ConfigurationSection cm = config.getConfigurationSection("ChatMessages");

		Set<String> chatMessages = cm.getKeys(false);

		Iterator<String> sections = chatMessages.iterator();

		while (sections.hasNext()) {

			String nextSection = sections.next();

			ConfigurationSection section = cm.getConfigurationSection(nextSection);

			player.sendMessage(nextSection);

			List<String> words = section.getStringList("Words");

			boolean caseSensitive = section.getBoolean("CaseSensitive");

			boolean ignoreSpaces = section.getBoolean("IgnoreSpaces");

			boolean checkExact = section.getBoolean("CheckExact");

		}

		return replacement;

	}

	public String swapper(String message, List<String> responses) {

		int responseSize = responses.size();

		int randomNumber = ThreadLocalRandom.current().nextInt(0, responseSize);

		String response = responses.get(randomNumber);

		return response;
	}

}
