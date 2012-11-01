package com.gravypod.AllAdmin.permissions;

import java.util.List;

import com.gravypod.AllAdmin.user.AllAdminUser;

public class PersonPermissions {
	
	public static List<String> getPerms(AllAdminUser player) {
	
		return player.getGroup().getPermissions();
		
	}
	
}
