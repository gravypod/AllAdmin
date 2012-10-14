/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.commands;

import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.user.IUser;

public class List extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler ch) {
	
		plugin.getCommand("List").setExecutor(ch);
	}
	
	@Override
	public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		
		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}
		
		String players = null;
		
		synchronized(AllAdmin.getUserList()) {
			
			final TreeMap<String, IUser> onlinePlayers = new TreeMap<String, IUser>();
			onlinePlayers.putAll(AllAdmin.getUserList());
			
			for (final String player : onlinePlayers.keySet()) {
				
				if (players == null) {
					
					players = "There are " + onlinePlayers.size() + " player online: " + player + ", ";
					continue;
					
				}
				
				players += player + ", ";
				
			}
			
			if (players == null) {
				sender.sendMessage(AllAdmin.getMessages("noPlayersOn"));
				return true;
			}
			
			sender.sendMessage(ChatColor.AQUA + players);
		}
		
		return true;
		
	}
	
	@Override
	public String commandHelp() {
	
		return "Use /list to show a list of online players.";
	}
	
}
