package com.gravypod.AllAdmin.WorldEditing;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gravypod.AllAdmin.AllAdmin;

public class EditListener implements Listener {
	
	final int itemID;
	
	public EditListener(int itemID) {
	
		this.itemID = itemID;
	}
	
	@EventHandler()
	public void interactEvent(PlayerInteractEvent event) {
	
		int item = event.getPlayer().getItemInHand().getTypeId();
		
		if (!(itemID == item)) {
			return;
		}
		
		final Player player = event.getPlayer();
		
		final String playerName = player.getName();
		
		final EditAction currentAction;
		
		if (EditingObjects.containsPlayer(playerName)) {
			currentAction = EditingObjects.getPlayer(playerName);
		} else {
			currentAction = new EditAction(player);
		}
		
		switch(event.getAction()) {
			case RIGHT_CLICK_BLOCK:
				currentAction.setStart(event.getClickedBlock().getLocation());
				player.sendMessage(AllAdmin.getMessages("rightClickEdit"));
				break;
			case LEFT_CLICK_BLOCK:
				currentAction.setEnd(event.getClickedBlock().getLocation());
				player.sendMessage(AllAdmin.getMessages("leftClickEdit"));
				break;
		}
		
		if (currentAction.isReady()) {
			player.sendMessage(AllAdmin.getMessages("editReady"));
		}
		
		EditingObjects.putPlayer(playerName, currentAction);
		
	}
	
}
