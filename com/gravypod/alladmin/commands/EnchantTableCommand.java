package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class EnchantTableCommand extends AllAdminCommand {

	public EnchantTableCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		sender.openEnchantment();
	}

}
