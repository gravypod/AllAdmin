package com.gravypod.alladmin.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandHeal extends AllAdminCommand {

	public CommandHeal(CommandPermissions permission, String name, String ... alias) {
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
			mp = AllAdmin.getUser(args[0]);
		}

		mp.heal();
		mp.translate("heal");
		
		
	}

}
