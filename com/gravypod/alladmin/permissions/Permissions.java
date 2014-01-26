package com.gravypod.alladmin.permissions;

import java.io.File;
import java.util.HashMap;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.PermissionFiles;
import com.gravypod.alladmin.files.SerializedUser;
import com.gravypod.alladmin.files.UserFiles;

public class Permissions {

	/**
	 * Preset permissions for all commands
	 * 
	 */
	public enum CommandPermissions {
		
		HOME("alladmin.command.home"), 
		SET_HOME("alladmin.command.sethome"),
		MULTI_SET_HOME("alladmin.command.multisethome"), 
		HEAL("alladmin.command.heal"), 
		PERMISSION("alladmin.command.permission"), 
		PERM_RULER("alladmin.admin.permruler" /* Allowed to do anything permission related*/),
		CHEST("alladmin.command.chest"),
		WORKBENCH("alladmin.command.workbench"), 
		FEED("alladmin.command.feed"),
		MUTE("alladmin.admin.mute"),
		ENCHANT_TABLE("alladmin.command.enchanttable"),
		STOP_LAG("alladmin.admin.stoplag"),
		INVISIBLE("alladmin.admin.invisible"),
		FLY("alladmin.command.fly"),
		BAN("alladmin.command.ban"),
		BAN_LIST("alladmin.command.ban-list"),
		KICK("alladmin.command.kick"),
		CLEAR("alladmin.command.clear"),
		DEFAULT_GAME_MODE("alladmin.command.defaultgamemode"),
		DIFFICULTY("alladmin.command.difficulty"),
		EFFECT("alladmin.command.effect"),
		ENCHANT("alladmin.command.enchant"),
		GAME_MODE("alladmin.command.gamemode"),
		GIVE("alladmin.command.give"),
		PARDON("alladmin.command.pardon"),
		ME("alladmin.command.me"),
		LIST("alladmin.command.list"),
		SAVE_ALL("alladmin.command.save-all"),
		SAVE_OFF("alladmin.command.save-off"),
		SAVE_ON("alladmin.command.save-on"),
		PLAY_SOUND("alladmin.command.playsound"),
		SAY("alladmin.command.say"),
		SPAWN_POINT("alladmin.command.spawnpoint"),
		SPREAD_PLAYERS("alladmin.command.spreadplayers"),
		SEED("alladmin.command.seed"),
		SCOREBOARD("alladmin.command.scoreboard"),
		STOP("alladmin.command.stop"),
		WEATHER("alladmin.command.weather"),
		TP("alladmin.command.tp"),
		TOGGLE_DOWNFALL("alladmin.command.toggledownfall"),
		WHITELIST("alladmin.command.whitelist"),
		XP("alladmin.command.xp"),;
		
		private final String permission;
		
		private CommandPermissions(String permission) {
			this.permission = permission;
		}

		public String getPermission() {
			return permission;
		}

	}

	private static final HashMap<String, Group> groups = PermissionFiles.getGroups();
	private static final String defaultGroup = PermissionFiles.getDefaultGroup();

	public static void addGroup(IUser user, String name, String... subgroups) {
		Group group = new Group(name, new String[] {});
		for (String s : subgroups) {
			if (groups.containsKey(s)) {
				group.parent(s);
			}
		}
	}

	public static Group findUser(String name) {
		SerializedUser user = UserFiles.loadUser(name);
		if (user.rank == null || !groups.containsKey(user.rank)) {
			user.rank = defaultGroup;
		}
		return groups.get(user.rank);
	}

	public static Group changeRank(String name, String rank) {
		SerializedUser user = UserFiles.loadUser(name);
		if (groups.containsKey(rank)) {
			user.rank = rank;
		}
		return groups.get(user.rank);
	}

	public static String getDefaultRank() {
		return defaultGroup;
	}

	public static Group getGroup(String group) {
		return groups.get(group);
	}

}
