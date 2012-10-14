package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;
import com.gravypod.AllAdmin.utils.TeleportUtils;


public class Setwarp implements ICommand {

	@Override
    public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
    
	    plugin.getCommand("setwarp").setExecutor(commandHandler);
	    
    }

	@Override
    public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		
		final IUser user = AllAdmin.getUser(sender.getName());
		
		if (!user.hasPermission("alladmin.commands.setwarp")) {
			user.sendCommandFaliure(cmd, "noPermissions");
			return true; 
		}
		
		if (args.length < 1) {
			user.sendMessage(commandHelp());
		}
		
		
		final AllAdminUser bukkitUser = ((AllAdminUser) user);
		
		TeleportUtils.setLocation(Settings.warpsYamlFile, "warps", args[0], bukkitUser.getBukkitPlayer().getLocation());
				
		bukkitUser.sendMessage(ChatColor.AQUA + "You have set the warp " + args[0]);
				
		
	    return true;
	    
    }

	@Override
    public String commandHelp() {
    
	    return "/setwarp (Name) sets the location you are standing at as a warp";
    }
	
}
