package com.gravypod.alladmin.commands;

import net.minecraft.server.MinecraftServer;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class SpawnCommand extends AllAdminCommand {
	
	public SpawnCommand(CommandPermissions perm, String name, String... alias) {
		super(perm, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {
		sender.teleport(MinecraftServer.getServer().worldServerForDimension(sender.getDimension()).getSpawnPoint());
		sender.translate("senttospawn");
	}
}
