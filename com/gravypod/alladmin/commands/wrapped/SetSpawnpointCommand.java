package com.gravypod.alladmin.commands.wrapped;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.IWrappedCommand;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

import net.minecraft.command.CommandSetSpawnpoint;
import net.minecraft.command.ICommandSender;

public class SetSpawnpointCommand extends CommandSetSpawnpoint implements IWrappedCommand {
	CommandPermissions permission;
	public SetSpawnpointCommand(CommandPermissions permission) {
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
	public CommandPermissions getPermission() {
		return permission;
	}
	
}
