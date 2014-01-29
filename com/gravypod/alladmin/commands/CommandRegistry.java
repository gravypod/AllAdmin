package com.gravypod.alladmin.commands;

import net.minecraft.command.CommandBase;

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
	;
	
	private final CommandBase command;

	private CommandRegistry(CommandBase command) {
		this.command = command;
	}

	public CommandBase getCommand() {
		return command;
	}
}
