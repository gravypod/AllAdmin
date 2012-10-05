package com.gravypod.AllAdmin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommand {
	
	public void registerSelf(AllAdmin plugin);
	
	public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args);
	
	public String commandHelp();
	
}
