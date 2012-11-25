package com.gravypod.AllAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.WorldEditing.EditingObjects;
import org.bukkit.entity.Player;

public class Item extends CommandUtil {

	@Override
    public void registerSelf(AllAdmin plugin, CommandHandler commandHandler) {
		plugin.getCommand("item").setExecutor(commandHandler);
    }

	@Override
    public boolean doCommand(CommandSender sender, Command command, String cmd, String[] args) {
	    
		if (!canUseCommand(sender, cmd, true, true)) {
			return true;
		}
		
		String arg = args[0] != null ? args[0] : "1";
		
		int itemId = EditingObjects.geItemId(arg);
		
		ItemStack b = new ItemStack(itemId != -1 ? itemId : 1);
		
		
		((Player) sender).getInventory().addItem(b);
		
		sender.sendMessage(AllAdmin.getMessages("itemSpawned"));
		
		
		return true;
	    
    }

	@Override
    public String commandHelp() {
	    return null;
    }
	
}
