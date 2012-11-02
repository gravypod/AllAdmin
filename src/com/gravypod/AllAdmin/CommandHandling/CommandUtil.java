package com.gravypod.AllAdmin.CommandHandling;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.utils.PermissionsTesting;

public abstract class CommandUtil implements ICommand {
	
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
