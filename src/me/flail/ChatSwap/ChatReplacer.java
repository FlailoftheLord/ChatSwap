package me.flail.ChatSwap;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatReplacer implements Listener {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	private ConsoleCommandSender console = Bukkit.getConsoleSender();

	private Chat chat = new Chat();

	@EventHandler
	public void playerChat(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();

		FileConfiguration config = plugin.getConfig();

		String message = event.getMessage();

		boolean ignoreSpaces = config.getBoolean("IgnoreSpaces");

		boolean caseSensitive = config.getBoolean("CaseSensitive");

		boolean checkExact = config.getBoolean("CheckExact");

		for (int word = 0; word <= 1000; word += 1) {

			ConfigurationSection cs = config.getConfigurationSection("WordReplacer." + word);

			if (cs != null) {

				List<String> words = cs.getStringList("Word");

				List<String> replacements = cs.getStringList("Replacement");

				int x = player.getLocation().getBlockX();
				int y = player.getLocation().getBlockY();
				int z = player.getLocation().getBlockZ();

				String pLoc = x + " " + y + " " + z;

				String pName = player.getName();

				for (String w : words) {

					if (checkExact) {

						if (caseSensitive) {

							if (ignoreSpaces) {

								if (message.replace(" ", "").equals(w.replace(" ", ""))) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							} else {

								if (message.equals(w)) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							}

						} else {

							if (ignoreSpaces) {

								if (message.replace(" ", "").equalsIgnoreCase(w.replace(" ", ""))) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							} else {

								if (message.equalsIgnoreCase(w)) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							}

						}

					} else {

						if (caseSensitive) {

							if (ignoreSpaces) {

								if (message.toLowerCase().replace(" ", "").contains(w.toLowerCase().replace(" ", ""))) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							} else {

								if (message.contains(w)) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							}

						} else {

							if (ignoreSpaces) {

								if (message.toLowerCase().replace(" ", "").contains(w.toLowerCase().replace(" ", ""))) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							} else {

								if (message.equalsIgnoreCase(w)) {

									int n = replacements.size();

									int rep = ThreadLocalRandom.current().nextInt(0, n);

									String r = replacements.get(rep);

									if (r != null) {

										event.setMessage(chat.m(r, pName, pLoc));

									} else {
										event.setCancelled(true);
										console.sendMessage("Oof, response selection is broken.");
									}

								}

							}

						}

					}

				}

			}

		}

	}

}
