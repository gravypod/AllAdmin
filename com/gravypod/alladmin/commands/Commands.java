package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public enum Commands {
	
	HEAL(new CommandHeal(CommandPermissions.HEAL, "heal")),
	HOME(new CommandHome(CommandPermissions.HOME, "home")),
	SET_HOME(new CommandSetHome(CommandPermissions.SET_HOME, "sethome", "shome", "sho")),
	PERMISSION(new CommandPermission(CommandPermissions.PERMISSION, "permission")),
	CHEST(new CommandChest(CommandPermissions.CHEST, "chest", "c", "ec")),
	WORKBENCH(new CommandWorkbench(CommandPermissions.WORKBENCH, "workbench")),
	FEED(new CommandFeed(CommandPermissions.FEED, "feed")),
	ENCHANT_TABLE(new CommandEnchant(CommandPermissions.ENCHANT_TABLE, "enchanttable")),
	MUTE(new CommandMute(CommandPermissions.MUTE, "mute")),
	//INVISIBLE(new CommandInvisible(CommandPermissions.INVISIBLE, "invisible", "vanish")),
	STOP_LAG(new CommandStopLag(CommandPermissions.STOP_LAG, "stoplag")),
	FLY(new CommandFly(CommandPermissions.FLY, "fly")),
	BAN(new AllAdminCommandWrapper(CommandPermissions.BAN, "ban")), 
	BAN_IP(new AllAdminCommandWrapper(CommandPermissions.BAN, "ban-ip")), 
	KICK(new AllAdminCommandWrapper(CommandPermissions.KICK, "kick")),
	BAN_LIST(new AllAdminCommandWrapper(CommandPermissions.BAN, "ban-list")),
	CLEAR(new AllAdminCommandWrapper(CommandPermissions.CLEAR, "clear")),
	DEFAULT_GAME_MODE(new AllAdminCommandWrapper(CommandPermissions.DEFAULT_GAME_MODE, "defaultgamemode")),
	DIFFICULTY(new AllAdminCommandWrapper(CommandPermissions.DIFFICULTY, "difficulty")),
	EFFECT(new AllAdminCommandWrapper(CommandPermissions.EFFECT, "effect")),
	ENCHANT(new AllAdminCommandWrapper(CommandPermissions.ENCHANT, "enchant")),
	GAME_MODE(new AllAdminCommandWrapper(CommandPermissions.GAME_MODE, "gamemode")),
	GIVE(new AllAdminCommandWrapper(CommandPermissions.GIVE, "give")),
	PARDON(new AllAdminCommandWrapper(CommandPermissions.PARDON, "pardon")),
	ME(new AllAdminCommandWrapper(CommandPermissions.ME, "me")),
	LIST(new AllAdminCommandWrapper(CommandPermissions.LIST, "list")),
	SAVE_ALL(new AllAdminCommandWrapper(CommandPermissions.SAVE_ALL, "save-all")),
	SAVE_OFF(new AllAdminCommandWrapper(CommandPermissions.SAVE_OFF, "save-off")),
	SAVE_ON(new AllAdminCommandWrapper(CommandPermissions.SAVE_ON, "save-on")),
	PLAY_SOUND(new AllAdminCommandWrapper(CommandPermissions.PLAY_SOUND, "playsound")),
	SAY(new AllAdminCommandWrapper(CommandPermissions.SAY, "say")),
	SPAWN_POINT(new AllAdminCommandWrapper(CommandPermissions.SPAWN_POINT, "spawnpoint")),
	SPREAD_PLAYERS(new AllAdminCommandWrapper(CommandPermissions.SPREAD_PLAYERS, "spreadplayers")),
	SEED(new AllAdminCommandWrapper(CommandPermissions.SEED, "seed")),
	SCOREBOARD(new AllAdminCommandWrapper(CommandPermissions.SCOREBOARD, "scoreboard")),
	STOP(new AllAdminCommandWrapper(CommandPermissions.STOP, "stop")),
	WEATHER(new AllAdminCommandWrapper(CommandPermissions.WEATHER, "weather")),
	TP(new AllAdminCommandWrapper(CommandPermissions.TP, "tp")),
	TOGGLE_DOWNFALL(new AllAdminCommandWrapper(CommandPermissions.TOGGLE_DOWNFALL, "toggledownfall")),
	WHITELIST(new AllAdminCommandWrapper(CommandPermissions.WHITELIST, "whitelist")),
	XP(new AllAdminCommandWrapper(CommandPermissions.XP, "xp"))
	;
	
	private final AllAdminCommand command;

	private Commands(AllAdminCommand command) {
		this.command = command;
	}

	public AllAdminCommand getCommand() {
		return command;
	}
}
