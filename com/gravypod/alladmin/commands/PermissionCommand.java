package com.gravypod.alladmin.commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.PermissionFiles;
import com.gravypod.alladmin.permissions.Group;
import com.gravypod.alladmin.permissions.PermissionManager;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;
import com.gravypod.alladmin.user.AllAdminConsole;

public class PermissionCommand extends AllAdminCommand {

	public PermissionCommand(CommandPermissions permission, String name, String... alias) {
		super(permission, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {
		
		String command = args.length >= 1 ? args[0] : "help";
		
		if (command.equalsIgnoreCase("help")) {
			sender.send(getCommandUsage(sender));
			return;
		}
		
		if (args[1].equalsIgnoreCase("help")) {
			sendHelp(sender, command);
			return;
		}
		
		if (args.length == 3) {
			if (command.equalsIgnoreCase("setg")) {
				setg(sender, args);
			} else if (command.equalsIgnoreCase("addp")) {
				addp(sender, args);
			} else if (command.equalsIgnoreCase("remp")) {
				remp(sender, args);
			} else if (command.equalsIgnoreCase("addsub")) {
				addsub(sender, args);
			} else if (command.equalsIgnoreCase("remsub")) {
				remg(sender, args);
			} else {
				sendHelp(sender, command);
			}
		} else if (args.length == 2) {
			if (command.equalsIgnoreCase("addg")) {
				addg(sender, args);
			} else if (command.equalsIgnoreCase("remg")) {
				remg(sender, args);
			} else if (command.equalsIgnoreCase("addsub")) {
				addsub(sender, args);
			} else if (command.equalsIgnoreCase("remsub")) {
				remg(sender, args);
			} else {
				sendHelp(sender, command);
			}
		} else {
			sendHelp(sender, command);
		}
	}
	
	public void remsub(IUser sender, String[] args) {
		String groupName = args[1];
		String subGroup = args[2];
		
		Group group = PermissionManager.getGroup(groupName);
		
		if (group == null) {
			sender.translate("groupnotfound", groupName);
			return;
		}
		
		Group newSubGroup = PermissionManager.getGroup(subGroup);
		
		if (newSubGroup == null) {
			sender.translate("groupnotfound", subGroup);
			return;
		}
		
		if (!PermissionManager.removeSubgroup(group, newSubGroup)) {
			sender.translate("notsubgroup", subGroup, groupName);
			return;
		}
		
		sender.translate("subgroupremoved", subGroup, groupName);
		return;
	}

	public void addsub(IUser sender, String[] args) {
		String groupName = args[1];
		String subGroup = args[2];
		
		Group group = PermissionManager.getGroup(groupName);
		
		if (group == null) {
			sender.translate("groupnotfound", groupName);
			return;
		}
		
		Group newSubGroup = PermissionManager.getGroup(subGroup);
		
		if (newSubGroup == null) {
			sender.translate("groupnotfound", subGroup);
			return;
		}
		
		if (!PermissionManager.addSubgroup(group, newSubGroup)) {
			sender.translate("alreadysubgroup", subGroup, groupName);
			return;
		}
		
		sender.translate("subgroupadded", subGroup, groupName);
		return;
		
		
	}
	
	public void remg(IUser sender, String[] args) {
		String groupName = args[1];
		
		Group group = PermissionManager.getGroup(groupName);
		
		if (group == null) {
			sender.translate("groupnotfound", groupName);
			return;
		}
		
		PermissionManager.deleteGroup(groupName);
		
		sender.translate("groupremoved", groupName);
	}
	
	public void addg(IUser sender, String[] args) {
		
		String groupName = args[1];
		
		Group group = PermissionManager.getGroup(groupName);
		
		if (group != null) {
			sender.translate("groupexisted", groupName);
			return;
		}
		
		PermissionManager.addGroup(groupName);
		
		sender.translate("groupadded", groupName);
		
	}
	
	public void remp(IUser sender, String[] args) {
		String groupName = args[1];
		String permission = args[2];
		
		Group group = PermissionManager.getGroup(groupName);
		
		if (group == null) {
			sender.translate("groupnotfound", groupName);
			return;
		}
		
		if (!group.removePermission(permission)) {
			sender.translate("permissionnotfound", groupName, permission);
			return;
		}
		
		sender.translate("permissionremoved", permission, groupName);
	}
	
	public void addp(IUser sender, String[] args) {
		String groupName = args[1];
		String permission = args[2];
		
		Group group = PermissionManager.getGroup(groupName);
		
		if (group == null) {
			sender.translate("groupnotfound", groupName);
			return;
		}
		
		group.addPermission(permission);
		
		sender.translate("permissionadded", permission, groupName);
	}
	
	public void setg(IUser sender, String[] args) {
		String playerName = args[1];
		String groupName = args[2];
		
		IUser user = AllAdmin.getUser(playerName);
		
		if (user == null) {
			sender.translate("playernotfound", playerName);
			return;
		}
		
		Group group = PermissionManager.getGroup(groupName);
		
		if (group == null) {
			sender.translate("groupnotfound", groupName);
			return;
		}
		
		user.setRank(groupName);
		
		sender.translate("setplayergroup", playerName, groupName);
		user.translate("yourgroupset", groupName);
		
	}
	public void sendHelp(IUser user, String command) {
		String c = command.toLowerCase();
		if (c.equals("addsub") || c.equals("setg") || c.equals("addp") || c.equals("addp") || c.equals("addg") || c.equals("remg")) {
			user.translate(command + "usage");
		} else {
			user.send(getCommandUsage(user));
		}
	}
	
}
