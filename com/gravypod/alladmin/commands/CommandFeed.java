package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandFeed extends AllAdminCommand {

	public CommandFeed(CommandPermissions perm, String name, String... alias) {
		super(perm, name, alias);
	}
	
	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return index == 0;
	}

	@Override
	public void execute(IUser sender, String[] args) {
		if (args.length == 0) {
			sender.feed();
			sender.translate("feed");
		} else {
			IUser user = AllAdmin.getUser(args[0]);
			
			if (user == null) {
				sender.translate("playernotfound");
				return;
				
			}
			
			user.feed();
			sender.translate("feeduser");
			user.translate("feed");
		}
	}
}
