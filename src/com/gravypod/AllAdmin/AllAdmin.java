/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin;

import java.io.File;
import java.io.InputStream;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.listeners.PlayerListener;
import com.gravypod.AllAdmin.permissions.AdminPerms;
import com.gravypod.AllAdmin.permissions.LoadPermissions;
import com.gravypod.AllAdmin.user.AllAdminCMD;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;
import com.gravypod.AllAdmin.utils.MatchUser;

/**
 * 
 * AllAdmin plugin
 * 
 * @author gravypod
 * 
 */
public class AllAdmin extends JavaPlugin {
	
	/** User list */
	private final static TreeMap<String, IUser> userList = new TreeMap<String, IUser>();
	
	/** Translation system instance */
	private static I18n i18n;
	
	/** AllAdmin instance */
	private static AllAdmin instance;
	
	/** Our command handler instance */
	private CommandHandler commandHandler;
	
	/** Our message map, all things from the message.prop that have been used */
	private final static TreeMap<String, String> messages = new TreeMap<String, String>();
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		commandHandler = new CommandHandler();
		
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
		final File userdata = new File(getDataFolder(), "userdata/");
		
		userdata.mkdirs();
		
		new Startup(this, commandHandler);
		
		final Player[] players = getServer().getOnlinePlayers();
		
		getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
			
				for (final Player player : players) {
					AllAdmin.addUser(player.getName());
				}
				
				i18n = new I18n();
				
			}
			
		});
		
		AdminPerms.initialize(this);
		
		new LoadPermissions();
		
	}
	
	@Override
	public void onDisable() {
	
		new SaveAll(AllAdmin.userList.values());
		
		userList.clear();
		
	}
	
	/**
	 * 
	 * Get a user from our user list.
	 * 
	 * @param name
	 *            - user name
	 * @return
	 * 
	 */
	public static synchronized final IUser getUser(final String name) {
	
		synchronized(userList) {
			
			if (!userList.containsKey(name)) {
				
				final Player p = MatchUser.matchOnlineUser(name);
				
				if (p == null) {
					
					userList.put(name, new AllAdminCMD(Bukkit.getConsoleSender()));
					
				} else {
					
					userList.put(name, new AllAdminUser(p));
					
				}
				
			}
			
			return userList.get(name);
			
		}
		
	}
	
	/**
	 * 
	 * Add a user to out user list.
	 * 
	 * @param name
	 * 
	 */
	public static synchronized void addUser(final String name) {
		
		synchronized(userList) {
			
			if (userList.containsKey(name)) {
				return;
			}
			
			final Player p = MatchUser.matchOnlineUser(name);
			
			if (p == null) {
				
				userList.put(name, new AllAdminCMD(Bukkit.getConsoleSender()));
				return;
				
			}
			
			userList.put(name, new AllAdminUser(p));
			
		}
		
	}
	
	/**
	 * Taken from groupmanager
	 * 
	 * @param fileName
	 * @return
	 */
	public final InputStream getResourceAsStream(final String fileName) {
		
		return getClassLoader().getResourceAsStream(fileName);
		
	}
	
	/**
	 * 
	 * Get our userlist
	 * 
	 * @return
	 * 
	 */
	public static synchronized final TreeMap<String, IUser> getUserList() {
		
		synchronized(userList) {
			return userList;
		}
		
	}
	
	/**
	 * 
	 * Remove a use from out user list
	 * 
	 * @param name
	 * 
	 */
	public static synchronized void removeUser(final String name) {
		
		synchronized(userList) {
			
			if (!userList.containsKey(name)) {
				return;
			}
			
			userList.get(name).saveData();
			
			userList.remove(name);
			
		}
		
		AdminPerms.removeAttachment(name);
		
	}
	
	/**
	 * 
	 * The main plugin instance.
	 * 
	 * @return
	 * 
	 */
	public static synchronized AllAdmin getInstance() {
		
		return instance;
		
	}
	
	/**
	 * 
	 * The jar file location
	 * 
	 * @return
	 * 
	 */
	public File jarLocation() {
		
		return new File(getFile().getAbsolutePath());
		
	}
	
	/**
	 * 
	 * Get a message from out I18n file
	 * 
	 * @param messageType
	 *            - Message name
	 * @return
	 * 
	 */
	public static String getMessages(final String messageType) {
		
		if (!messages.containsKey(messageType)) {
			messages.put(messageType, AllAdmin.i18n.getColoredMessage(messageType));
		}
		
		return messages.get(messageType);
		
	}
	
}
