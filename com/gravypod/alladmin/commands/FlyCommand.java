package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class FlyCommand extends AllAdminCommand {

	public FlyCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}
	
	@Override
	public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
		return par2 == 1;
	}
	
	@Override
	void execute(IUser sender, String[] args) {
		
		if (args.length == 0) {
			
			sender.toggleFlight();
			
			if (sender.isFlying()) {
				sender.translate("flying");
			} else {
				sender.translate("grounded");
			}
			
		} else if (args.length == 1) {
			if (!sender.hasPermission(CommandPermissions.FLY_OTHERS)) {
				sender.translate("nopermissions");
				return;
			}
			IUser user = AllAdmin.getUser(args[1]);
			
			if (user == null) {
				sender.translate("playernotfound", args[0]);
				return;
			}
			
			user.toggleFlight();
			
			if (user.isFlying()) {
				user.translate("flying");
				sender.translate("setflying", args[0]);
			} else {
				user.translate("grounded");
				sender.translate("setgrounded", args[0]);
			}
		}
	}

}
