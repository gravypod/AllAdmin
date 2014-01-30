package com.gravypod.alladmin.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class HealCommand extends AllAdminCommand {

	public HealCommand(CommandPermissions permission, String name, String ... alias) {
		super(permission, name, alias);
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return index == 0;
	}

	@Override
	public void execute(IUser sender, String[] args) {
		
		IUser mp;

		if (args.length == 0) {
			mp = sender;
		} else {
			if (!sender.hasPermission(CommandPermissions.HEAL_OTHERS)) {
				sender.translate("nopermissions");
				return;
			}
			mp = AllAdmin.getUser(args[0]);
			
			if (mp == null) {
				sender.translate("playernotfound", args[0]);
				return;
			}
			
			sender.translate("healeduser", mp.getUsername());
		}

		mp.heal();
		mp.translate("heal");
		
		
	}

}
