package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandWorkbench extends AllAdminCommand {

	public CommandWorkbench(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {
		sender.openWorkbench();
	}

}
