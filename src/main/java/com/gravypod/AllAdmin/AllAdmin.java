/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin;

import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.listeners.PlayerListener;
import com.gravypod.AllAdmin.user.AllAdminCMD;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;
import com.gravypod.AllAdmin.utils.MatchUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.util.TreeMap;

public class AllAdmin extends JavaPlugin {

    private final static TreeMap<String, IUser> userList = new TreeMap<String, IUser>();
    
    private final static I18N i18n = new I18N();
    
    private static AllAdmin instance;

    private static CommandHandler commandHandler;
    
    private final static TreeMap<String, String> messages = new TreeMap<String, String>();

    @Override
    public void onEnable() {

        instance = this;
        
        commandHandler = new CommandHandler();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);

        final File userdata = new File(this.getDataFolder(), "userdata/");

        userdata.mkdirs();

        new Startup(this, commandHandler);
        
        this.getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
        	
        	@Override
        	public void run() {
        	
        		for (final Player player : getServer().getOnlinePlayers()) {
        			addUser(player.getName());
        		}
        	
        	}
        	
        });
        
    }

    @Override
    public void onDisable() {
    	
    	new SaveAll(userList.values());
    	
        userList.clear();

    }

    public static synchronized final IUser getUser(final String name) {

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
    
    public static synchronized void addUser(final String name) {

    	synchronized (userList) {
    	
    		if (userList.containsKey(name))
    			return;

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

        return this.getClassLoader().getResourceAsStream(fileName);
    }

    public static synchronized final TreeMap<String, IUser> getUserList() {
    	
    	synchronized (userList) {
     	   return userList;
    	}
    	
    }

    public static synchronized void removeUser(String name) {
        userList.remove(name);
    }

    public static synchronized AllAdmin getInstance() {

        return instance;
    }

    public File jarLocation() {

        return new File(this.getFile().getAbsolutePath());

    }


    public static final CommandHandler getCommandHandler() {

        return commandHandler;
    }

	public static String getMessages(String messageType) {
		
		if (!messages.containsKey(messageType)) {
			messages.put(messageType, i18n.getColoredMessage(messageType));
		}
		
	    return messages.get(messageType);
    }

}
