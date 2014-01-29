package com.gravypod.alladmin.files;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;

public class WarpFiles {

	private static final File warpFile = new File(AllAdmin.getDataDir(), "warps.yml");
	private static final HashMap<String, SerializedLocation> warps = new HashMap<String, SerializedLocation>();
	
	static {
		load();
	}
	public static void save() throws IOException {
		SerializedWarpConfig serializedWarpConfig = new SerializedWarpConfig();
		serializedWarpConfig.warps = warps;
		YamlWriter writer = new YamlWriter(new FileWriter(warpFile));
		writer.write(serializedWarpConfig);
		writer.close();
	}
	
	public static void load() {
		try {
			if (warpFile.exists()) {
				YamlReader reader = new YamlReader(new FileReader(warpFile));
				SerializedWarpConfig serializedWarpConfig = reader.read(SerializedWarpConfig.class);
				warps.putAll(serializedWarpConfig.warps);
				reader.close();
			} else {
				save();
			}
		} catch (Exception e) {
			if (warpFile.exists()) { // should never happen if the file exists unless someone edited the file incorrectly
				e.printStackTrace();
			}
		}
	}
	
	public static void createWarp(IUser user, String warpname) {
		SerializedLocation location = user.getLocation();
		warps.put(warpname, location);
	}
	
	public static boolean warpExists(String warpname) {
		return warps.containsKey(warpname);
	}
	
	public void removeWarp(String warpname) {
		warps.remove(warpname);
	}
	
	public static HashMap<String, SerializedLocation> getWarps() {
		return warps;
	}
	
}
