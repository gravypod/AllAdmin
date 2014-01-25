package com.gravypod.alladmin.commands;

import net.minecraft.command.ICommand;

import com.gravypod.alladmin.Command;
import com.gravypod.alladmin.permissions.Permissions;

import static com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public enum Commands {
	
	HEAL(new CommandHeal(CommandPermissions.HEAL, "heal")),
	HOME(new CommandHome(CommandPermissions.HOME, "home")),
	SET_HOME(new CommandSetHome(CommandPermissions.SET_HOME, "sethome", "shome", "sho")),
	PERMISSION(new CommandPermission(CommandPermissions.PERMISSION, "permission")),
	CHEST(new CommandChest(CommandPermissions.CHEST, "chest", "c", "ec")),
	WORKBENCH(new CommandWorkbench(CommandPermissions.WORKBENCH, "workbench")),
	FEED(new CommandFeed(CommandPermissions.FEED, "feed")),
	ENCHANT(new CommandEnchant(CommandPermissions.ENCHANT, "enchant")),
	MUTE(new CommandMute(CommandPermissions.MUTE, "mute")),
	INVISIBLE(new CommandInvisible(CommandPermissions.INVISIBLE, "invisible", "vanish")),
	STOP_LAG(new CommandStopLag(CommandPermissions.STOP_LAG, "stoplag"));
	
	private final ICommand command;

	private Commands(ICommand command) {
		this.command = command;
	}

	public ICommand getCommand() {
		return command;
	}
}
