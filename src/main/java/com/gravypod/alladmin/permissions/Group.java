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
		
		boolean has = false;
		
		for (String group : subgroups) {
			Group g = PermissionManager.getGroup(group);
			if (g != null && g.hasPermission(s)) {
				has = true;
			}
		}
		
		if (!has) {
			has = selfHasPermission(s);
			
			
		}
		
		if (!has && selfHasPermission("-" + s)) {
			has = false;
		}
		
		permissionCache.put(s, has);
		return has;
		
	}
	
	private boolean selfHasPermission(String s) {// From group manager, taken with permission from elgarl
		
		String name = s.toLowerCase().toLowerCase();

        if (permissions.contains(name)) {
            return true;
        }

        final String[] parts = name.split("\\.");
        final StringBuilder builder = new StringBuilder(name.length());
        
        for (String part : parts) {
        	
            builder.append('*');

            if (permissions.contains(builder.toString())) {
                return true;
            }
            
            builder.deleteCharAt(builder.length() - 1);
            builder.append(part).append('.');
        }
        
        return false;
	}
	
	public SerializedGroup serializeGroup() {
		SerializedGroup groups = new SerializedGroup();
		groups.name = getName();
		groups.permissions = getPermissions();
		groups.subgroups = this.subgroups;
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
	public List<String> getSubgroups() {
		return subgroups;
	}

	public boolean removePermission(String permission) {
		return permissions.remove(permission);
	}
}
