package com.gravypod.AllAdmin.permissions;

import java.util.HashMap;

public class PermissionData {
	
	private static Group defaultGroup;
	
	private static HashMap<String, Group> groups;
	
	static {
		
		new LoadPermissions();
		
	}
	
	public static Group getDefaultGroup() {
	
		return PermissionData.defaultGroup;
	}
	
	public static void setDefaultGroup(final Group defaultGroup) {
	
		PermissionData.defaultGroup = defaultGroup;
	}
	
	public static HashMap<String, Group> getGroups() {
	
		return PermissionData.groups;
	}
	
	public static void setGroups(final HashMap<String, Group> groups) {
	
		PermissionData.groups = groups;
	}
	
}
