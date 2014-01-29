package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class InvseeCommand extends AllAdminCommand {
	
	public InvseeCommand(CommandPermissions perm, String name, String... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		
		if (args.length != 1) {
			sender.send(this.getCommandUsage(sender));
			return;
		}
		
		IUser user = AllAdmin.getUser(args[0]);
		
		if (user == null) {
			sender.translate("playernotfound", args[0]);
			return;
		}
		
		sender.showInventory(user);
		
	}
	
}
