package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandChest extends AllAdminCommand {

	public CommandChest(CommandPermissions permission, String name, String ... alias) {
		super(permission, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {
		sender.openEnderChest();
	}
	
}
