package com.gravypod.AllAdmin;

import java.io.File;
import java.io.InputStream;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.listeners.*;
import com.gravypod.AllAdmin.user.AllAdminCMD;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;
import com.gravypod.AllAdmin.utils.MatchUser;

public class AllAdmin extends JavaPlugin {
	
	private static TreeMap<String, IUser> userList = new TreeMap<String, IUser>();
	
	private static AllAdmin instance;
	
	private static CommandHandler ch;
	
	@Override
	public void onEnable() {
	
		instance = this;
		
		ch = new CommandHandler();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		
		File userdata = new File(this.getDataFolder(), "userdata/");
		
		userdata.mkdirs();
		
		new Startup(this, ch);
		
	}
	
	@Override
	public void onDisable() {
	
		userList.clear();
		
	}
	
	public static IUser getUser(String name) {
	
		if (!userList.containsKey(name)) {
			
			Player p = MatchUser.matchOnlineUser(name);
			
			if (p == null) {
				userList.put(name, new AllAdminCMD(Bukkit.getConsoleSender()));
				
			} else {
				
				userList.put(name, new AllAdminUser(MatchUser.matchOnlineUser(name)));
				
			}
			
		}
		
		return userList.get(name);
	}
	
	/**
	 * Taken from groupmanager
	 * 
	 * @param fileName
	 * @return
	 */
	public InputStream getResourceAsStream(String fileName) {

		return this.getClassLoader().getResourceAsStream(fileName);
	}
	
	public static TreeMap<String, IUser> getUserList() {
		return userList;
	}
	
	public static void removeUser(String name) {
		userList.remove(name);
	}
	
	public static AllAdmin getInstance() {
	
		return instance;
	}
	
	public File jarLocation() {
	
		return new File(this.getFile().getAbsolutePath());
		
	}

	
    public static CommandHandler getCommandHandler() {
    
    	return ch;
    }

}
