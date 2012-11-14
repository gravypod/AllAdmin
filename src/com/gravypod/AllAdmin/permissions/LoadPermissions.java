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
import com.gravypod.AllAdmin.configuration.ConfigHandle;

public class LoadPermissions {
	
	private final AllAdmin plugin = AllAdmin.getInstance();
	
	private final File permissionsFile;
	
	public LoadPermissions() {
	
		final File permissionsFolder = new File(plugin.getDataFolder(), "permissions/");
		
		if (!permissionsFolder.exists()) {
			permissionsFolder.mkdirs();
		}
		
		permissionsFile = new File(plugin.getDataFolder(), "permissions/permissions.yml");
		
		if (!permissionsFile.exists()) {
			try {
				ConfigHandle.copy(plugin.getResourceAsStream("permissions.yml"), permissionsFile);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		
		final FileConfiguration permissionsYaml = new YamlConfiguration();
		
		try {
			permissionsYaml.load(permissionsFile);
		} catch (final Exception e) {
			throw new IllegalStateException("Can't load permissions file!");
		}
		
		final HashMap<String, Group> groups = new HashMap<String, Group>();
		
		final Set<String> groupNames = permissionsYaml.getConfigurationSection("groups").getKeys(false);
		
		for (final String groupName : groupNames) {
			
			final boolean isDefault = permissionsYaml.getBoolean("groups." + groupName + ".default");
			
			final List<String> permissions = permissionsYaml.getStringList("groups." + groupName + ".permissions");
			
			final List<String> flags = permissionsYaml.getStringList("groups." + groupName + ".flags");
			
			final List<String> tempPerms = new ArrayList<String>();
			
			final HashMap<String, Boolean> tempflags = new HashMap<String, Boolean>();
			
			for (final String permission : permissions) {
				tempPerms.add(permission.toLowerCase());
			}
			for (final String flag : flags) {
				String[] tempArray = flag.split(":");
				tempflags.put(tempArray[0], Boolean.parseBoolean(tempArray[1]));
			}
			
			final String tag = permissionsYaml.getString("groups." + groupName + ".tag");
			
			final Group group = new Group(groupName.toLowerCase(), tag, isDefault, tempPerms, tempflags);
			
			permissions.clear();
			tempPerms.clear();
			flags.clear();
			tempflags.clear();
			
			if (isDefault) {
				PermissionData.setDefaultGroup(group);
			}
			
			groups.put(groupName.toLowerCase(), group);
			
		}
		
		PermissionData.setGroups(groups);
		
	}
	
}
