package com.gravypod.AllAdmin.WorldEditing;

import org.bukkit.Location;

public class EditAction {
	
	private Location start;
	
	private Location end;
	
	public void setStart(Location first) {
	
		start = first;
	}
	
	public void setEnd(Location finish) {
	
		end = finish;
		
	}
	
	public boolean isReady() {
	
		return (end != null && start != null) ? true : false;
	}
	
}
