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
		
		LocationUtil.dontCount.add((byte) Material.AIR.getId());
		LocationUtil.dontCount.add((byte) Material.WATER_LILY.getId());
		LocationUtil.dontCount.add((byte) Material.WATER.getId());
		LocationUtil.dontCount.add((byte) Material.STATIONARY_WATER.getId());
		LocationUtil.dontCount.add((byte) Material.SAPLING.getId());
		LocationUtil.dontCount.add((byte) Material.POWERED_RAIL.getId());
		LocationUtil.dontCount.add((byte) Material.DETECTOR_RAIL.getId());
		LocationUtil.dontCount.add((byte) Material.LONG_GRASS.getId());
		LocationUtil.dontCount.add((byte) Material.DEAD_BUSH.getId());
		LocationUtil.dontCount.add((byte) Material.YELLOW_FLOWER.getId());
		LocationUtil.dontCount.add((byte) Material.RED_ROSE.getId());
		LocationUtil.dontCount.add((byte) Material.BROWN_MUSHROOM.getId());
		LocationUtil.dontCount.add((byte) Material.RED_MUSHROOM.getId());
		LocationUtil.dontCount.add((byte) Material.TORCH.getId());
		LocationUtil.dontCount.add((byte) Material.REDSTONE_WIRE.getId());
		LocationUtil.dontCount.add((byte) Material.SEEDS.getId());
		LocationUtil.dontCount.add((byte) Material.SIGN_POST.getId());
		LocationUtil.dontCount.add((byte) Material.WOODEN_DOOR.getId());
		LocationUtil.dontCount.add((byte) Material.LADDER.getId());
		LocationUtil.dontCount.add((byte) Material.RAILS.getId());
		LocationUtil.dontCount.add((byte) Material.WALL_SIGN.getId());
		LocationUtil.dontCount.add((byte) Material.LEVER.getId());
		LocationUtil.dontCount.add((byte) Material.STONE_PLATE.getId());
		LocationUtil.dontCount.add((byte) Material.IRON_DOOR_BLOCK.getId());
		LocationUtil.dontCount.add((byte) Material.WOOD_PLATE.getId());
		LocationUtil.dontCount.add((byte) Material.REDSTONE_TORCH_OFF.getId());
		LocationUtil.dontCount.add((byte) Material.REDSTONE_TORCH_ON.getId());
		LocationUtil.dontCount.add((byte) Material.STONE_BUTTON.getId());
		LocationUtil.dontCount.add((byte) Material.SUGAR_CANE_BLOCK.getId());
		LocationUtil.dontCount.add((byte) Material.DIODE_BLOCK_OFF.getId());
		LocationUtil.dontCount.add((byte) Material.DIODE_BLOCK_ON.getId());
		LocationUtil.dontCount.add((byte) Material.TRAP_DOOR.getId());
		LocationUtil.dontCount.add((byte) Material.PUMPKIN_STEM.getId());
		LocationUtil.dontCount.add((byte) Material.MELON_STEM.getId());
		LocationUtil.dontCount.add((byte) Material.VINE.getId());
		LocationUtil.dontCount.add((byte) Material.NETHER_WARTS.getId());
		
	}
	
	public static Location getTarget(final LivingEntity entity) {
	
		final Block block = entity.getTargetBlock(LocationUtil.dontCount, 300);
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
		final double yLoc = blockLocation.getY();
		
		// find the first non air block below us
		while(blockLocation.getBlock().getType() != Material.AIR) {
			blockLocation.setY(yLoc + 1);
		}
		
		// set to 1 block up so we are not sunk in the ground
		blockLocation.setY(yLoc + 1);
		
		return blockLocation;
		
	}
	
	public static Location safeLocation(final Location location) {
	
		final Location blockLocation = location;
		
		final double yLoc = blockLocation.getY();
		
		final Block checkedBlock = blockLocation.getBlock();
		
		// find the first non air block below us
		while(checkedBlock.getType() != Material.AIR) {
			
			for (final BlockFace bf : BlockFace.values()) {
				
				if (checkedBlock.getRelative(bf).getType() == Material.AIR) {
					
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
