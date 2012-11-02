/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.configuration.Settings;
import com.gravypod.AllAdmin.user.AllAdminUser;

public class Home extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler ch) {
	
		if (Settings.isUseHomes()) {
			plugin.getCommand("Home").setExecutor(ch);
		}
		
	}
	
	@Override
	public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		if (!canUseCommand(sender, cmd, true, true)) {
			return true;
		}
		
		final AllAdminUser user = (AllAdminUser) AllAdmin.getUser(sender.getName());
		
		String homeName = null;
		
		if (user.getBukkitPlayer().hasPermission("alladmin.multihome")) {
			if (args.length == 1) {
				homeName = args[0];
			}
		}
		
		final Location homeLoc = user.getHome(homeName == null ? homeName : "home");
		
		if (homeLoc == null) {
			user.getBukkitPlayer().sendMessage(AllAdmin.getMessages("notSetHome"));
			return true;
		}
		
		user.getBukkitPlayer().teleport(user.getHome(homeName == null ? homeName : "home"));
		
		return true;
		
	}
	
	@Override
	public final String commandHelp() {
	
		return "Use /sethome to set a home, /home takes you back";
	}
	
}
