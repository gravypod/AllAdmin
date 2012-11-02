package com.gravypod.AllAdmin.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.permissions.PermissionData;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;

public class Groups extends CommandUtil {
	
	enum arguments {
		promote, change, check;
	}
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
	
		plugin.getCommand("groups").setExecutor(commandHandler);
	}
	
	@Override
	public boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}
		
		arguments arg = null;
		
		if (!(args.length >= 1)) {
			incorrectUsage(sender);
			return true;
		}
		
		try {
			arg = arguments.valueOf(args[0]);
		} catch (final Exception e) {
			incorrectUsage(sender);
			return true;
		}
		
		switch(arg) {
			case change:
			case promote:
				if (!(args.length >= 3)) {
					incorrectUsage(sender);
					return true;
				}
				
				final String groupName = args[1];
				final String userName = args[2];
				
				if (PermissionData.getGroups().containsKey(groupName)) {
					sender.sendMessage("That is not a real group!");
					return true;
				}
				
				final IUser user = AllAdmin.getUser(userName);
				
				if (!(user instanceof AllAdminUser)) {
					sender.sendMessage("That is not a real player!"); // TODO:
																	  // Update
																	  // for the
																	  // OfflineAllAdminPlayer
					return true;
				}
				
				((AllAdminUser) user).setGroup(groupName);
				
				sender.sendMessage("You have changed the group of that player!");
				
				break;
		}
		
		return true;
	}
	
	private String ourArguments() {
	
		return Arrays.toString(arguments.values());
	}
	
	private void incorrectUsage(final CommandSender sender) {
	
		sender.sendMessage("That was incorrect useage of the Groups command.");
		sender.sendMessage("You can yes it correctly like this: " + commandHelp());
	}
	
	@Override
	public String commandHelp() {
	
		return "/groups " + ourArguments();
	}
	
}
