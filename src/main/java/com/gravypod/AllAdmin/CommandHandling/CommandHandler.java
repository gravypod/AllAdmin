package com.gravypod.AllAdmin.CommandHandling;

import org.apache.commons.lang.WordUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;

public class CommandHandler implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		
		
		
		try {
			return ((ICommand) Class.forName("com.gravypod.AllAdmin.commands." + WordUtils.capitalize(cmd.toLowerCase())).newInstance()).doCommand(sender, command, cmd, args);
		} catch (Exception e) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
		}
		
		return false;
	}
	
}
