package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class GetPosCommand extends AllAdminCommand {

	public GetPosCommand(CommandPermissions perm, String name, String... alias) {
		super(perm, name, alias);
	}
	
	@Override
	public void execute(IUser sender, String[] args) {
		SerializedLocation location = sender.getLocation();
		
		sender.translate("currentpos", location.x, location.y, location.z);
	}
	
}
