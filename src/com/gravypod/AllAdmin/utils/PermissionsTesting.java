package com.gravypod.AllAdmin.utils;

import org.bukkit.entity.Player;

public class PermissionsTesting {
	
	public static boolean canUseCommand(Player player, String command) {
		return hasPermission(player, "alladmin.commands." + command);
	}
	
	public static boolean hasPermission(Player player, String node) {
		
		return player.hasPermission(node);
		
	}
	
}
