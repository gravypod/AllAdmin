package com.gravypod.AllAdmin.utils;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;

public class LocationUtil {
	
	private static final HashSet<Byte> dontCount = new HashSet<Byte>();
	
	static {
		dontCount.add((byte)Material.AIR.getId());
		dontCount.add((byte)Material.WATER_LILY.getId());
		dontCount.add((byte)Material.WATER.getId());
		dontCount.add((byte)Material.STATIONARY_WATER.getId());
		dontCount.add((byte)Material.SAPLING.getId());
		dontCount.add((byte)Material.POWERED_RAIL.getId());
		dontCount.add((byte)Material.DETECTOR_RAIL.getId());
		dontCount.add((byte)Material.LONG_GRASS.getId());
		dontCount.add((byte)Material.DEAD_BUSH.getId());
		dontCount.add((byte)Material.YELLOW_FLOWER.getId());
		dontCount.add((byte)Material.RED_ROSE.getId());
		dontCount.add((byte)Material.BROWN_MUSHROOM.getId());
		dontCount.add((byte)Material.RED_MUSHROOM.getId());
		dontCount.add((byte)Material.TORCH.getId());
		dontCount.add((byte)Material.REDSTONE_WIRE.getId());
		dontCount.add((byte)Material.SEEDS.getId());
		dontCount.add((byte)Material.SIGN_POST.getId());
		dontCount.add((byte)Material.WOODEN_DOOR.getId());
		dontCount.add((byte)Material.LADDER.getId());
		dontCount.add((byte)Material.RAILS.getId());
		dontCount.add((byte)Material.WALL_SIGN.getId());
		dontCount.add((byte)Material.LEVER.getId());
		dontCount.add((byte)Material.STONE_PLATE.getId());
		dontCount.add((byte)Material.IRON_DOOR_BLOCK.getId());
		dontCount.add((byte)Material.WOOD_PLATE.getId());
		dontCount.add((byte)Material.REDSTONE_TORCH_OFF.getId());
		dontCount.add((byte)Material.REDSTONE_TORCH_ON.getId());
		dontCount.add((byte)Material.STONE_BUTTON.getId());
		dontCount.add((byte)Material.SUGAR_CANE_BLOCK.getId());
		dontCount.add((byte)Material.DIODE_BLOCK_OFF.getId());
		dontCount.add((byte)Material.DIODE_BLOCK_ON.getId());
		dontCount.add((byte)Material.TRAP_DOOR.getId());
		dontCount.add((byte)Material.PUMPKIN_STEM.getId());
		dontCount.add((byte)Material.MELON_STEM.getId());
		dontCount.add((byte)Material.VINE.getId());
		dontCount.add((byte)Material.NETHER_WARTS.getId());
		System.out.println("Added materials");
	}
	
	public static Location getTarget(final LivingEntity entity) {
	
		final Block block = entity.getTargetBlock(dontCount, 300);
		return block.getLocation();
		
	}
	
	/**
	 * Makes a safe location, only searches up and down.
	 * 
	 * @param locatin
	 * @return Modified location so you are not buried.
	 * 
	 */
	public static Location safeYLocation(final Location locatin) {
		
		final Location blockLocation = locatin;
		double yLoc = blockLocation.getY();
		
		//find the first non air block below us
		while (blockLocation.getBlock().getType() != Material.AIR) {
		    blockLocation.setY(yLoc + 1);
		}
		
		// set to 1 block up so we are not sunk in the ground
		blockLocation.setY(yLoc + 1);
		
		return blockLocation;
		
	}
	
	public static Location safeLocation(final Location location) {
		
		final Location blockLocation = location;
		
		double yLoc = blockLocation.getY();
		
		Block checkedBlock = blockLocation.getBlock();
		
		//find the first non air block below us
		while (checkedBlock.getType() != Material.AIR) {
			
			for (BlockFace bf : BlockFace.values()) {
				
				if (checkedBlock.getRelative(bf).getType() == Material.AIR){
					
					return checkedBlock.getRelative(bf).getLocation();
					
				}
				
			}
			
			blockLocation.setY(yLoc + 1);
			
		}
		
		// set to 1 block up so we are not sunk in the ground
		blockLocation.setY(yLoc + 1);
		
		return blockLocation;
		
	}
	
}
