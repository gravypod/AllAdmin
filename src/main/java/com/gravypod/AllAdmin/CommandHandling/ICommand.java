/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.CommandHandling;

import com.gravypod.AllAdmin.AllAdmin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommand {
	
	/**
	 * 
	 * Used to register a command on startup.
	 * Should only called one time!
	 * 
	 * @param plugin - Instance of the plugin for it to be registered to.
	 * @param commandHandler - CommandHandler to pass itself to
	 * 
	 */
    public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler);
    
    /**
     * 
     * Run a command.
     * 
     * @param sender - Sender of the command
     * @param command - Command object from bukkit
     * @param cmd - Command as a string
     * @param args - Arguments of the command
     * @return 
     * 
     */
    public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args);
    
    /**
     * 
     * Help documentation of the command.
     * 
     * @return
     * 
     */
    public String commandHelp();

}
