package com.gravypod.alladmin.commands.wrapped;

import net.minecraft.command.CommandServerSaveOff;
import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class SaveOffCommand extends CommandServerSaveOff {
	CommandPermissions permission;
	public SaveOffCommand(CommandPermissions permission) {
		this.permission = permission;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1iCommandSender) {
		IUser user = AllAdmin.getUser(par1iCommandSender);
		return user.hasPermission(permission);
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}
	
}
