package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;


public class Setwarp implements ICommand {

	@Override
    public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
    
	    plugin.getCommand("setwarp").setExecutor(commandHandler);
	    
    }

	@Override
    public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
    
	    // TODO Auto-generated method stub
	    return true;
    }

	@Override
    public String commandHelp() {
    
	    // TODO Auto-generated method stub
	    return "/setwarp (Name) sets the location you are standing at as a warp";
    }
	
}
