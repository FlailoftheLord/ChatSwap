package me.flail.ChatSwap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CsCommands implements CommandExecutor {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	private ConsoleCommandSender console = Bukkit.getConsoleSender();

	private Chat chat = new Chat();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		String cmd = command.getName().toLowerCase();

		if (cmd.equals("chatswap")) {

			FileConfiguration config = plugin.getConfig();

			String version = plugin.getDescription().getVersion();

			String defaultMessage = chat.m("&3ChatSwap &7v" + version + " &2by FlailoftheLord.", "", "");

			if (sender instanceof Player) {

				Player player = (Player) sender;

				String reloadMessage = config.getString("ReloadMessage");

				if (player.hasPermission("chatswap.command")) {

					if (args.length == 1) {

						if (args[0].equalsIgnoreCase("reload")) {

							plugin.reloadConfig();

							console.sendMessage(chat.m("&aChatSwap config reloaded!", "", ""));

							player.sendMessage(chat.m(reloadMessage, "", ""));

						} else {

							player.sendMessage(defaultMessage);

						}

					} else {

						player.sendMessage(defaultMessage);

					}

				} else {

					player.sendMessage(defaultMessage);

				}

			} else {

				if (args.length == 1) {

					if (args[0].equalsIgnoreCase("reload")) {

						plugin.reloadConfig();

						console.sendMessage(chat.m("&aChatSwap config reloaded!", "", ""));

					} else {

						console.sendMessage(defaultMessage);

					}

				} else {

					console.sendMessage(defaultMessage);

				}

			}

		}

		return true;
	}

}
