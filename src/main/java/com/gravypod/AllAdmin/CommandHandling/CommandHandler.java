/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.CommandHandling;

import com.gravypod.AllAdmin.AllAdmin;
import org.apache.commons.lang.WordUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {



        try {
            return ((ICommand) Class.forName("com.gravypod.AllAdmin.commands." + WordUtils.capitalize(cmd.toLowerCase())).newInstance()).doCommand(sender, command, cmd, args);
        } catch (Exception e) {
            AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
        }

        return false;
    }

}
