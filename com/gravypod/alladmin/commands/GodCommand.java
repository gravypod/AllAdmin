package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class GodCommand extends AllAdminCommand {

	public GodCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		sender.toggleGodMode();
		
		if (sender.hasGodMode()) {
			sender.translate("godon");
		} else {
			sender.translate("godoff");
		}
	}
	
	
	
}
