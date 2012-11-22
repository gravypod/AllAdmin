package com.gravypod.AllAdmin.WorldEditing;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class BlockEditing {
	
	public void replaceBlocks(Location start, Location end, Material replaceWith) {
	
		final int startX = Math.max(start.getBlockX(), end.getBlockX());
		final int startY = Math.max(start.getBlockY(), end.getBlockY());
		final int startZ = Math.max(start.getBlockZ(), end.getBlockZ());
		
		final int endX = Math.min(start.getBlockX(), end.getBlockX());
		final int endY = Math.min(start.getBlockY(), end.getBlockY());
		final int endZ = Math.min(start.getBlockZ(), end.getBlockZ());
		
		final World world = start.getWorld();
		
		for (int x = startX; x < endX; x++) {
			
			for (int y = startY; x < endY; y++) {
				
				for (int z = startZ; x < endZ; z++) {
					
					world.getBlockAt(x, y, z).setType(replaceWith);
					
				}
				
			}
			
		}
		
	}
	
}
