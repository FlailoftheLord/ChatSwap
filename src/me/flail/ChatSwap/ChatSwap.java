/*
 *  Copyright (C) 2018 FlailoftheLord
 *
 *  This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package me.flail.ChatSwap;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.ChatSwap.Listeners.ChatListener;

public class ChatSwap extends JavaPlugin {

	private ConsoleCommandSender console = Bukkit.getConsoleSender();

	@Override
	public void onDisable() {

		Chat tools = new Chat();

		console.sendMessage(tools.chat("&3Bye bye!...  &o*slips and falls down stairs*", null));

	}

	@Override
	public void onEnable() {

		Chat tools = new Chat();

		// save files
		saveDefaultConfig();

		// Register Events and Commands
		console.sendMessage("...");
		getServer().getPluginManager().registerEvents(new ChatListener(), this);

		getCommand("chatswap").setExecutor(new CsCommands());

		// Friendly console spam :>
		String version = getDescription().getVersion();

		console.sendMessage(tools.chat("&3------====|||====------", null));
		console.sendMessage(tools.chat(" &aChatSwap &7v" + version, null));
		console.sendMessage(tools.chat("   &2by FlailoftheLord.", null));
		console.sendMessage(tools.chat("  &aThe final solution to chat moderation.", null));
		console.sendMessage(tools.chat("&3------====|||====------", null));

	}

}
