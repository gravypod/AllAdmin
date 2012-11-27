package com.gravypod.AllAdmin.WorldEditing;

import com.gravypod.AllAdmin.AllAdmin;

public class PrepUsage {
	
	public PrepUsage(int itemId) {
		
		AllAdmin aa = AllAdmin.getInstance();
		aa.getServer().getPluginManager().registerEvents(new EditListener(itemId), aa);
		
	}
	
}
