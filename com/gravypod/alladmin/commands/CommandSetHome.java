package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandSetHome extends AllAdminCommand {

	public CommandSetHome(CommandPermissions permission, String name, String ... alias) {
		super(permission, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		
		if (args.length == 0) {
			
			if (!sender.getHomes().containsKey("default")) {
				sender.translate("nodefaulthome");
				return;
			}
			
			sender.setHome();
			
		} else {
			
			if (!sender.getHomes().containsKey(args[0])) {
				sender.translate("nohome", args[0]);
				return;
			}
			
			sender.setHome(args[0]);
			
		}
		
		sender.translate("homeset");
		
	}
	
}
