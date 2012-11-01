package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.utils.TeleportUtils;

public class Warp extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
	
		plugin.getCommand("warp").setExecutor(commandHandler);
		
	}
	
	@Override
	public final boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		
		if (!canUseCommand(sender, cmd, true, true)) {
			return true;
		}
		
		final AllAdminUser user = (AllAdminUser) AllAdmin.getUser(sender.getName());
		
		if (!(args.length == 1)) {
			user.sendCommandFaliure(cmd, "noArguments");
			return true;
		}
		
		final Location toSend = TeleportUtils.getLocation(Settings.warpsYamlFile, "warps", args[0]);
		
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
