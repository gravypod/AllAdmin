package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.AllAdminUser;


public class Back implements ICommand {

	@Override
    public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
		
		if (Settings.useBack)
			plugin.getCommand("Back").setExecutor(commandHandler);
	    
    }

	@Override
    public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		
		if (!(sender instanceof Player)) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
			return true;
		}
		
		final AllAdminUser user = (AllAdminUser) AllAdmin.getUser(sender.getName());
	    
		if (!user.canUseCommand(cmd)) {
			user.sendCommandFaliure(cmd);
			return true;
		}
		
		user.getBukkitPlayer().teleport(user.getLastLocation());
		user.sendMessage(ChatColor.AQUA + "You have been teleported!");
		
	    return true;
    }

	@Override
    public String commandHelp() {
		
	    return "type /back to go to your last location";
    }
	
}
