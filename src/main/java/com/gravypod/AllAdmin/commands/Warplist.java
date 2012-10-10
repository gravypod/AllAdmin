package com.gravypod.AllAdmin.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;

public class Warplist implements ICommand {
	
	@Override
	public void registerSelf(AllAdmin plugin, CommandHandler commandHandler) {
	
		plugin.getCommand("warplist").setExecutor(commandHandler);
		
	}
	
	@Override
	public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
		
		if (!AllAdmin.getUser(sender.getName()).hasPermission("alladmin.commnads.warplist")) {
			sender.sendMessage("You do not have permissions!");
			return true;
		}
		
		String warps = null;
		ArrayList<String> warpList = new ArrayList<String>(Settings.warpsYamlFile.getStringList("warps"));
		
		for (String warpName : warpList) {
			
    		if (warps == null) {

    			warps = "There are " + warpList.size() + " warps, they are: " + warpName;
    			continue;

    		}

    		warpName += ", " + warpName;
			
		}
		
		sender.sendMessage(ChatColor.AQUA + warps);
		
		return true;
		
	}
	
	@Override
	public String commandHelp() {
		return "/warplist shows a list of warps";
	}
	
}
