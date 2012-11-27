package com.gravypod.AllAdmin.WorldEditing;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EditAction {
	
	private Location start;
	
	private Location end;
	
	private final Player player;
	
	public EditAction(Player player) {
	
		this.player = player;
	}
	
	public void setStart(Location first) {
	
		start = first;
	}
	
	public void setEnd(Location finish) {
	
		end = finish;
		
	}
	
	public Location getEnd() {
	
		return end;
	}
	
	public Location getStart() {
	
		return start;
	}
	
	public boolean isReady() {
	
		return (end != null && start != null) && (end.getWorld() == start.getWorld()) ? true : false;
	}
	
	public Player getPlayer() {
	
		return player;
	}
	
}
