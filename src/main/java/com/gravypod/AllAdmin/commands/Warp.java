package com.gravypod.AllAdmin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;
import com.gravypod.AllAdmin.user.AllAdminUser;

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
		
		
		final double x = Settings.warpsYamlFile.getDouble("warps." + args[0] + ".x");
		final double y = Settings.warpsYamlFile.getDouble("warps." + args[0] + ".y");
		final double z = Settings.warpsYamlFile.getDouble("warps." + args[0] + ".z");
		final float pitch = (float) Settings.warpsYamlFile.getDouble("warps." + args[0] + ".pitch");
		final float yaw = (float) Settings.warpsYamlFile.getDouble("warps." + args[0] + ".yaw");
		final World world = AllAdmin.getInstance().getServer().getWorld(Settings.warpsYamlFile.getString("warps." + args[0] + ".world"));
	
		if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z) || world == null || Float.isNaN(pitch) || Float.isNaN(yaw)) {
			sender.sendMessage(ChatColor.RED + "There is no warp with that name");
			return true;
		}
		
		Location toSend = new Location(world, x, y, z, yaw, pitch);
		
		user.getBukkitPlayer().teleport(toSend);
		
		return true;
		
	}
	
	@Override
	public final String commandHelp() {
		
		return "/warp (warp name) will teleport you to the warp";
		
	}
	
}
