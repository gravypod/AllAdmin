package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;


public class Edit extends CommandUtil {

	@Override
    public void registerSelf(AllAdmin plugin, CommandHandler commandHandler) {
	    plugin.getCommand("edit").setExecutor(commandHandler);
    }

	@Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
    
		canUseCommand(sender, cmd, true, true);
		
		// Use the EditingObjects.getItem to find item names from Item ids
		
		
	    return true;
    }

	@Override
    public String commandHelp() {
	    return "/edit [set | replace] [item(name or ID)] [item to replace with(name or ID)]";
    }
	
}
