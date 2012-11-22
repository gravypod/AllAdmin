/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gravypod.AllAdmin.AllAdmin;

/**
 * 
 * Loads a config file.
 * 
 */
public class ConfigLoad {
	
	private final transient AllAdmin plugin;
	
	private final transient File configFile;
	
	private final transient File warpsList;
	
	public ConfigLoad(final AllAdmin _plugin, final File _warpFile) {
	
		plugin = _plugin;
		configFile = _warpFile;
		warpsList = new File(_plugin.getDataFolder(), "warps.yml");
		
		if (!warpsList.exists()) {
			try {
				warpsList.createNewFile();
			} catch (final IOException e) {
			}
		}
		
		loadData();
		
	}
	
	public void loadData() {
	
		final FileConfiguration ymlConfig = plugin.getConfig();
		
		try {
			
			ymlConfig.load(configFile);
			
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			
			Settings.setConfig(ymlConfig);
			Settings.setUseHomes(ymlConfig.getBoolean(Configuration.USE_HOME.getPath()));
			Settings.setUseWarps(ymlConfig.getBoolean(Configuration.USE_WARPS.getPath()));
			Settings.setUseBack(ymlConfig.getBoolean(Configuration.USE_BACK.getPath()));
			Settings.setUsePermissions(ymlConfig.getBoolean(Configuration.USE_PERMISSIONS.getPath()));
			Settings.setUseWorldEditing(ymlConfig.getBoolean(Configuration.USE_WORLDEDITING.getPath()), ymlConfig.getInt(Configuration.WORLDEDITING_ITEM.getPath()));
			try {
				ymlConfig.save(configFile);
			} catch (final IOException e) {
			}
			
		}
		
		final FileConfiguration warpYamlFile = new YamlConfiguration();
		
		try {
			
			warpYamlFile.load(warpsList);
			
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			
			Settings.setWarpsYamlFile(warpYamlFile);
			Settings.setWarpsList(warpsList);
			
		}
		
		try {
			
			ymlConfig.save(configFile);
			warpYamlFile.save(warpsList);
			
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
