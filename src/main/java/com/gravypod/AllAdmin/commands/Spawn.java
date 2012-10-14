package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.user.AllAdminUser;

public class Spawn extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
	
		plugin.getCommand("spawn").setExecutor(commandHandler);
		
	}
	
	@Override
	public final boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		if (!(sender instanceof Player)) {
			AllAdmin.getUser(sender.getName()).sendCommandFaliure(cmd, "mustBePlayer");
			return true;
		}
		
		final AllAdminUser user = (AllAdminUser) AllAdmin.getUser(sender.getName());
		
		if (!user.canUseCommand(cmd)) {
			user.sendCommandFaliure(cmd, "noPermissions");
			return true;
		}
		
		user.getBukkitPlayer().teleport(user.getBukkitPlayer().getWorld().getSpawnLocation());
		
		return true;
		
	}
	
	@Override
	public final String commandHelp() {
	
		return "/spawn will take you to spawn";
	}
	
}
