package com.gravypod.AllAdmin.permissions;

import java.util.List;

public class Group {
	
	private final boolean isDefault;
	
	private final List<String> permissions;
	
	private final String name;
	
	public Group(String _name, boolean _isDefault, List<String> _permissions) {
	
		name = _name;
		isDefault = _isDefault;
		permissions = _permissions;
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
	
}
