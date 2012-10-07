package com.gravypod.AllAdmin.utils;

import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;

public class MatchUser {
	
	public static Player matchOnlineUser(String name) {
		
		return AllAdmin.getInstance().getServer().getPlayer(name);
		
	}
	
}
