/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.CommandHandling;

import com.gravypod.AllAdmin.AllAdmin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommand {

    public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler);

    public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args);

    public String commandHelp();

}
