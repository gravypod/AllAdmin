/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.commands;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.IUser;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.TreeMap;

public class List implements ICommand {

    @Override
    public void registerSelf(final AllAdmin plugin, final CommandHandler ch) {
        plugin.getCommand("List").setExecutor(ch);
    }

    @Override
    public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {

        String players = null;
        
        synchronized (AllAdmin.getUserList()) {
        
        	final TreeMap<String,IUser> onlinePlayers = new TreeMap<String, IUser>();
        	onlinePlayers.putAll(AllAdmin.getUserList());
        
        	for (String player : onlinePlayers.keySet()) {

        		if (players == null) {

        			players = "There are " + onlinePlayers.size() + " player online: " + player + ", ";
        			continue;

        		}

        		players += player + ", ";

        	}

        	if (players == null) {
        		sender.sendMessage(ChatColor.RED + "There are no players online!");
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
