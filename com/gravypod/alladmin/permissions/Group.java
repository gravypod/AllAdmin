package com.gravypod.alladmin.permissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.gravypod.alladmin.files.SerializedGroup;

public class Group {

	private final String name;
	private final String messageFormat;
	private final List<String> permissions;
	private final List<String> subgroups;
	private HashMap<String, Boolean> permissionCache = new HashMap<String, Boolean>();
	

	public Group(SerializedGroup g) {
		name = g.name;
		messageFormat = g.messageFormat != null ? g.messageFormat : "[&b{GROUP}&f] {USERNAME}: {MESSAGE}";
		permissions = g.permissions != null ? new ArrayList<String>(g.permissions) : new ArrayList<String>();
		subgroups = g.subgroups != null ? new ArrayList<String>(g.subgroups) : new ArrayList<String>();
	}
	
	public Group(String name, String[] permissions) {
		this.name = name;
		this.permissions = Arrays.asList(permissions);
		this.subgroups = new ArrayList<String>();
		this.messageFormat = "[&b{GROUPNAME}&f] {USERNAME}: {MESSAGE}";
	}
	public boolean hasPermission(String s) {
		
		if (permissionCache.containsKey(s)) {
			return permissionCache.get(s);
		}
		
		boolean has = selfHasPermission(s);
		
		if (!has) {
			for (String group : subgroups) {
				Group g = Permissions.getGroup(group);
				if (g != null && g.hasPermission(s)) {
					has = true;
					break;
				}
			}	
		}
		permissionCache.put(s, has);
		return has;
		
	}
	
	private boolean selfHasPermission(String s) {
		
		if (permissions.contains(s)) {
			return true;
		}
		
		String[] parts = s.split("\\.");
		
		StringBuilder builder = new StringBuilder();

		for (String part : parts) {
			
			if (permissions.contains(builder.toString() + "*")) {
				return true;
			}
			
			builder.append(part);

			if (permissions.contains(builder.toString())) {
				return true;
			}

			builder.append(".");


		}

		return false;

	}
	
	public SerializedGroup serializeGroup() {
		SerializedGroup groups = new SerializedGroup();
		groups.name = getName();
		groups.permissions = getPermissions();
		groups.messageFormat = this.messageFormat;
		return groups;
	}
	
	public String getName() {
		return name;
	}
	
	public void addPermission(String permission) {
		permissions.add(permission);
	}
	
	public List<String> getPermissions() {
		return permissions;
	}

	public void parent(String name) {
		subgroups.add(name);
	}
	
	public String getMessageFormat() {
		return messageFormat;
	}

}
