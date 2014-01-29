package com.gravypod.alladmin.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Permissions;
import java.util.HashMap;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.permissions.Group;
import com.gravypod.alladmin.permissions.PermissionManager;
import static com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions.*;

public class PermissionFiles {
	
	private static SerializedPermissionConfigs permissonConfigs;
	private static final File permissionFile = new File(AllAdmin.getDataDir(), "permissions.yml");
	
	static {
		try {
			YamlReader reader = new YamlReader(new FileReader(permissionFile));
			permissonConfigs = reader.read(SerializedPermissionConfigs.class);
		} catch (FileNotFoundException e) {
			loadDefault();
		} catch (YamlException e) {
			loadDefault();
		}
	}
	public static void save() throws IOException {
		
		SerializedPermissionConfigs perm  = PermissionManager.serialize();
		if (permissionFile.exists() && permissonConfigs.equals(perm)) {
			return;
		}
		YamlWriter writer = new YamlWriter(new FileWriter(permissionFile));
		writer.write(perm);
		writer.close();
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
			HOME.getPermission(),
			SET_HOME.getPermission(),
			CHEST.getPermission(),
			WORKBENCH.getPermission(),
			ENCHANT_TABLE.getPermission(),
			BLOCK_ALL.getPermission(),
			GET_POS.getPermission(),
			WARP.getPermission(),
			SPAWN.getPermission(),
			ME.getPermission(),
			CLEAR_INVENTORY.getPermission(),
			HELP.getPermission(),
		});
		
		Group modGroup = new Group("moderator", new String[] {
			BAN.getPermission(),
			KICK.getPermission(),
			INVSEE.getPermission(),
			CLEAR_INVENTORY_OTHER.getPermission(),
			PARDON.getPermission(),
			SET_WARP.getPermission(),
			WHITELIST.getPermission(),
			TP.getPermission(),
			TP_OTHERS.getPermission(),
			MUTE.getPermission(),
			BURN.getPermission(),
			BROADCAST.getPermission(),
			FLY.getPermission()
		});
		
		modGroup.parent(defaultGroup.getName());
		
		Group adminGroup = new Group("admin", new String[] {
			"*"
		});
		
		adminGroup.parent(modGroup.getName());
		
		permissonConfigs.defaultGroup = defaultGroup.getName();
		
		permissonConfigs.groups = new SerializedGroup[] {
			defaultGroup.serializeGroup(),
			modGroup.serializeGroup(),
			adminGroup.serializeGroup()
				
		};
	}
	
}