package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class SetHomeCommand extends AllAdminCommand {

	public SetHomeCommand(CommandPermissions permission, String name, String ... alias) {
		super(permission, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {
		
		if (args.length == 0) {
			
			sender.setHome();
			
		} else {
			
			
			if (!sender.hasPermission(CommandPermissions.MULTI_SET_HOME)) {
				sender.translate("nomultihomeperms");
				return;
			}
			sender.setHome(args[0]);
			
		}
		
		sender.translate("homeset");
		
	}
	
}
