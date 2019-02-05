package net.flail.chatswap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		ChatSwap plugin = JavaPlugin.getPlugin(ChatSwap.class);

		String cmd = command.getName().toLowerCase();

		if (cmd.equals("chatswap")) {

			command.setPermission("chatswap.command");
			command.setPermissionMessage(plugin.chat.chat("%prefix% &cYou don't have permission for that."));

			String usage = plugin.chat.chat("&cProper usage&8: &7/<command> reload &c to reload the config file.")
					.replace("<command>", label);

			command.setUsage(usage);

			String defaultMsg = plugin.chat
					.chat("%prefix% &3ChatSwap version &7" + plugin.version + " &2by FlailoftheLord.");

			switch (args.length) {

			case 0:

				sender.sendMessage(defaultMsg);
				sender.sendMessage(usage);

				return true;
			case 1:
				if (args[0].equalsIgnoreCase("reload")) {
					return plugin.reload(sender);

				} else {
					sender.sendMessage(usage);
				}

				return true;
			default:

				if ((args.length >= 2) && args[0].equalsIgnoreCase("reload")) {
					return plugin.reload(sender);

				} else {
					sender.sendMessage(usage);
				}

			}

		}

		return true;
	}

}
