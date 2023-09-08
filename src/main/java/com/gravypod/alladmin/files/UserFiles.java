package com.gravypod.alladmin.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.permissions.PermissionManager;

public class UserFiles {

	private static final File userDir;
	private static final HashMap<String, SerializedUser> userFiles = new HashMap<String, SerializedUser>();
	static {
		userDir = new File(AllAdmin.getDataDir(), "users");

		if (!userDir.exists() || !userDir.isDirectory()) {
			userDir.mkdirs();
		}
	}
	
	public static SerializedUser loadUser(String name) {
		name = name.toLowerCase();
		if (userFiles.containsKey(name)) {
			return userFiles.get(name);
		}
		
		File userFile = new File(userDir, name + ".yml");
		SerializedUser user = null;
		
		if (userFile.exists()) {
			try {
				YamlReader reader = new YamlReader(new FileReader(userFile));
				AllAdminYMLConfig.getYMLConfig(reader.getConfig());
				user = reader.read(SerializedUser.class);
				reader.close();
			} catch (Exception e) {
				user = blankUser(name);
			}
		} else {
			user = blankUser(name);
		}
		
		if (user == null) {
			user = blankUser(name);
		}
		
		userFiles.put(name, user);
		
		return user;
		
	}
	
	public static void unloadUser(String name, SerializedUser serializedUser) throws IOException {
		name = name.toLowerCase();
		File userFile = new File(userDir, name + ".yml");
		SerializedUser user = userFiles.remove(name);
		
		
		if (user.equals(serializedUser) && userFile.exists()) {
			return;
		}
		
		YamlWriter writer = new YamlWriter(new FileWriter(userFile));
		AllAdminYMLConfig.getYMLConfig(writer.getConfig());
		writer.write(serializedUser);
		writer.close();
		
	}
	
	private static SerializedUser blankUser(String name) {
		SerializedUser user = new SerializedUser();
		user.homes = new HashMap<String, SerializedLocation>();
		user.name = name;
		user.rank = PermissionManager.getDefaultRank();
		return user;
	}
	
	
	public static File getUserdir() {
		return userDir;
	}
	
}
