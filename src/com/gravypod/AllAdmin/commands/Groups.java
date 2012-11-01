package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;


public class Groups extends CommandUtil {
	
	enum arguments {
		promote, add, check;
	}

	@Override
    public void registerSelf(AllAdmin plugin, CommandHandler commandHandler) {
		plugin.getCommand("groups").setExecutor(commandHandler);
    }

	@Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
		
		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}
		
		
		
		return true;
    }

	@Override
    public String commandHelp() {
	    return "/groups";
    }
	
}
