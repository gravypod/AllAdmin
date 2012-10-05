package com.gravypod.AllAdmin;

import java.io.File;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gravypod.AllAdmin.user.AllAdminCMD;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;
import com.gravypod.AllAdmin.utils.MatchUser;

public class AllAdmin extends JavaPlugin {
	
	private static TreeMap<String, IUser> userList = new TreeMap<String, IUser>();
	
	private static AllAdmin instance;
	
	public CommandHandler ch = new CommandHandler();
	
	@Override
	public void onEnable() {
	
		instance = this;
		
		new Startup(this);
		
	}
	
	@Override
	public void onDisable() {
	
		userList.clear();
		
	}
	
	public void addCommand(String classPath) {
	
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
	
	public static AllAdmin getInstance() {
	
		return instance;
	}
	
	public File jarLocation() {
	
		return new File(this.getFile().getAbsolutePath());
		
	}
	
}
