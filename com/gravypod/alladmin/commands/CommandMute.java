package com.gravypod.alladmin.commands;

import net.minecraft.entity.player.EntityPlayerMP;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandMute extends AllAdminCommand {

	public CommandMute(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}
	
	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return index == 0;
	}
	
	@Override
	void execute(IUser sender, String[] args) {
		
		if (args.length != 1) {
			sender.translate("muteusage");
			return;
		}
		
		EntityPlayerMP player = getPlayer(sender.getICommandSender(), args[0]);
		
		if (player == null) {
			sender.translate("playernotfound");
			return;
		}
		
		IUser user = AllAdmin.getUser(player);
		
		user.toggleMute();
		
		
		sender.translate("mutedplayer", user.getUsername());
		user.translate("muted");
	}
	
}
