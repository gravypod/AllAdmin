/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.user;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.permissions.Group;
import com.gravypod.AllAdmin.permissions.PermissionData;
import com.gravypod.AllAdmin.utils.PermissionsTesting;
import com.gravypod.AllAdmin.utils.TeleportUtils;

public class OfflineAllAdminUser implements IUser {
	
	private final OfflinePlayer bukkitPlayer;
	
	private Location lastLocation;
	
	private final File userDataFile;
	
	private final FileConfiguration userData = new YamlConfiguration();
	
	private final HashMap<String, Location> userHome = new HashMap<String, Location>();
	
	public OfflineAllAdminUser(final OfflinePlayer _bukkitPlayer) {
	
		bukkitPlayer = _bukkitPlayer;
		
		userDataFile = new File(AllAdmin.getInstance().getDataFolder(), "userdata/" + bukkitPlayer.getName() + ".yml");
		
		final AllAdmin plugin = AllAdmin.getInstance();
		
		BukkitScheduler schedular = plugin.getServer().getScheduler();
		
		schedular.scheduleAsyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
			
				try {
					
					if (!userDataFile.exists()) {
						userDataFile.createNewFile();
					}
					
					userData.load(userDataFile);
					
					userData.save(userDataFile);
					
					synchronized(userHome) {
						
						userHome.put("home", TeleportUtils.getLocation(userData, "homes", "home"));
						
					}
					
				} catch (final Exception e) {
				}
				
			}
			
		});
		
	}
	
	public final String getUserName() {
	
		return bukkitPlayer.getName();
		
	}
	
	@Override
	public void sendCommandFaliure(final String command, final String why) {
	
	}
	
	@Override
	public boolean canUseCommand(final String command) {
	
		return false;
	}
	
	@Override
	public final boolean hasPermission(final String permission) {
	
		return PermissionsTesting.hasPermission(this, permission);
	}
	
	public final OfflinePlayer getBukkitPlayer() {
	
		return bukkitPlayer;
	}
	
	public final Location getLastLocation() {
	
		return lastLocation;
	}
	
	public void setData(final String path, final Object data) {
	
		AllAdmin.getInstance().getServer().getScheduler().scheduleAsyncDelayedTask(AllAdmin.getInstance(), new Runnable() {
			
			@Override
			public void run() {
			
				synchronized(userData) {
					userData.set(path, data);
				}
				
			}
			
		});
		
	}
	
	public void setHome(final Location loc, final String homeName) {
	
		final String name = homeName != null ? homeName : "home";
		
		TeleportUtils.setLocation(userData, "homes", name, loc);
		
		try {
			
			userData.save(userDataFile);
			
		} catch (final IOException e) {
		}
		
	}
	
	public final Location getHome(final String name) {
	
		return TeleportUtils.getLocation(userData, "homes", name != null ? name : "home");
		
	}
	
	@Override
	public void sendMessage(final String message) {
	
	}
	
	@Override
	public void saveData() {
	
		synchronized(userData) {
			
			try {
				
				userData.set("lastLocation.x", lastLocation.getBlockX());
				userData.set("lastLocation.y", lastLocation.getBlockY());
				userData.set("lastLocation.z", lastLocation.getBlockZ());
				
				userData.save(userDataFile);
				
			} catch (final Exception e) {
			}
			
		}
		
	}
	
	@Override
	public boolean isPlayer() {
	
		return true;
		
	}
	
	@Override
	public boolean canColourChat() {
	
		return false;
	}
	
	public Group getGroup() {
	
		if (!userData.contains("permissions.group")) {
			userData.set("permissions.group", PermissionData.getDefaultGroup().getName());
		}
		
		String group = userData.getString("permissioins.group");
		
		if (!PermissionData.getGroups().containsKey(group)) {
			group = PermissionData.getDefaultGroup().getName();
		}
		
		return PermissionData.getGroups().get(group);
		
	}
	
}
