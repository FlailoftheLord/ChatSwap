package me.flail.ChatSwap.Listeners;

import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.flail.ChatSwap.Chat;
import me.flail.ChatSwap.ChatResponder;
import me.flail.ChatSwap.ChatSwap;
import me.flail.ChatSwap.ChatSwapper;

public class ChatListener implements Listener {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	private Chat tools = new Chat();

	@EventHandler
	public void playerChat(AsyncPlayerChatEvent event) {

		FileConfiguration config = plugin.getConfig();

		Player player = event.getPlayer();

		String message = event.getMessage();

		ConfigurationSection cm = config.getConfigurationSection("ChatMessages");

		Set<String> chatMessages = cm.getKeys(false);

		boolean checkExact = config.getBoolean("CheckExact");

		for (String m : chatMessages) {

			List<String> words = cm.getStringList(m + ".Words");

			for (String word : words) {

				if (checkExact) {

					if (message.equalsIgnoreCase(word)) {

						String type = cm.getString(m + ".Type");

						switch (type.toLowerCase()) {

						case "replace-exact":
							String chatSwapperExact = new ChatSwapper().swap(message, true, player);

							event.setMessage(tools.chat(chatSwapperExact, player));
							break;

						case "replace-all":
							String chatSwapperAll = new ChatSwapper().swap(message, false, player);

							event.setMessage(tools.chat(chatSwapperAll, player));
							break;

						case "respond":
							String chatResponder = new ChatResponder().respond(message);

							event.setCancelled(true);

							player.sendMessage(tools.chat(chatResponder, player));
							break;

						}

					}

				} else {

					if (message.toLowerCase().contains(word.toLowerCase())) {

						String type = cm.getString(m + ".Type");

						switch (type.toLowerCase()) {

						case "replace-exact":
							String chatSwapperExact = new ChatSwapper().swap(message, true, player);

							event.setMessage(tools.chat(chatSwapperExact, player));
							break;

						case "replace-all":
							String chatSwapperAll = new ChatSwapper().swap(message, false, player);

							event.setMessage(tools.chat(chatSwapperAll, player));
							break;

						case "respond":
							String chatResponder = new ChatResponder().respond(message);

							event.setCancelled(true);

							player.sendMessage(tools.chat(chatResponder, player));
							break;

						}

					}

				}

			}

		}

	}

}
