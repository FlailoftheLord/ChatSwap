package net.flail.chatswap.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.flail.chatswap.ChatSwap;

public class PlayerHandler implements Listener {

	private ChatSwap plugin = JavaPlugin.getPlugin(ChatSwap.class);

	@EventHandler
	public void playerJoin(PlayerJoinEvent join) {
		plugin.players.add(join.getPlayer());

	}

	@EventHandler
	public void playerQuit(PlayerQuitEvent quit) {
		plugin.players.remove(quit.getPlayer());

	}

}
