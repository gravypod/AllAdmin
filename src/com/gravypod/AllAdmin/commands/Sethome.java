package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.AllAdminUser;


public class Sethome implements ICommand {

	@Override
    public void registerSelf(AllAdmin plugin, CommandHandler ch) {
    
	    plugin.getCommand("sethome").setExecutor(ch);
	    
    }

	@Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
		
		if (!(sender instanceof Player)) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
			return true;
		}
		
		AllAdminUser allAdmin = (AllAdminUser) AllAdmin.getUser(cmd);
		
		if (!allAdmin.canUseCommand(cmd)) {
			allAdmin.getBukkitPlayer().sendMessage(ChatColor.RED + "You do not have permissions to use that command!");
			return true;
		}
		
		allAdmin.setHome(((Player) sender).getLocation());
		
		allAdmin.sendMessage(ChatColor.AQUA + "Your command is done!");
		
		return true;
    }

	@Override
    public String commandHelp() {
	    return "/sethome to set a home location";
    }
	
}
