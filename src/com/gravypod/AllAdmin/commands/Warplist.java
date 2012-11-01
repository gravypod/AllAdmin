package com.gravypod.AllAdmin.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;

public class Warplist extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
	
		plugin.getCommand("warplist").setExecutor(commandHandler);
		
	}
	
	@Override
	public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}
		
		String warps = null;
		final ArrayList<String> warpList = new ArrayList<String>(Settings.warpsYamlFile.getStringList("warps"));
		
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
