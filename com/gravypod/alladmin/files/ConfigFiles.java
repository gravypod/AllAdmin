package com.gravypod.alladmin.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.gravypod.alladmin.AllAdmin;

public class ConfigFiles {
	private static SerializedConfig serializedConfig;
	private static final File configFile = new File(AllAdmin.getDataDir(), "config.yml");
	public static String broadcastFormat = "&e{MESSAGE}";
	static {
		load();
	}

	public static void save() throws IOException {
		SerializedConfig config = new SerializedConfig();
		config.broadcastFormat = broadcastFormat;
		boolean loadConfig = false;
		if (!(serializedConfig == null)) {
			if (configFile.exists() && serializedConfig.equals(config)) {
				return;
			}
		} else {
			loadConfig = true;
		}
		
		YamlWriter writer = new YamlWriter(new FileWriter(configFile));
		writer.write(config);
		writer.close();
		
		if (loadConfig) {
			load();
		}
		
	}

	public static void load() {

		try {
			YamlReader reader = new YamlReader(new FileReader(configFile));
			serializedConfig = reader.read(SerializedConfig.class);
			reader.close();
		} catch (Exception e) {
			broadcastFormat = "&e{MESSAGE}";
			try {
				save();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
