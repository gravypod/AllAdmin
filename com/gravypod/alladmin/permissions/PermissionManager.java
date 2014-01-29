package com.gravypod.alladmin.permissions;

import java.io.File;
import java.util.HashMap;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.PermissionFiles;
import com.gravypod.alladmin.files.SerializedUser;
import com.gravypod.alladmin.files.UserFiles;

public class PermissionManager {

	/**
	 * Preset permissions for all commands
	 * 
	 */
	public enum CommandPermissions {
		
		HOME("alladmin.command.home"), 
		SET_HOME("alladmin.command.sethome"),
		MULTI_SET_HOME("alladmin.command.multisethome"), 
		HEAL("alladmin.command.heal"), 
		HEAL_OTHERS("alladmin.command.heal.others"),
		PERMISSION("alladmin.command.permission"), 
		PERM_RULER("alladmin.admin.permruler" /* Allowed to do anything permission related*/),
		CHEST("alladmin.command.chest"),
		WORKBENCH("alladmin.command.workbench"), 
		FEED("alladmin.command.feed"),
		FEED_OTHERS("alladmin.command.feed.others"),
		MUTE("alladmin.admin.mute"),
		ENCHANT_TABLE("alladmin.command.enchanttable"),
		STOP_LAG("alladmin.admin.stoplag"),
		INVISIBLE("alladmin.admin.invisible"),
		FLY("alladmin.command.fly"),
		FLY_OTHERS("alladmin.command.fly.others"),
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
		TP_OTHERS("alladmin.command.tp.others"),
		TOGGLE_DOWNFALL("alladmin.command.toggledownfall"),
		WHITELIST("alladmin.command.whitelist"),
		XP("alladmin.command.xp"),
		WARP("alladmin.command.warp"),
		SET_WARP("alladmin.command.setwarp"),
		SPAWN("alladmin.command.spawn"),
		SET_SPAWN("alladmin.command.setspawn"), 
		INVSEE("alladmin.command.invsee"),
		CLEAR_INVENTORY("alladmin.command.clearinventory"), 
		GET_POS("alladmin.command.getpos"),
		BROADCAST("alladmin.command.broadcast"),
		BLOCK("alladmin.blocks.") {
			@Override
			public String getPermissionFor(Object o) {
				return getPermission() + o.toString();
			}
		},
		BLOCK_ALL("alladmin.blocks.all"), 
		REPAIR("alladmin.command.repair"), 
		HELP("alladmin.command.help"),
		OP("alladmin.command.op"),
		DEBUG("alladmin.command.debug"),
		GAME_RULE("alladmin.command.gamerule"), 
		TIMEOUT("alladmin.command.timeout"), 
		TIME("alladmin.command.time"),
		;
		
		private final String permission;
		
		private CommandPermissions(String permission) {
			this.permission = permission.toLowerCase();
		}

		public String getPermissionFor(Object o) {
			return getPermission();
		}

		public String getPermission() {
			return permission;
		}

	}

	private static final HashMap<String, Group> groups = PermissionFiles.getGroups();
	private static final String defaultGroup = PermissionFiles.getDefaultGroup();

	public static void addGroup(String name) {
		Group group = new Group(name, new String[] {});
		group.parent(getDefaultRank());
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
	
	public static boolean groupExists(String group) {
		return groups.containsKey(group);
	}
	
	public static void deleteGroup(String groupName) {
		Group g = groups.remove(groupName);
		for (Group group : groups.values()) {
			removeSubgroup(group, g);
		}
	}
	
	public static boolean removeSubgroup(Group parent, Group subgroup) {
		return parent.getSubgroups().remove(subgroup.getName());
	}

	public static boolean addSubgroup(Group group, Group newSubGroup) {
		
		boolean groupadded = !group.getSubgroups().contains(newSubGroup);
		
		if (groupadded) {
			group.getSubgroups().add(newSubGroup.getName());
		}
		return groupadded;
	}

}
