package com.gravypod.alladmin.commands.wrapped;

import net.minecraft.command.CommandServerEmote;
import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class ServerEmoteCommand extends CommandServerEmote {
	CommandPermissions permission;
	public ServerEmoteCommand(CommandPermissions permission) {
		this.permission = permission;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1iCommandSender) {
		IUser user = AllAdmin.getUser(permission);
		return user.hasPermission(permission);
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
}
