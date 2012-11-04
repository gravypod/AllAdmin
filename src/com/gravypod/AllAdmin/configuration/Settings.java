/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.gravypod.AllAdmin.AllAdmin;

/**
 * 
 * All of the AllAdmin plugin settings.
 * 
 */
public class Settings {
	
	private static File warpsList;
	
	private static boolean useHomes;
	
	private static boolean useWarps;
	
	private static boolean useBack;
	
	private static FileConfiguration warpsYamlFile;
	
	private static FileConfiguration config;
	
	private static boolean usePermissions;
	
	static {
		
		System.out.println("loading config!");
		new ConfigHandle(AllAdmin.getInstance());
	}
	
	public static File getWarpsList() {
	
		return Settings.warpsList;
	}
	
	public static void setWarpsList(final File warpsList) {
	
		Settings.warpsList = warpsList;
	}
	
	public static boolean isUseHomes() {
	
		return Settings.useHomes;
	}
	
	public static void setUseHomes(final boolean useHomes) {
	
		Settings.useHomes = useHomes;
	}
	
	public static boolean isUseWarps() {
	
		return Settings.useWarps;
	}
	
	public static void setUseWarps(final boolean useWarps) {
	
		Settings.useWarps = useWarps;
	}
	
	public static boolean isUseBack() {
	
		return Settings.useBack;
	}
	
	public static void setUseBack(final boolean useBack) {
	
		Settings.useBack = useBack;
	}
	
	public static FileConfiguration getWarpsYamlFile() {
	
		return Settings.warpsYamlFile;
	}
	
	public static void setWarpsYamlFile(final FileConfiguration warpsYamlFile) {
	
		Settings.warpsYamlFile = warpsYamlFile;
	}
	
	public static FileConfiguration getConfig() {
	
		return Settings.config;
	}
	
	public static void setConfig(final FileConfiguration config) {
	
		Settings.config = config;
	}
	
	public static boolean isUsePermissions() {
	
		return Settings.usePermissions;
	}
	
	public static void setUsePermissions(final boolean usePermissions) {
	
		Settings.usePermissions = usePermissions;
	}
	
}
