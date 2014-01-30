package com.gravypod.alladmin.commands;

import java.util.Arrays;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.files.WarpFiles;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class DelWarpCommand extends AllAdminCommand {

	public DelWarpCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}
	@Override
	public void execute(IUser sender, String[] args) {
		
		if (args.length == 1) {
			
			String warp = args[0];
			
			if (!WarpFiles.warpExists(warp)) {
				sender.translate("nowarp", warp);
				return;
			}
			
			WarpFiles.deleteWarp(warp);
			sender.translate("delwarp", warp);
			
		} else {
			sender.send(getCommandUsage(sender));
		}
		
	}
}
