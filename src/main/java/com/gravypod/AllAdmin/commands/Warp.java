package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.utils.TeleportUtils;

public class Warp implements ICommand {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
	
		plugin.getCommand("warp").setExecutor(commandHandler);
		
	}
	
	@Override
	public final boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		if (!(sender instanceof Player)) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd);
			return true;
		}
		
		final AllAdminUser user = (AllAdminUser) AllAdmin.getUser(sender.getName());
		
		if (!user.canUseCommand(cmd)) {
			user.sendCommandFaliure(cmd);
			return true;
		}
		
		if (!(args.length == 1)) {
			user.sendCommandFaliure(cmd);
			return true;
		}
		
		Location toSend = TeleportUtils.getLocation(Settings.warpsYamlFile, "warps", args[0]);
		
		if (toSend == null) {
			sender.sendMessage(ChatColor.RED + "There is no warp with that name");
			return true;
		}
		
		user.getBukkitPlayer().teleport(toSend);
		
		return true;
		
	}
	
	@Override
	public final String commandHelp() {
	
		return "/warp (warp name) will teleport you to the warp";
		
	}
	
}
