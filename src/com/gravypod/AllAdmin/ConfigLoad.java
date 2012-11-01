/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gravypod.AllAdmin.listeners.PlayerChat;

public class ConfigLoad {
	
	static AllAdmin plugin;
	
	static FileConfiguration config;
	
	static File configFile;
	
	static File warpsList;
	
	public ConfigLoad(final AllAdmin _plugin, final File _configFile) {
	
		ConfigLoad.plugin = _plugin;
		ConfigLoad.configFile = _configFile;
		ConfigLoad.warpsList = new File(_plugin.getDataFolder(), "warps.yml");
		
		if (!ConfigLoad.warpsList.exists()) {
			try {
				ConfigLoad.warpsList.createNewFile();
			} catch (final IOException e) {
			}
		}
		
		ConfigLoad.loadData();
		
	}
	
	public static void loadData() {
	
		final FileConfiguration ymlConfig = ConfigLoad.plugin.getConfig();
		
		try {
			
			ymlConfig.load(ConfigLoad.configFile);
			
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			
			Settings.useHomes = ymlConfig.getBoolean("AllAdmin.use-homes");
			Settings.useWarps = ymlConfig.getBoolean("AllAdmin.use-warps");
			Settings.useBack = ymlConfig.getBoolean("AllAdmin.use-back");
			Settings.usePermissions = ymlConfig.getBoolean("AllAdmin.use-permissions");
			Settings.config = ymlConfig;
			
			if (ymlConfig.getBoolean("AllAdmin.Chat.use-chat")) {
				plugin.getServer().getPluginManager().registerEvents(new PlayerChat(), plugin);
			}
			
			try {
				ymlConfig.save(ConfigLoad.configFile);
			} catch (final IOException e) {
			}
			
		}
		
		final FileConfiguration warpYamlFile = new YamlConfiguration();
		
		try {
			
			warpYamlFile.load(ConfigLoad.warpsList);
			
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			
			Settings.warpsList = ConfigLoad.warpsList;
			Settings.warpsYamlFile = warpYamlFile;
			
		}
		
		try {
			
			ymlConfig.save(ConfigLoad.configFile);
			warpYamlFile.save(ConfigLoad.warpsList);
			
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
