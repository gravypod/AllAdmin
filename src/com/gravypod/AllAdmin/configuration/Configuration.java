package com.gravypod.AllAdmin.configuration;

/**
 * 
 * TODO: All loading done in here
 * 
 */
public enum Configuration {
	
	USE_HOME("AllAdmin.use-homes"), USE_WARPS("AllAdmin.use-warps"), USE_BACK("AllAdmin.use-back"), USE_PERMISSIONS("AllAdmin.use-permissions"), USE_CHAT("AllAdmin.Chat.use-chat");
	
	private final String path;
	
	private Configuration(final String _path) {
	
		path = _path;
	}
	
	public String getPath() {
	
		return path;
		
	}
	
}
