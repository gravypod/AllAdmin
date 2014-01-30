package com.gravypod.alladmin.commands;

import java.util.Arrays;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.files.WarpFiles;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class WarpCommand extends AllAdminCommand {
	
	private final String[] genericStringArray = new String[0];
	
	public WarpCommand(CommandPermissions perm, String name, String ... alias) {
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
			
			SerializedLocation location = WarpFiles.getWarps().get(warp);
			sender.teleport(location);
			sender.translate("warped", warp);
			
		} else {
			sender.translate("warplist", Arrays.toString(WarpFiles.getWarps().keySet().toArray(genericStringArray)));
		}
	}
	
}
