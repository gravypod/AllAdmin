package com.gravypod.alladmin.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.CommandHelp;
import net.minecraft.command.IAdminCommand;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;

public class AllAdminCommandManager extends ServerCommandManager {
	public AllAdminCommandManager(CommandHandler original) {

		Map<String, ICommand> commands = new HashMap<String, ICommand>(original.getCommands());
		for (CommandRegistry c : CommandRegistry.values()) {
			
			AllAdminCommand alladminCommmand = c.getCommand();
			
			String name = alladminCommmand.getCommandName();
			
			if (alladminCommmand instanceof AllAdminCommandWrapper) {
				ICommand command = commands.get(name);
				if (command != null) {
					((AllAdminCommandWrapper) alladminCommmand).setCommand(command);
				} else {
					continue;
				}
			}
			
			registerCommand(alladminCommmand);
			
		}
		
		commands = original.getCommands();
		
		for (String s : commands.keySet()) {
			if (!commands.containsKey(s)) {
				registerCommand(commands.get(s));
			}
		}
		
		System.out.println(MinecraftServer.getServer().getCommandManager().getCommands());
		
	}
	

}
