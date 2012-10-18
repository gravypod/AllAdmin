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
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.listeners.PlayerListener;
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
	private static CommandHandler commandHandler;
	
	/** Our message map, all things from the message.prop that have been used */
	private final static TreeMap<String, String> messages = new TreeMap<String, String>();
	
	@Override
	public void onEnable() {
	
		AllAdmin.instance = this;
		
		AllAdmin.commandHandler = new CommandHandler();
		
		final PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		
		final File userdata = new File(getDataFolder(), "userdata/");
		
		userdata.mkdirs();
		
		new Startup(this, AllAdmin.commandHandler);
		
		final Player[] players = getServer().getOnlinePlayers();
		
		getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
			
				for (final Player player : players) {
					AllAdmin.addUser(player.getName());
				}
				
			}
			
		});
		
		i18n = new I18n();
		
	}
	
	@Override
	public void onDisable() {
	
		new SaveAll(AllAdmin.userList.values());
		
		AllAdmin.userList.clear();
		
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
	
		synchronized(AllAdmin.userList) {
			
			if (!AllAdmin.userList.containsKey(name)) {
				
				final Player p = MatchUser.matchOnlineUser(name);
				
				if (p == null) {
					
					AllAdmin.userList.put(name, new AllAdminCMD(Bukkit.getConsoleSender()));
					
				} else {
					
					AllAdmin.userList.put(name, new AllAdminUser(p));
					
				}
				
			}
			
			return AllAdmin.userList.get(name);
			
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
	
		synchronized(AllAdmin.userList) {
			
			if (AllAdmin.userList.containsKey(name)) {
				return;
			}
			
			final Player p = MatchUser.matchOnlineUser(name);
			
			if (p == null) {
				
				AllAdmin.userList.put(name, new AllAdminCMD(Bukkit.getConsoleSender()));
				return;
				
			}
			
			AllAdmin.userList.put(name, new AllAdminUser(p));
			
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
	
		synchronized(AllAdmin.userList) {
			return AllAdmin.userList;
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
	
		synchronized(AllAdmin.userList) {
			
			if (!AllAdmin.userList.containsKey(name)) {
				return;
			}
			
			AllAdmin.userList.get(name).saveData();
			
			AllAdmin.userList.remove(name);
			
		}
		
	}
	
	/**
	 * 
	 * The main plugin instance.
	 * 
	 * @return
	 * 
	 */
	public static synchronized AllAdmin getInstance() {
	
		return AllAdmin.instance;
		
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
	 * The instance of our command handler
	 * 
	 * @return
	 * 
	 */
	public static final CommandHandler getCommandHandler() {
	
		return AllAdmin.commandHandler;
		
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
	
		if (!AllAdmin.messages.containsKey(messageType)) {
			AllAdmin.messages.put(messageType, AllAdmin.i18n.getColoredMessage(messageType));
		}
		
		return AllAdmin.messages.get(messageType);
		
	}
	
}
