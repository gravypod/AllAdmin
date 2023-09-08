package com.gravypod.alladmin.files;

import java.util.ArrayList;

import com.esotericsoftware.yamlbeans.YamlConfig;

public class AllAdminYMLConfig {
	
	public static void getYMLConfig(YamlConfig config) {
		config.setClassTag("Group", SerializedGroup.class);
		config.setClassTag("Location", SerializedLocation.class);
		config.setClassTag("PermissionConfig", SerializedPermissionConfigs.class);
		config.setClassTag("User", SerializedUser.class);
		config.setClassTag("WarpConfig", SerializedWarpConfig.class);
		config.setClassTag("List", ArrayList.class);
		config.setClassTag("AllAdminConfig", SerializedConfig.class);
	}
}
