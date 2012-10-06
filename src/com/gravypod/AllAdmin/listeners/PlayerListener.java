package com.gravypod.AllAdmin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gravypod.AllAdmin.AllAdmin;

public class PlayerListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public static void onLogin(PlayerLoginEvent event) {
	
		if (event.getPlayer().getName().contains("["))
			return;
		
		AllAdmin.getUser(event.getPlayer().getName());
		
	}
	
	@EventHandler(ignoreCancelled = false)
	public static void onLogout(PlayerQuitEvent event) {
	
		if (event.getPlayer().getName().contains("["))
			return;
		
		AllAdmin.removeUser(event.getPlayer().getName());
		
	}
	
}
