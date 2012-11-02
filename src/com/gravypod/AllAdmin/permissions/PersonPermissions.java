package com.gravypod.AllAdmin.permissions;

import java.util.List;

import com.gravypod.AllAdmin.user.AllAdminUser;

public class PersonPermissions {
	
	/**
	 * 
	 * Get all the permissions a user has.
	 * 
	 * @param player
	 * @return
	 * 
	 */
	public static List<String> getPerms(final AllAdminUser player) {
	
		return player.getGroup().getPermissions();
		
	}
	
}
