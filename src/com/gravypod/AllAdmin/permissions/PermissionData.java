package com.gravypod.AllAdmin.permissions;

import java.util.HashMap;
import java.util.Map;

public class PermissionData {
	
	private static Group defaultGroup;
	
	private static HashMap<String, Group> groups;
	
	static {
		
		new LoadPermissions();
		
	}
	
	/**
	 * 
	 * Get the default group to set a user to.
	 * 
	 * @return
	 * 
	 */
	public static Group getDefaultGroup() {
	
		return PermissionData.defaultGroup;
	}
	
	/**
	 * 
	 * Set the default group to set a user to. [On first connect]
	 * 
	 * @param defaultGroup
	 * 
	 */
	public static void setDefaultGroup(final Group defaultGroup) {
	
		PermissionData.defaultGroup = defaultGroup;
	}
	
	/**
	 * 
	 * Get a HashMap of groups we know of
	 * 
	 * @return
	 * 
	 */
	public static HashMap<String, Group> getGroups() {
	
		return PermissionData.groups;
	}
	
	/**
	 * 
	 * Set a HashMap of groups we know of
	 * 
	 */
	public static void setGroups(final HashMap<String, Group> groups) {
	
		PermissionData.groups = groups;
	}
	
	/**
	 * 
	 * Add to the groups we know of
	 * 
	 */
	public static void addGroups(final Map<String, Group> groups) {
	
		if (PermissionData.groups == null) {
			PermissionData.groups = new HashMap<String, Group>();
		}
		
		PermissionData.groups.putAll(groups);
		
	}
	
}
