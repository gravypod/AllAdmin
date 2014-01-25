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
			
			sender.setHome();
			
		} else {
			
			sender.setHome(args[0]);
			
		}
		
		sender.translate("homeset");
		
	}
	
}
