/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.commands;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.AllAdminUser;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Sethome implements ICommand {

    @Override
    public void registerSelf(final AllAdmin plugin, final CommandHandler ch) {
   
    	if (Settings.useHomes)
    		plugin.getCommand("sethome").setExecutor(ch);

    }

    @Override
    public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {

        if (!(sender instanceof Player)) {
            AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd, "mustBePlayer");
            return true;
        }

        final AllAdminUser allAdmin = (AllAdminUser) AllAdmin.getUser(sender.getName());

        if (!allAdmin.canUseCommand(cmd)) {
            allAdmin.getBukkitPlayer().sendMessage(ChatColor.RED + "You do not have permissions to use that command!");
            return true;
        }

        allAdmin.setHome(((Player) sender).getLocation(), args.length == 1 ? args[0] : "home");

        allAdmin.sendMessage(ChatColor.AQUA + "Your command is done!");

        return true;
    }

    @Override
    public final String commandHelp() {
        return "/sethome to set a home location";
    }

}
