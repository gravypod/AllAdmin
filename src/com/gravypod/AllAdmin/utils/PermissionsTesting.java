/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.OfflineAllAdminUser;

public class PermissionsTesting {
	
	public static boolean canUseCommand(final OfflineAllAdminUser player, final String command) {
	
		return player.getGroup().getPermissions().contains("alladmin.commands." + command);
	}
	
	public static boolean canUseCommand(final AllAdminUser player, final String command) {
	
		return player.getGroup().getPermissions().contains("alladmin.commands." + command) ? true : hasPermission(player.getBukkitPlayer(), "alladmin.commands." + command);
	}
	
	public static boolean canUseCommand(final Player player, final String command) {
	
		return PermissionsTesting.hasPermission(player, "alladmin.commands." + command);
	}
	
	public static boolean canUseCommand(final CommandSender player, final String command) {
	
		return PermissionsTesting.hasPermission(player, "alladmin.commands." + command);
	}
	
	public static boolean hasPermission(final OfflineAllAdminUser player, final String node) {
	
		return player.hasPermission(node);
		
	}
	
	public static boolean hasPermission(final AllAdminUser player, final String node) {
	
		return player.hasPermission(node);
		
	}
	
	public static boolean hasPermission(final Player player, final String node) {
	
		return player.hasPermission(node);
		
	}
	
	public static boolean hasPermission(final CommandSender player, final String node) {
	
		return player.hasPermission(node);
		
	}
	
}
