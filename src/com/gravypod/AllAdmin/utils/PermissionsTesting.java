/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.utils;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.user.AllAdminUser;

public class PermissionsTesting {
	
	public static boolean canUseCommand(final AllAdminUser player, final String command) {
	
		return PermissionsTesting.internalHasPermissions(player, "alladmin.commands." + command);
	}
	
	public static boolean canUseCommand(final Player player, final String command) {
	
		return PermissionsTesting.hasPermission(player, "alladmin.commands." + command);
	}
	
	public static boolean canUseCommand(final CommandSender player, final String command) {
	
		return PermissionsTesting.hasPermission(player, "alladmin.commands." + command);
	}
	
	public static boolean internalHasPermissions(final AllAdminUser player, final String node) {
	
		final StringBuilder sb = new StringBuilder();
		
		final List<String> playerPermissions = player.getGroup().getPermissions();
		
		for (final String s : node.split(".")) {
			
			sb.append("*");
			
			if (playerPermissions.contains(sb.toString())) {
				return true;
			}
			
			sb.deleteCharAt(sb.length() - 1);
			
			sb.append(s).append(".");
			
		}
		
		return player.getBukkitPlayer().isOp() ? true : PermissionsTesting.hasPermission(player.getBukkitPlayer(), node);
		
	}
	
	private static boolean hasPermission(final Player player, final String node) {
	
		return player.hasPermission(node);
		
	}
	
	private static boolean hasPermission(final CommandSender player, final String node) {
	
		return player.hasPermission(node);
		
	}
	
}
