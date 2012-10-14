/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;

public class Test extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler ch) {
	
		plugin.getCommand("Test").setExecutor(ch);
		
	}
	
	@Override
	public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		AllAdmin.getUser(sender.getName()).hasPermission("alladmin.commands." + cmd);
		
		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}
		
		sender.sendMessage("Test this is!");
		
		return true;
		
	}
	
	@Override
	public final String commandHelp() {
	
		return ChatColor.AQUA + "Test command: /test, used for testing";
	}
	
}
