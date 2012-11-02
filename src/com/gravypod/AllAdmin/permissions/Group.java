package com.gravypod.AllAdmin.permissions;

import java.util.List;

public class Group {
	
	private final boolean isDefault;
	
	private final List<String> permissions;
	
	private final String name;
	
	private final String tag;
	
	public Group(String _name, String _tag, boolean _isDefault, List<String> _permissions) {
	
		name = _name;
		isDefault = _isDefault;
		permissions = _permissions;
		tag = _tag;
	}
	
	public boolean isDefault() {
	
		return isDefault;
	}
	
	public List<String> getPermissions() {
	
		return permissions;
		
	}
	
	public String getName() {
	
		return name;
	}
	
	public String getTag() {
	
		return tag;
	}
	
}
