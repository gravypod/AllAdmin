package com.gravypod.alladmin.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.permissions.Group;
import com.gravypod.alladmin.permissions.Permissions;

public class PermissionFiles {
	
	private static SerializedPermissionConfigs permissonConfigs;
	private static final File permissionFile = new File(AllAdmin.getDataDir(), "permissions.yml");
	
	static {
		load();
	}
	public static void save() throws IOException {
		YamlWriter writer = new YamlWriter(new FileWriter(permissionFile));
		writer.write(permissonConfigs);
		writer.close();
	}
	
	public static void load() {
		try {
			YamlReader reader = new YamlReader(new FileReader(permissionFile));
			permissonConfigs = reader.read(SerializedPermissionConfigs.class);
		} catch (FileNotFoundException e) {
			loadDefault();
		} catch (YamlException e) {
			loadDefault();
		}
	}
	
	public static String getDefaultGroup() {
		
		return permissonConfigs.defaultGroup;
		
	}
	
	public static HashMap<String, Group> getGroups() {
		
		HashMap<String, Group> groups = new HashMap<String, Group>();
		
		for (SerializedGroup g : permissonConfigs.groups) {
			groups.put(g.name, new Group(g));
		}
		
		return groups;
		
	}
	
	private static void loadDefault() {
		permissonConfigs = new SerializedPermissionConfigs();
		
		Group defaultGroup = new Group("default", new String[] {
			Permissions.CommandPermissions.HOME.getPermission(),
			Permissions.CommandPermissions.SET_HOME.getPermission(),
			Permissions.CommandPermissions.CHEST.getPermission(),
			Permissions.CommandPermissions.WORKBENCH.getPermission(),
			Permissions.CommandPermissions.ENCHANT.getPermission(),
		});
		
		Group adminGroup = new Group("admin", new String[] {
			"*"
		});
		
		adminGroup.parent(defaultGroup.getName());
		
		permissonConfigs.defaultGroup = defaultGroup.getName();
		
		permissonConfigs.groups = new SerializedGroup[] {
			defaultGroup.serializeGroup(),
			adminGroup.serializeGroup()
				
		};
	}
	
}