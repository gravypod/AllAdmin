package com.gravypod.alladmin;

import java.util.List;

import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public interface IWrappedCommand {
	CommandPermissions getPermission();
	String getCommandName();
	List getCommandAliases();
	void processCommand(ICommandSender sender, String[] args);
}
