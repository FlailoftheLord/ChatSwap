package me.flail.ChatSwap;

import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("unused")
public class ChatResponder {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	private Chat tools = new Chat();

	public String respond(String message) {

		FileConfiguration config = plugin.getConfig();

		String response = "";

		return response;

	}

}
