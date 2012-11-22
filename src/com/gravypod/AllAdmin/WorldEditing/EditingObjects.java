package com.gravypod.AllAdmin.WorldEditing;

import java.util.HashMap;

public class EditingObjects {
	
	final private static HashMap<String, EditAction> playerClicks = new HashMap<String, EditAction>();
	
	final private static HashMap<String, Integer> itemIDs = new HashMap<String, Integer>();
	
	final private static ItemI18n idList;
	
	static {
		
		idList = new ItemI18n();
		
	}
	
	public static boolean containsPlayer(String player) {
	
		return playerClicks.containsKey(player);
	}
	
	public static EditAction getPlayer(String player) {
	
		return playerClicks.get(player);
	}
	
	public static void putPlayer(String player, EditAction action) {
	
		if (playerClicks.containsKey(player)) {
			playerClicks.remove(player);
		}
		
		playerClicks.put(player, action);
	}
	
	public static int getIdlist(String name) {
	
		if (!itemIDs.containsKey(name)) {
			
			itemIDs.put(name, idList.getItem(name));
			
		}
		
		return itemIDs.get(name);
	}
	
}
