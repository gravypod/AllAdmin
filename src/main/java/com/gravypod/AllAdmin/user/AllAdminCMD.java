package com.gravypod.AllAdmin.user;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AllAdminCMD implements IUser {

    CommandSender sender;

    public AllAdminCMD(CommandSender _sender) {

        sender = _sender;
    }

    public String getUserName() {

        return "Console";
    }

    public String getDisplayName() {

        return "Console";
    }

    public Inventory getInventory() {

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

    public boolean canUseCommand(String command) {

        return true;
    }

    public boolean hasPermission(String permission) {

        return true;
    }

    @Override
    public void sendMessage(String message) {

        this.sender.sendMessage(message);

    }

}
