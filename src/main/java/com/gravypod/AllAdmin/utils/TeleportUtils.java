/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.user.AllAdminUser;

/**
 * A work in progress utils class for EVERYTHING you could need for teleporting.
 * Needs work
 * 
 * @author gravypod
 * 
 */
public class TeleportUtils {
	
	public static void setLocation(final FileConfiguration db, final String location, final String name, final Location loc) {
	
		AllAdmin.getInstance().getServer().getScheduler().scheduleAsyncDelayedTask(AllAdmin.getInstance(), new Runnable() {
			
			@Override
			public void run() {
			
				db.set(location + "." + name + ".x", loc.getBlockX());
				db.set(location + "." + name + ".y", loc.getBlockY());
				db.set(location + "." + name + ".z", loc.getBlockZ());
				db.set(location + "." + name + ".pitch", loc.getPitch());
				db.set(location + "." + name + ".yaw", loc.getYaw());
				db.set(location + "." + name + ".world", loc.getWorld().getName());
				
			}
			
		});
		
	}
	
	public static Location getLocation(final FileConfiguration db, final String location, final String name) {
	
		final double x = db.getDouble(location + "." + name + ".x");
		final double y = db.getDouble(location + "." + name + ".y");
		final double z = db.getDouble(location + "." + name + ".z");
		final float pitch = (float) db.getDouble(location + "." + name + ".pitch");
		final float yaw = (float) db.getDouble(location + "." + name + ".yaw");
		
		String worldName = db.getString(location + "." + name + ".world");
		
		if (worldName == null) {
			worldName = "world";
		}
		
		final World world = AllAdmin.getInstance().getServer().getWorld(worldName);
		
		if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z) || world == null || Float.isNaN(pitch) || Float.isNaN(yaw)) {
			return null;
		}
		
		return new Location(world, x, y, z, yaw, pitch);
		
	}
	
	public static void delayedTeleport(final AllAdminUser player, final Location toGo, final long time) {
	
		player.sendMessage(ChatColor.AQUA + "Do not move for " + time + " seconds");
		
		player.updateLastLocation();
		
		final Location playersLocation = player.getLastLocation();
		
		AllAdmin.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(AllAdmin.getInstance(), new Runnable() {
			
			@Override
			public void run() {
			
				player.updateLastLocation();
				
				if (playersLocation != player.getLastLocation()) {
					player.sendMessage(ChatColor.RED + "You have not been teleported. You moved");
				}
				
				player.getBukkitPlayer().teleport(TeleportUtils.safeLocation(toGo));
				
				player.sendMessage(ChatColor.RED + "You have been teleported");
				
			}
			
		}, time);
		
	}
	
	public static final Location safeLocation(final Location spawnLocation) {
	
		final Location blockLocation = spawnLocation;
		final double yLoc = blockLocation.getY();
		
		// find the first non air block below us
		while(blockLocation.getBlock().getType() != Material.AIR) {
			blockLocation.setY(yLoc + 1);
		}
		
		// set to 1 block up so we are not sunk in the ground
		blockLocation.setY(yLoc + 1);
		
		return blockLocation;
		
	}
	
}
