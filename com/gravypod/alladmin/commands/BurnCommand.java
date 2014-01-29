package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class BurnCommand extends AllAdminCommand {

	public BurnCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		IUser user = AllAdmin.getUser(args[0]);
		if (user == null) {
			sender.translate("playernotfound", args[0]);
			return;
		}
	}
	
}
