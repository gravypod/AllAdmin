/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.user;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AllAdminCMD implements IUser {

	final CommandSender sender;

    public AllAdminCMD(final CommandSender _sender) {

        sender = _sender;
    }

    public final String getUserName() {

        return "Console";
    }

    public final String getDisplayName() {

        return "Console";
    }

    public final Inventory getInventory() {

        return null;
    }

    public boolean doesHaveItem(Material m) {

        return false;
    }

    public boolean doesHaveItem(ItemStack m) {

        return false;
    }

    public boolean doesHaveItem(int m) {

        return false;
    }

    public void sendCommandFaliure(String command) {

        sender.sendMessage(ChatColor.RED + "We could not exacute the command " + command + " !");
    }

    public final boolean canUseCommand(String command) {

        return true;
    }

    public final boolean hasPermission(String permission) {

        return true;
    }

    @Override
    public void sendMessage(String message) {

        this.sender.sendMessage(message);

    }

}
