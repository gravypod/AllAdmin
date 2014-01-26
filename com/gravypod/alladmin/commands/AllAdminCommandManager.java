package com.gravypod.alladmin.commands;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;

public class AllAdminCommandManager extends ServerCommandManager {

	private final CommandHandler original;

	public AllAdminCommandManager(CommandHandler original) {
		this.original = original;

		Map<String, ICommand> commands = original.getCommands();
		for (Commands c : Commands.values()) {
			AllAdminCommand alladminCommmand = c.getCommand();
			String name = alladminCommmand.getCommandName();
			ICommand command = commands.remove(name);
			if (command != null && alladminCommmand instanceof AllAdminCommandWrapper) {
				((AllAdminCommandWrapper) alladminCommmand).setCommand(command);
			}
		}
		
		for (ICommand c : commands.values()) {
			this.registerCommand(c);
		}

	}

	@Override
	public boolean equals(Object obj) {
		return original.equals(obj);
	}

}
