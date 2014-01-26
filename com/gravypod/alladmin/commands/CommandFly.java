package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandFly extends AllAdminCommand {

	public CommandFly(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		sender.allowFlight();
		sender.translate("flying");
	}

}
