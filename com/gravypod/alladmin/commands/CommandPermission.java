package com.gravypod.alladmin.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.PermissionFiles;
import com.gravypod.alladmin.permissions.Group;
import com.gravypod.alladmin.permissions.Permissions;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandPermission extends AllAdminCommand {

	public CommandPermission(CommandPermissions permission, String name, String ... alias) {
		super(permission, name, alias);
	}
	
	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		
		if (args.length == 1 && args[0].equalsIgnoreCase("uaddg") && index == 1) {
			return true; // Handle uaddg
		}
		
		return false;
		
	}
	@Override
	public void execute(IUser sender, String[] args) {

		if (!(args.length >= 1)) {
			sender.translate("permissionusage");
			return;
		}

		if (args.length == 3 && args[2].equalsIgnoreCase("help")) {
			
		} else {

			if (args[0].equalsIgnoreCase("save")) {
				
				try {
					PermissionFiles.save();
				} catch (IOException e) {
					e.printStackTrace();
					sender.translate("error", e.toString());
					return;
				}
				sender.translate("permsaved");
				
				// TODO: Reload command
			} else if (args[0].equalsIgnoreCase("gaddp")) {

				if (args.length < 3) {
					sender.translate("permissionusage");
					return; 
				}

				Group group = Permissions.getGroup(args[1]);

				group.addPermission(args[2]);
				
				sender.translate("permadded", args[2], args[1]);
				
			} else if (args[0].equalsIgnoreCase("addg")) {
				
				if (args.length != 2) {
					sender.translate("permissionusage");
					return;
				}
				
				Permissions.addGroup(sender, args[1], args.length > 1 ? Arrays.copyOfRange(args, 1, args.length - 1) : new String[0]);
				
			} else if (args[0].equalsIgnoreCase("uaddg")) {
				if (args.length < 3) {
					sender.translate("permissionusage");
					return;
				}
				AllAdmin.getUser(args[1]).setRank(args[2]);
				sender.translate("permuaddg", args[1], args[2]);
			} 
		}
	}
}
