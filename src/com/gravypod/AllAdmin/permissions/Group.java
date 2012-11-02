package com.gravypod.AllAdmin.permissions;

import java.util.List;

/**
 * 
 * Our permissions system group object.
 *
 */
public class Group {
	
	private final boolean isDefault;
	
	private final List<String> permissions;
	
	private final String name;
	
	private final String tag;
	
	public Group(final String _name, final String _tag, final boolean _isDefault, final List<String> _permissions) {
	
		name = _name;
		isDefault = _isDefault;
		permissions = _permissions;
		tag = _tag;
	}
	
	/**
	 * 
	 * Is this group our default group.
	 * 
	 * @return
	 * 
	 */
	public boolean isDefault() {
	
		return isDefault;
	}
	
	/**
	 * 
	 * Gets a list of the groups permissions.
	 * @return
	 * 
	 */
	public List<String> getPermissions() {
	
		return permissions;
		
	}
	
	/**
	 * 
	 * Gets the name of the group
	 * 
	 * @return - Lower case version of the groups definition in YAML
	 * 
	 */
	public String getName() {
	
		return name;
	}
	
	/**
	 * 
	 * Gets any case name of the group
	 * 
	 * @return - what ever is typed in the tag filed
	 * 
	 */
	public String getTag() {
	
		return tag;
	}
	
}
