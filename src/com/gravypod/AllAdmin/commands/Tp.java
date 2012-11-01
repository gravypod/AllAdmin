/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.utils.MatchUser;

public class Tp extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler ch) {
	
		plugin.getCommand("tp").setExecutor(ch);
		
	}
	
	@Override
	public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		switch(args.length) {
			case 2:
				if (!MatchUser.matchOnlineUser(args[0]).teleport(MatchUser.matchOnlineUser(args[1]))) {
					AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd, "noPlayer");
				}
				break;
			case 1:
				
				if (!(sender instanceof Player)) {
					AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd, "mustBePlayer");
				}
				
				if (!((Player) sender).teleport(MatchUser.matchOnlineUser(args[0]))) {
					AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd, "noPlayer");
				}
				break;
			default:
				sender.sendMessage(commandHelp());
		}
		
		return true;
	}
	
	@Override
	public final String commandHelp() {
	
		return ChatColor.AQUA + "Tp command: /tp (Player) ([optional] player), teleport to a player";
		
	}
	
}
