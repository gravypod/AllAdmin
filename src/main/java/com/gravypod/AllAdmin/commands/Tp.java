package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.utils.MatchUser;

public class Tp implements ICommand {
	
	@Override
	public void registerSelf(AllAdmin plugin, CommandHandler ch) {
		
		plugin.getCommand("tp").setExecutor(ch);
		
	}
	
	@Override
	public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
		
		switch(args.length) {
			case 2:
				if (!MatchUser.matchOnlineUser(args[0]).teleport(MatchUser.matchOnlineUser(args[1]))) {
					AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
				}
				break;
			case 1:
				
				if (!(sender instanceof Player))
					AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
				
				if (!((Player) sender).teleport(MatchUser.matchOnlineUser(args[0]))) {
					AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
				}
				break;
			default:
				sender.sendMessage(commandHelp());
		}
		
		return true;
	}
	
	@Override
	public String commandHelp() {
		
		return ChatColor.AQUA + "Tp command: /tp (Player) ([optional] player), teleport to a player";
		
	}
	
}
