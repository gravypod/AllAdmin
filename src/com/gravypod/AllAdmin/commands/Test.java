package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.ICommand;

public class Test implements ICommand {
	
	@Override
	public void registerSelf(AllAdmin plugin) {
	
		plugin.getCommand("Test").setExecutor(plugin.ch);
		
	}
	
	@Override
	public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
	
		AllAdmin.getUser(sender.getName()).hasPermission("alladmin.commands." + cmd);
		
		sender.sendMessage("Test this is!");
		
		return true;
		
	}

	@Override
    public String commandHelp() {
		
	    return ChatColor.AQUA + "Test command: /test, used for testing";
    }
}
