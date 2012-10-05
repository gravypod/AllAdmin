package com.gravypod.AllAdmin.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.gravypod.AllAdmin.AllAdmin;

public class PlayerListener implements Listener {
	
	public static void onLogin(PlayerLoginEvent event) {
	
		if (event.getPlayer().getName().contains("["))
			return;
		
		AllAdmin.getUser(event.getPlayer().getName());
		
	}
	
}
