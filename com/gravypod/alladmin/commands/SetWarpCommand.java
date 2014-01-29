package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.WarpFiles;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class SetWarpCommand extends AllAdminCommand {

	public SetWarpCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}
	
	@Override
	void execute(IUser sender, String[] args) {
		if (args.length != 1) {
			sender.send(this.getCommandUsage(sender));
			return;
		}
		
		WarpFiles.createWarp(sender, args[0]);
		sender.translate("createdwarp", args[0]);
	}
	
}
