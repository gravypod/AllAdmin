package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class ChestCommand extends AllAdminCommand {

	public ChestCommand(CommandPermissions permission, String name, String ... alias) {
		super(permission, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {
		sender.openEnderChest();
	}
	
}
