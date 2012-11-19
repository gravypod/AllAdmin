package com.gravypod.AllAdmin.storage;

import com.gravypod.AllAdmin.AllAdmin;

public class InitStorage {
	
	final static transient AllAdmin plugin;
	
	static {
		plugin = AllAdmin.getInstance();
	}
	
	public static void init() {
	
		InitStorage.plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(InitStorage.plugin, new StorageThread(), 1L, 10000L);
		
	}
	
}
