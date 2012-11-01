package com.gravypod.AllAdmin.permissions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.ConfigHandle;

public class LoadPermissions {
	
	final AllAdmin plugin = AllAdmin.getInstance();
	
	final File permissionsFile;
	
	public LoadPermissions() {
	
		File permissionsFolder = new File(plugin.getDataFolder(), "permissions/");
		
		if (!permissionsFolder.exists()) {
			permissionsFolder.mkdirs();
		}
		
		permissionsFile = new File(plugin.getDataFolder(), "permissions/permissions.yml");
		
		if (!permissionsFile.exists()) {
			try {
				ConfigHandle.copy(plugin.getResourceAsStream("permissions.yml"), permissionsFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		final FileConfiguration permissionsYaml = new YamlConfiguration();
		
		try {
			permissionsYaml.load(permissionsFile);
		} catch (Exception e) {
			throw new IllegalStateException("Can't load permissions file!");
		}
		
		final HashMap<String, Group> groups = new HashMap<String, Group>();
		
		final Set<String> groupNames = permissionsYaml.getConfigurationSection("groups").getKeys(false);
		
		for (String groupName : groupNames) {
			
			System.out.println(groupNames);
			
			boolean isDefault = permissionsYaml.getBoolean("groups." + groupName + ".default");
			
			List<String> permissions = permissionsYaml.getStringList("groups." + groupName + ".permissions");
			
			Group group = new Group(groupName, isDefault, permissions);
			
			if (isDefault) {
				PermissionData.setDefaultGroup(group);
			}
			
			groups.put(groupName, group);
			
		}
		
		PermissionData.setGroups(groups);
		
	}
	
}
