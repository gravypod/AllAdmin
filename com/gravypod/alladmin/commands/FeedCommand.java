package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class FeedCommand extends AllAdminCommand {

	public FeedCommand(CommandPermissions perm, String name, String ... alias) {
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
			
			if (!sender.hasPermission(CommandPermissions.FEED_OTHERS)) {
				sender.translate("nopermissions");
				return;
			}
			
			IUser user = AllAdmin.getUser(args[0]);
			
			if (user == null) {
				sender.translate("playernotfound", args[0]);
				return;
			}
			
			user.feed();
			sender.translate("feeduser", user.getUsername());
			user.translate("feed");
			
		}
		
	}
	
}
