package com.gravypod.AllAdmin.user;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gravypod.AllAdmin.utils.PermissionsTesting;

public class AllAdminUser implements IUser {
	
	private Player bukkitPlayer;
	
    public AllAdminUser(Player _bukkitPlayer) {
    	
    	bukkitPlayer = _bukkitPlayer;
    	
    }
    
    public String getUserName() {
		return bukkitPlayer.getName();
    }
    
    public String getDisplayName() {
    	return bukkitPlayer.getDisplayName();
    }
    
    public Inventory getInventory() {
    	return bukkitPlayer.getInventory();
    }
    
    public boolean doesHaveItem(Material m) {
    	return getInventory().contains(m);
    }
    
    public boolean doesHaveItem(ItemStack m) {
    	return getInventory().contains(m);
    }
    
    public boolean doesHaveItem(int m) {
    	return getInventory().contains(m);
    }
    
    public void sendCommandFaliure(String command) {
    	bukkitPlayer.sendMessage(ChatColor.RED + "We could not exacute the command " + command + " !");
    }
    
    public boolean canUseCommand(String command) {
    	return PermissionsTesting.canUseCommand(bukkitPlayer, command);
    }
    
    public boolean hasPermission(String permission) {
    	return PermissionsTesting.hasPermission(bukkitPlayer, permission);
    }
    
}
