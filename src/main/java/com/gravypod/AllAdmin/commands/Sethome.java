/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.user.AllAdminUser;

public class Sethome extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler ch) {
	
		if (Settings.useHomes) {
			plugin.getCommand("sethome").setExecutor(ch);
		}
		
	}
	
	@Override
	public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		if (!canUseCommand(sender, cmd, true, true)) {
			return true;
		}
		
		final AllAdminUser user = (AllAdminUser) AllAdmin.getUser(sender.getName());
		
		user.setHome(((Player) sender).getLocation(), args.length == 1 ? args[0] : "home");
		
		user.sendMessage(AllAdmin.getMessages("setHome"));
		
		return true;
		
	}
	
	@Override
	public final String commandHelp() {
	
		return "/sethome to set a home location";
	}
	
}
