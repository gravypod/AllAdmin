package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class ClearInventoryCommand extends AllAdminCommand {

	public ClearInventoryCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}
	
	@Override
	public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
		return par2 == 1;
	}
	
	@Override
	public void execute(IUser sender, String[] args) {
		
		if (args.length == 1) {
			
			
			if (!sender.hasPermission(CommandPermissions.CLEAR_INVENTORY_OTHER)) {
				sender.translate("nopermissions");
				return;
			}
			
			IUser user = AllAdmin.getUser(args[0]);
			
			if (user == null) {
				
				sender.translate("playernotfound", args[0]);
				return;
				
			}
			
			user.clearInventory();
			
			sender.translate("invcleared", args[0]);
			
			user.translate("cleared");
			
		} else if (args.length == 0) {
			
			sender.clearInventory();
			sender.translate("cleared");
			
		} else {
			sender.send(getCommandUsage(sender));
		}
	}
	
}
