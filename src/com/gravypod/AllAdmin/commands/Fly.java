package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.utils.MatchUser;

public class Fly extends CommandUtil {
	
	@Override
	public void registerSelf(AllAdmin plugin, CommandHandler commandHandler) {
		plugin.getCommand("fly").setExecutor(commandHandler);
	}
	
	@Override
	public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
		
		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}
		
		if (!(sender instanceof Player)) {
			
			if (args.length == 1) {
				
				final Player toFly = MatchUser.matchOnlineUser(args[0]);
				
				if (toFly == null) {
					sender.sendMessage(AllAdmin.getMessages("noPlayer"));
					return true;
				}
				
				setFly(toFly);
				
			}
			
			return true;
			
		}
		
		setFly((Player) sender);
		
		return true;
		
	}
	
	public void setFly(Player player) {
		
		boolean toSet = !player.getAllowFlight();
		
		player.setAllowFlight(toSet);
		
		player.sendMessage(AllAdmin.getMessages(toSet ? "flyOn" : "flyOff"));
		
	}
	
	@Override
	public String commandHelp() {
		return "/fly turns your flymode on";
	}
	
}
