package com.gravypod.alladmin.commands.wrapped;

import net.minecraft.command.CommandXP;
import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class XPCommand extends CommandXP {
	CommandPermissions permission;
	public XPCommand(CommandPermissions permission) {
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
	
	@Override
	public String getCommandUsage(ICommandSender par1iCommandSender) {
		return super.getCommandUsage(par1iCommandSender);
	}
	
}
