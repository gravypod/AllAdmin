package com.gravypod.alladmin;

import java.util.Arrays;
import java.util.List;

import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public abstract class AllAdminCommand extends CommandBase implements ICommand, Comparable {

	private final String name;
	private final String[] alias;
	private final String permission;
	
	public AllAdminCommand(CommandPermissions perm, String name, String... alias) {
		this(perm.getPermission(), name, alias);
	}
	
	public AllAdminCommand(String permission, String name, String... alias) {
		this.name = name;
		this.alias = alias;
		this.permission = permission;
	}

	@Override
	public List getCommandAliases() {
		return Arrays.asList(alias);
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		IUser user = AllAdmin.getUser(sender);
		return (hasPermission(user) || user.hasPermission(permission)) || super.canCommandSenderUseCommand(sender);
	}

	public boolean hasPermission(IUser sender) {
		return false;
	}

	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return AllAdmin.getString(name + "usage");
	}
	
	public String getCommandUsage(IUser sender) {
		return this.getCommandUsage(sender.getICommandSender());
	}
	
	@Override
	public void processCommand(ICommandSender user, String[] args) {
		IUser sender = AllAdmin.getUser(user);
		execute(sender, args);
	}

	public abstract void execute(IUser sender, String[] args);
	
	@Override
	public int compareTo(Object par1Obj) {
		if (par1Obj instanceof ICommand) {
			return compareTo((ICommand) par1Obj);
		}
		return super.compareTo(par1Obj);
	}
	@Override
	public int compareTo(ICommand par1iCommand) {
		if (par1iCommand == null){
			return 0;
		}
		return getCommandName().compareTo(par1iCommand.getCommandName());
	}
}
