package com.gravypod.alladmin.commands;

import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public enum CommandRegistry {
	
	HEAL(new HealCommand(CommandPermissions.HEAL, "heal")),
	HOME(new HomeCommand(CommandPermissions.HOME, "home")),
	SET_HOME(new SetHomeCommand(CommandPermissions.SET_HOME, "sethome", "shome", "sho")),
	PERMISSION(new PermissionCommand(CommandPermissions.PERMISSION, "permission")),
	CHEST(new ChestCommand(CommandPermissions.CHEST, "chest", "c", "ec")),
	WORKBENCH(new WorkbenchCommand(CommandPermissions.WORKBENCH, "workbench")),
	FEED(new FeedCommand(CommandPermissions.FEED, "feed")),
	ENCHANT_TABLE(new EnchantTableCommand(CommandPermissions.ENCHANT_TABLE, "enchanttable")),
	MUTE(new MuteCommand(CommandPermissions.MUTE, "mute")),
	STOP_LAG(new StopLagCommand(CommandPermissions.STOP_LAG, "stoplag")),
	FLY(new FlyCommand(CommandPermissions.FLY, "fly")),
	TP_POS(new TpPosCommand(CommandPermissions.TP_OTHERS, "tppos")), 
	WARP(new WarpCommand(CommandPermissions.WARP, "warp")),
	SET_WARP(new SetWarpCommand(CommandPermissions.SET_WARP, "setwarp")),
	SPAWN(new SpawnCommand(CommandPermissions.SPAWN, "spawn")),
	SET_SPAWN(new SetSpawnCommand(CommandPermissions.SET_SPAWN, "setspawn")),
	INVSEE(new InvseeCommand(CommandPermissions.INVSEE, "invsee")),
	CLEAR_INVENTORY(new ClearInventoryCommand(CommandPermissions.CLEAR_INVENTORY, "clearinventory", "ci")),
	GET_POS(new GetPosCommand(CommandPermissions.GET_POS, "getpos", "gs", "gps")),
	BROADCAST(new BroadcastCommand(CommandPermissions.BROADCAST, "broadcast")),
	REPAIR(new RepairCommand(CommandPermissions.REPAIR, "repair")),
	GOD(new GodCommand(CommandPermissions.GAME_MODE, "god")),
	
	//// WRAPED COMMANDS \\\\
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

	private CommandRegistry(AllAdminCommand command) {
		this.command = command;
	}

	public AllAdminCommand getCommand() {
		return command;
	}
}
