package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.permissions.Group;
import com.gravypod.AllAdmin.user.AllAdminUser;


public class Permissions extends CommandUtil {

	@Override
    public void registerSelf(AllAdmin plugin, CommandHandler commandHandler) {
		plugin.getCommand("permissions").setExecutor(commandHandler);
    }

	@Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
		
		if (args.length != 1) {
			sender.sendMessage("Incorrect usage, /permissions this.is.a.node");
			return true;
		}
		
		if (!canUseCommand(sender, cmd, true, false)) {
			return true;
		}
		
		Group group =  ((AllAdminUser) AllAdmin.getUser(sender.getName())).getGroup();
		
		sender.sendMessage("It is " + sender.hasPermission(args[0]) + " that you have the permission " + args[0]);
		
		sender.sendMessage("It is " + ((AllAdminUser) AllAdmin.getUser(sender.getName())).getGroup().getPermissions().contains(args[0]) + " that you have the permission " + args[0]);
		
		sender.sendMessage("group: " + group.getName());
		sender.sendMessage("isDefault: " + group.isDefault());
		
		return true;
		
    }

	@Override
    public String commandHelp() {
	    return null;
    }
	
}
