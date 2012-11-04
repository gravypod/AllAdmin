/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.CommandHandling;

import org.apache.commons.lang.WordUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;

/**
 * 
 * Our command handler.
 * 
 */
public class CommandHandler implements CommandExecutor {
	
	public CommandHandler(final AllAdmin plugin) {
	
		new RegisterCommands(plugin, this);
		
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		try {
			return ((ICommand) Class.forName("com.gravypod.AllAdmin.commands." + WordUtils.capitalize(cmd.toLowerCase())).newInstance()).doCommand(sender, command, cmd, args);
		} catch (final Exception e) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd, "anError");
			e.printStackTrace();
		}
		
		return false;
	}
	
}
