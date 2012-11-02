package com.gravypod.AllAdmin.permissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
			
			boolean isDefault = permissionsYaml.getBoolean("groups." + groupName + ".default");
			
			List<String> permissions = permissionsYaml.getStringList("groups." + groupName + ".permissions");
			
			List<String> tempPerms = new ArrayList<String>();
			
			for (String permission : permissions) {
				tempPerms.add(permission.toLowerCase());
			}
			
			String tag = permissionsYaml.getString("groups." + groupName + ".tag");
			
			Group group = new Group(groupName.toLowerCase(), tag, isDefault, tempPerms);
			
			permissions.clear();
			tempPerms.clear();
			
			if (isDefault) {
				PermissionData.setDefaultGroup(group);
			}
			
			groups.put(groupName.toLowerCase(), group);
			
		}
		
		PermissionData.setGroups(groups);
		
	}
	
}
