package com.gravypod.alladmin.commands;

import net.minecraft.entity.player.EntityPlayerMP;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandInvisible extends AllAdminCommand {
	public CommandInvisible(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		
		if (args.length == 0) {
			sender.setInvisible(!sender.isInvisible());
			sender.translate("nowinvis");
		} else {
			EntityPlayerMP player = getPlayer(sender.getICommandSender(), args[0]);
			
			if (player == null) {
				sender.translate("playernotfound");
				return;
			}
			
			IUser user = AllAdmin.getUser(player);
			
			user.setInvisible(!user.isInvisible());
			
			user.translate("nowinvis");
			
			sender.translate("setinvis", user.getUsername());
			
		}
		
	}
}
