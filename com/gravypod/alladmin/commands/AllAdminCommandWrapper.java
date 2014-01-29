package com.gravypod.alladmin.commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class AllAdminCommandWrapper extends AllAdminCommand {
	ICommand command;
	boolean ready;
	public AllAdminCommandWrapper(CommandPermissions perm, String name, String ... alias) {
		super(perm, name, alias);
		ready = false;
	}
	
	@Override
	public void processCommand(ICommandSender user, String[] args) {
		if (!ready) {
			super.processCommand(user, args);
			return;
		}
		
		command.processCommand(user, args);
	}
	
	@Override
	public int compareTo(ICommand par1iCommand) {
		return command.compareTo(par1iCommand);
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		if (command instanceof CommandBase) {
			return ((CommandBase)command).getRequiredPermissionLevel();
		}
		
		return super.getRequiredPermissionLevel();
	}
	
	@Override
	public int compareTo(Object par1Obj) {
		if (!ready) {
			return super.compareTo(par1Obj);
		}
		return command.compareTo(par1Obj);
	}
	
	@Override
	public String getCommandName() {
		if (!ready) {
			return super.getCommandName();
		}
		return command.getCommandName();
	}
	
	@Override
	public boolean hasPermission(IUser sender) {
		return ready && super.hasPermission(sender);
	}
	
	@Override
	public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
		if (!ready) {
			return false;
		}
		return command.isUsernameIndex(par1ArrayOfStr, par2);
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender par1iCommandSender, String[] par2ArrayOfStr) {
		if (!ready) {
			return null;
		}
		return command.addTabCompletionOptions(par1iCommandSender, par2ArrayOfStr);
	}
	
	@Override
	public List getCommandAliases() {
		if (!ready) {
			return null;
		}
		return command.getCommandAliases();
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		if (!ready) {
			return null;
		}
		return command.getCommandUsage(sender);
	}
	
	@Override
	void execute(IUser sender, String[] args) {
		if (!ready) {
			return;
		}
		command.processCommand(sender.getICommandSender(), args);
	}
	
	public void setCommand(ICommand c) {
		this.command = c;
		this.ready = true;
	}
	
	
}
