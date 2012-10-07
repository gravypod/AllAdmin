package com.gravypod.AllAdmin.commands;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements ICommand {

    @Override
    public void registerSelf(AllAdmin plugin, CommandHandler ch) {

        plugin.getCommand("Home").setExecutor(ch);
    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {

        IUser user = AllAdmin.getUser(sender.getName());

        if (!(sender instanceof Player)) {

            user.sendCommandFaliure(cmd);

            return true;

        }

        AllAdminUser allAdmin = (AllAdminUser) user;

        if (!allAdmin.canUseCommand(cmd)) {
            allAdmin.getBukkitPlayer().sendMessage(ChatColor.RED + "You do not have permissions to use that command!");
            return true;
        }

        Location homeLoc = allAdmin.getHome();

        if (homeLoc == null) {
            allAdmin.getBukkitPlayer().sendMessage(ChatColor.RED + "You do not have a home set yet. Please set it with /sethome!");
            return true;
        }

        allAdmin.getBukkitPlayer().teleport(allAdmin.getHome());

        return true;

    }

    @Override
    public String commandHelp() {

        return "Use /sethome to set a home, /home takes you back";
    }

}
