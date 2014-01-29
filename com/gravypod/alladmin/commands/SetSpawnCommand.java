package com.gravypod.alladmin.commands;

import net.minecraft.server.MinecraftServer;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.Utils;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class SetSpawnCommand extends AllAdminCommand {
	
	public SetSpawnCommand(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {
		
		if (args.length == 0) {
			
			SerializedLocation location = sender.getLocation();
			
			MinecraftServer.getServer().worldServerForDimension(sender.getDimension()).provider.setSpawnPoint((int)location.x, (int)location.y, (int)location.z);
			
			sender.translate("spawnset");
			
		} else if (args.length == 3) {
			
			String xArg = args[0];
			String yArg = args[1];
			String zArg = args[2];
			
			if (!Utils.isInteger(xArg)) {
				sender.translate("notaninteger", xArg);
				return;
			}

			if (!Utils.isInteger(yArg)) {
				sender.translate("notaninteger", yArg);
				return;
			}
			
			if (!Utils.isInteger(zArg)) {
				sender.translate("notaninteger", zArg);
				return;
			}
			
			int x = Integer.parseInt(xArg);
			int y = Integer.parseInt(yArg);
			int z = Integer.parseInt(zArg);
			
			MinecraftServer.getServer().worldServerForDimension(sender.getDimension()).setSpawnLocation(x, y, z);
			
			sender.translate("spawnset");
			
		} else {
			
			sender.send(this.getCommandUsage(sender));
			
		}
		
	}
	
}
