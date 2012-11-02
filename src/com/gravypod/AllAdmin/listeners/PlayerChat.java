package com.gravypod.AllAdmin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gravypod.AllAdmin.AllAdmin;

/**
 * 
 * Player chat listener
 *
 */
public class PlayerChat implements Listener {
	
	private final char colorCode = (char) 38;;
	
	@EventHandler()
	public void chatEvent(final AsyncPlayerChatEvent event) {
	
		if (AllAdmin.getUser(event.getPlayer().getName()).canColourChat()) {
			event.setMessage(ChatColor.translateAlternateColorCodes(colorCode, event.getMessage()));
		}
		
	}
	
}
