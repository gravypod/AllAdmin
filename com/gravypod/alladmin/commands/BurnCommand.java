package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.Utils;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class BurnCommand extends AllAdminCommand {

	public BurnCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		
		if (args.length == 2) {
			IUser user = AllAdmin.getUser(args[0]);
			if (user == null) {
				sender.translate("playernotfound", args[0]);
				return;
			}
			
			String time = args[1];
			
			if (!Utils.isInteger(time)) {
				sender.translate("notaninteger", time);
				return;
			}
			
			user.setFire(Integer.parseInt(time));
			sender.translate("setfire", args[0]);
			user.translate("fire");
		} else if (args.length == 1) {
			IUser user = AllAdmin.getUser(args[0]);
			if (user == null) {
				sender.translate("playernotfound", args[0]);
				return;
			}
			user.setFire();
			sender.translate("setfire", args[0]);
			user.translate("fire");
		} else {
			sender.send(getCommandUsage(sender));
		}
		
		
	}
	
}
