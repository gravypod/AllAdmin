package com.gravypod.alladmin.commands;

import net.minecraft.entity.player.EntityPlayerMP;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class MuteCommand extends AllAdminCommand {

	public MuteCommand(CommandPermissions perm, String name, String ... alias) {
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
		
		IUser user = AllAdmin.getUser(args[0]);
		if (user == null) {
			sender.translate("playernotfound", args[0]);
			return;
		}
		
		user.toggleMute();
		
		if (user.isMute()) {
			sender.translate("mutedplayer", user.getUsername());
			user.translate("muted");
		} else {
			sender.translate("unmutedplayer", user.getUsername());
			user.translate("unmuted");
		}
	}
	
}
