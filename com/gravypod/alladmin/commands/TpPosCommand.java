package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.Utils;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class TpPosCommand extends AllAdminCommand {
	
	
	public TpPosCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		if (args.length == 4) {
			
			if (!sender.hasPermission(CommandPermissions.TP_OTHERS)) {
				sender.translate("nopermissions");
				return;
			}
			
			IUser user = AllAdmin.getUser(args[0]);
			
			if (user == null) {
				sender.translate("playernotfound", args[0]);
				return;
			}
			
			String xArg = args[1];
			String yArg = args[2];
			String zArg = args[3];
			if (attemptTP(sender, user, xArg, yArg, zArg)) {
				sender.translate("teleporteduser", args[0], xArg, yArg, zArg);
				return;
			}
		} else if (args.length == 3) {
			String xArg = args[0];
			String yArg = args[1];
			String zArg = args[2];
			attemptTP(sender, sender, xArg, yArg, zArg);
		} else {
			sender.send(this.getCommandUsage(sender));
		}
	}
	
	public boolean attemptTP(IUser sender, IUser user, String xArg, String yArg, String zArg) {
		if (!Utils.isInteger(xArg)) {
			sender.translate("notaninteger", xArg);
			return false;
		}
		
		if (!Utils.isInteger(yArg)) {
			sender.translate("notaninteger", yArg);
			return false;
		}

		if (!Utils.isInteger(zArg)) {
			sender.translate("notaninteger", zArg);
			return false;
		}
		int x = Integer.parseInt(xArg);
		int y = Integer.parseInt(yArg);
		int z = Integer.parseInt(zArg);
		user.teleport(x, y, z);
		user.translate("teleported", xArg, yArg, zArg);
		return true;
	}
	
}
