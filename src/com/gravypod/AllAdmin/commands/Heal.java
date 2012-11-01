package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.utils.MatchUser;

public class Heal extends CommandUtil {
	
	@Override
	public void registerSelf(final AllAdmin plugin, final CommandHandler commandHandler) {
	
		plugin.getCommand("heal").setExecutor(commandHandler);
	}
	
	@Override
	public final boolean doCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
	
		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}
		
		if (!(sender instanceof Player) && args.length < 1) {
			sender.sendMessage(AllAdmin.getMessages("noArguments"));
			return true;
		}
		
		if (args.length == 1) {
			
			if (!AllAdmin.getUser(sender.getName()).canUseCommand(cmd + ".others")) {
				sender.sendMessage(AllAdmin.getMessages("noPermissions"));
				return true;
			}
			
			final Player toHeal = MatchUser.matchOnlineUser(args[0]);
			
			if (toHeal == null) {
				sender.sendMessage(AllAdmin.getMessages("noPlayer"));
				return true;
			}
			
			heal(toHeal);
			
			return true;
			
		}
		
		heal(((AllAdminUser) AllAdmin.getUser(sender.getName())).getBukkitPlayer());
		
		return true;
		
	}
	
	public static void heal(final Player player) {
	
		player.setFoodLevel(20);
		player.setHealth(10);
		player.setSaturation(10);
		player.sendMessage(AllAdmin.getMessages("hasHealed"));
		
	}
	
	@Override
	public final String commandHelp() {
	
		return "/feed [player]";
	}
	
}
