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
    public void registerSelf(AllAdmin plugin, CommandHandler ch) {
        plugin.getCommand("List").setExecutor(ch);
    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {

        String players = null;

        TreeMap<String,IUser> onlinePlayers = AllAdmin.getUserList();

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

        return true;

    }

    @Override
    public String commandHelp() {
        return "Use /list to show a list of online players.";
    }

}
