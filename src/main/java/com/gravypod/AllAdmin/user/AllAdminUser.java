/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.user;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.utils.PermissionsTesting;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class AllAdminUser implements IUser {
    
    private final Player bukkitPlayer;
    
    private Location lastLocation;
    
    private final File userDataFile;

    private final FileConfiguration userData = new YamlConfiguration();

    public AllAdminUser(final Player _bukkitPlayer) {
        
        bukkitPlayer = _bukkitPlayer;
        
        userDataFile = new File(AllAdmin.getInstance().getDataFolder(), "userdata/" + bukkitPlayer.getName() + ".yml");
        
        AllAdmin plugin = AllAdmin.getInstance();
        
        plugin.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            
        	@Override
        	public void run() {
                
        		try {
                    
        			if (userDataFile.exists()) {
        				userDataFile.createNewFile();
        			}
                    
        			userData.load(userDataFile);
                    
        			userData.save(userDataFile);
                    
        		} catch (Exception e) {
        		}
                
        	}
            
        });

    }

    public final String getUserName() {

        return bukkitPlayer.getName();
    }

    public final String getDisplayName() {

        return bukkitPlayer.getDisplayName();
    }

    public final Inventory getInventory() {

        return bukkitPlayer.getInventory();
    }

    public final boolean doesHaveItem(final Material m) {

        return getInventory().contains(m);
    }

    public final boolean doesHaveItem(final ItemStack m) {

        return getInventory().contains(m);
    }

    public final boolean doesHaveItem(final int m) {

        return getInventory().contains(m);
    }

    public void sendCommandFaliure(final String command) {

        bukkitPlayer.sendMessage(ChatColor.RED + "We could not exacute the command " + command + "!");
    }

    public boolean canUseCommand(final String command) {

        return PermissionsTesting.canUseCommand(bukkitPlayer, command);
    }

    public final boolean hasPermission(final String permission) {

        return PermissionsTesting.hasPermission(bukkitPlayer, permission);
    }

    public final Player getBukkitPlayer() {

        return this.bukkitPlayer;
    }

    public final Location getLastLocation() {

        return lastLocation;
    }

    public void setHome(final Location loc) {

        userData.set("homes.world", loc.getWorld().getName());
        userData.set("homes.x", loc.getX());
        userData.set("homes.y", loc.getY());
        userData.set("homes.z", loc.getZ());
        
        try {
            userData.save(userDataFile);
        } catch (IOException e) {
        }

    }

    public final Location getHome() {
    	
    	final World world = AllAdmin.getInstance().getServer().getWorld(userData.getString("homes.world"));
    	final double x = userData.getDouble("homes.x");
    	final double y = userData.getDouble("homes.y");
    	final double z = userData.getDouble("homes.z");
    	
    	if (world == null || Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z))
    		return null;
    	
    	return new Location(world, x, y, z);
        
    }

    public void updateLastLocation() {

        this.lastLocation = this.bukkitPlayer.getLocation();
    }

    @Override
    public void sendMessage(final String message) {

        this.bukkitPlayer.sendMessage(message);

    }

	@Override
    public void saveData() {
		
		try {
			
			userData.save(userDataFile);
            
		} catch (Exception e) {
		}
		
    }
}
