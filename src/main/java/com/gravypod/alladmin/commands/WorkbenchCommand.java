package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class WorkbenchCommand extends AllAdminCommand {

	public WorkbenchCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {
		sender.openWorkbench();
	}

}
