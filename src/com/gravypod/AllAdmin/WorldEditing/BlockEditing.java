package com.gravypod.AllAdmin.WorldEditing;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;

public class BlockEditing {
	
	private final Location start;
	
	private final Location end;
	
	private final Player player;
	
	public BlockEditing(EditAction action) {
		
		player = action.getPlayer();
		
		if (!action.isReady()) {
			
			player.sendMessage(AllAdmin.getMessages("editNotReady"));
			
		}
		
		start = action.getStart();
		end = action.getEnd();
		
	}
	
	public void replaceBlocks(Material replaceWith) {
	
		final World world = start.getWorld();
		
		final int startX = Math.max(start.getBlockX(), end.getBlockX());
		final int startY = Math.max(start.getBlockY(), end.getBlockY());
		final int startZ = Math.max(start.getBlockZ(), end.getBlockZ());
		
		final int endX = Math.min(start.getBlockX(), end.getBlockX());
		final int endY = Math.min(start.getBlockY(), end.getBlockY());
		final int endZ = Math.min(start.getBlockZ(), end.getBlockZ());
		
		for (int x = startX; x < endX; x++) {
			
			for (int y = startY; x < endY; y++) {
				
				for (int z = startZ; x < endZ; z++) {
					
					world.getBlockAt(x, y, z).setType(replaceWith);
					
				}
				
			}
			
		}
		
	}
	
	public void overlayBlocks(Location start, Location end, Material replaceWith) {
	
		final World world = start.getWorld();
		
		final int startX = Math.max(start.getBlockX(), end.getBlockX());
		final int startZ = Math.max(start.getBlockZ(), end.getBlockZ());
		
		final int endX = Math.min(start.getBlockX(), end.getBlockX());
		final int endZ = Math.min(start.getBlockZ(), end.getBlockZ());
		
		for (int x = startX; x < endX; x++) {
			
			for (int z = startZ; x < endZ; z++) {
				
				world.getHighestBlockAt(x, z).setType(replaceWith);
				
			}
			
		}
		
	}
	
	public HashMap<Material, Integer> countBlocks(Location start, Location end) {
	
		HashMap<Material, Integer> blocks = new HashMap<Material, Integer>();
		
		final World world = start.getWorld();
		
		if (world != end.getWorld()) {
			return blocks;
		}
		
		final int startX = Math.max(start.getBlockX(), end.getBlockX());
		final int startY = Math.max(start.getBlockY(), end.getBlockY());
		final int startZ = Math.max(start.getBlockZ(), end.getBlockZ());
		
		final int endX = Math.min(start.getBlockX(), end.getBlockX());
		final int endY = Math.min(start.getBlockY(), end.getBlockY());
		final int endZ = Math.min(start.getBlockZ(), end.getBlockZ());
		
		for (int x = startX; x < endX; x++) {
			
			for (int y = startY; x < endY; y++) {
				
				for (int z = startZ; x < endZ; z++) {
					
					Material currentBlock = world.getBlockAt(x, y, z).getType();
					
					int lastAmmount = blocks.containsKey(currentBlock) ? blocks.get(currentBlock) : 0;
					
					blocks.put(currentBlock, lastAmmount);
					
				}
				
			}
			
		}
		
		return blocks;
		
	}
	
	public String nameBlocks(Location start, Location end) {
	
		return countBlocks(start, end).toString();
		
	}
	
}
