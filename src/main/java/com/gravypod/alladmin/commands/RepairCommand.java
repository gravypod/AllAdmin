package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class RepairCommand extends AllAdminCommand {

	public RepairCommand(CommandPermissions perm, String name, String... alias) {
		super(perm, name, alias);
	}
	@Override
	public void execute(IUser sender, String[] args) {
		sender.repairItemInHand();
		sender.translate("repaired");
	}
}
