package com.gravypod.AllAdmin.commands;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Test implements ICommand {

    @Override
    public void registerSelf(AllAdmin plugin, CommandHandler ch) {

        plugin.getCommand("Test").setExecutor(ch);

    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {

        AllAdmin.getUser(sender.getName()).hasPermission("alladmin.commands." + cmd);

        sender.sendMessage("Test this is!");

        return true;

    }

    @Override
    public String commandHelp() {

        return ChatColor.AQUA + "Test command: /test, used for testing";
    }

}
