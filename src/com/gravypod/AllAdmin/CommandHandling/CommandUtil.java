package com.gravypod.AllAdmin.CommandHandling;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.utils.PermissionsTesting;

/**
 * 
 * Command utility class.
 * Extend to be a Command file.
 * 
 */
public abstract class CommandUtil implements ICommand {
	
	/**
	 * 
	 * Checks to see if a someone can use a command
	 * 
	 * @param sender - sender of the command. Currently a CommandSender
	 * @param command - Command as a string
	 * @param checkPlayer - Check to see if the sender is a player.
	 * @param checkPermissions - Check to see if the sender has permissions
	 * @return
	 * 
	 */
	public final boolean canUseCommand(final CommandSender sender, final String command, final boolean checkPlayer, final boolean checkPermissions) {
	
		if (checkPermissions && !PermissionsTesting.canUseCommand(sender, command)) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(command, "noPermissions");
			return false;
		}
		
		if (checkPlayer && !(sender instanceof Player)) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(command, "mustBePlayer");
			return false;
		}
		
		return true;
		
	}
	
}
