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

	private static final File configFile = new File(AllAdmin.getDataDir(), "config.yml");
	public static String broadcastFormat;
	static {
		load();
	}

	public static void save() throws IOException {
		YamlWriter writer = new YamlWriter(new FileWriter(configFile));
		SerializedConfig config = new SerializedConfig();
		
		config.broadcastFormat = broadcastFormat;
		writer.write(config);
		writer.close();
	}

	public static void load() {
		try {
			YamlReader reader = new YamlReader(new FileReader(configFile));
			SerializedConfig config = reader.read(SerializedConfig.class);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			broadcastFormat = "&e{MESSAGE}";
		}
	}
}
