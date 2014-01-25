package com.gravypod.alladmin;

import java.util.HashMap;

import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.permissions.Group;

public interface IUser {
	
	/**
	 * Check to see if a user has the permission node specified
	 * @param permission - Permission node
	 * @return - True of the use does have the permission node, false otherwise
	 */
	public boolean hasPermission(String permission);
	
	/**
	 * Set the rank of the user
	 * @param rank - Rank for the user to be set to. If this is not found within the groups list no change is made
	 */
	public void setRank(String rank);
	
	/**
	 * Get the rank the player is
	 * @return {@link Group} the users is in
	 */
	public Group getRank();
	
	/**
	 * Heal the player to max health
	 */
	public void heal();
	
	/**
	 * Set the health of the player
	 * @param h - Health value
	 */
	public void setHealth(int h);
	
	/**
	 * Send a message to the player
	 * @param message
	 */
	public void send(String message);
	
	/**
	 * Load a message from {@link I18n} and send it to the users.
	 * @param key - Key for the message you are looking for
	 * @param args - Data to replace strings within the message
	 */
	public void translate(String key, Object ... args);
	
	/**
	 * Fire a logout event for a player, this saves the user data collected
	 */
	public void logout();
	
	/**
	 * Teleport a user without setting pitch/yaw
	 * @param x - X coord
	 * @param y - Y coord
	 * @param z - Z coord
	 */
	public void teleport(int x, int y, int z);
	
	/**
	 * Teleport a user with setting a pitch and yaw
	 * @param x
	 * @param y
	 * @param z
	 * @param pitch
	 * @param yaw
	 */
	public void teleport(int x, int y, int z, float pitch, float yaw);
	
	/**
	 * Set a home and specify its name
	 * @param name
	 */
	public void setHome(String name);
	
	/**
	 * Set the default home for the player
	 */
	public void setHome();
	
	/**
	 * Send a user to a home and specify its name
	 * @param name
	 */
	public void sendHome(String name);
	
	/**
	 * Send a user to their default home
	 */
	public void sendHome();
	
	/**
	 * Get all the homes the user has
	 * @return
	 */
	public HashMap<String, SerializedLocation> getHomes();
	
	/**
	 * Feed a user until they are not hungry
	 */
	public void feed();
	
	/**
	 * Feed a user the specified amount of food
	 * @param food - amount of food to feed
	 */
	public void feed(int food);
	
	/**
	 * Open the user's ender chest GUI
	 */
	public void openEnderChest();
	
	/**
	 * Open a workbench GUI
	 */
	public void openWorkbench();
	
	/**
	 * Toggle the ability for this user to send chat messages
	 */
	public void toggleMute();
	
	/**
	 * Find if this user can send chat messages
	 */
	public boolean isMute();
	
	public void openEnchantment();

	public ICommandSender getICommandSender();
	
	
}
