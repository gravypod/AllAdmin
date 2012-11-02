package com.gravypod.AllAdmin.permissions;

import java.util.List;

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
