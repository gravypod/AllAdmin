package com.gravypod.AllAdmin.permissions;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * Our permissions system group object.
 * 
 */
public class Group {
	
	private final boolean isDefault;
	
	private final List<String> permissions;
	
	private final HashMap<String, Object> flags = new HashMap<String, Object>();
	
	private final String name;
	
	private final String tag;
	
	public Group(final String _name, final String _tag, final boolean _isDefault, final List<String> _permissions, final HashMap<String, Object> tempflags) {
	
		name = _name;
		isDefault = _isDefault;
		permissions = _permissions;
		tag = _tag;
		flags.putAll(tempflags);
		
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
	 * 
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
	
	public boolean hasFlag(final String flag) {
	
		return flags.containsKey(flag);
		
	}
	
	public Object getFlag(final String flag) {
	
		return flags.get(flag);
		
	}
	
	public String getFlagString(final String flag) {
	
		if (hasFlag(flag)) {
			return "";
		}
		
		final Object taken = flags.get(flag);
		
		if (!(taken instanceof String)) {
			return "";
		}
		
		return (String) taken;
		
	}
	
	public boolean getFlagBoolean(final String flag) {
	
		if (hasFlag(flag)) {
			return false;
		}
		
		final Object taken = flags.get(flag);
		
		if (!(taken instanceof Boolean)) {
			return false;
		}
		
		return (boolean) taken;
		
	}
	
	public void removeFlag(final String string) {
	
		flags.remove(string);
	}
	
	public void addFlag(final String string, final Object tempValue) {
	
		flags.put(string, tempValue);
		
	}
	
}
