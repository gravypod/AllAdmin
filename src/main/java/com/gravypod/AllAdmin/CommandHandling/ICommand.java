package com.gravypod.AllAdmin.CommandHandling;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;

public interface ICommand {
	
	public void registerSelf(AllAdmin plugin, CommandHandler ch);
	
	public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args);
	
	public String commandHelp();
	
}
