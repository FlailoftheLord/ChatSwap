package net.flail.chatswap.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.flail.chatswap.ChatSwap;
import net.flail.chatswap.Chat.TypeFilter;

public class ChatListener implements Listener {

	private ChatSwap plugin = ChatSwap.getPlugin(ChatSwap.class);

	@EventHandler
	public void playerChat(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();

		String message = event.getMessage();

		if (!event.isAsynchronous()) {
			plugin.recentChat.put(player, Boolean.TRUE);
		}

		if (plugin.recentChat.containsKey(player) && !plugin.recentChat.get(player).booleanValue()) {

			boolean filter = new TypeFilter().chatSwapType(player, message);

			event.setCancelled(filter);

		}

	}

}
