package com.gravypod.alladmin.commands;

import net.minecraft.command.ICommand;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;

import com.gravypod.alladmin.AllAdminCommand;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.Utils;
import com.gravypod.alladmin.files.ConfigFiles;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class BroadcastCommand extends AllAdminCommand {

	public BroadcastCommand(CommandPermissions perm, String name,
			String... alias) {
		super(perm, name, alias);
	}

	@Override
	public void execute(IUser sender, String[] args) {

		if (args.length != 0) {

			String message = "";

			for (String s : args) {
				message += s + " ";
			}

			String serverMessage = Utils.translateAlternateColorCodes(ConfigFiles.broadcastFormat.replace("{MESSAGE}", message.trim()));
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(ChatMessageComponent.createFromText(serverMessage));

		} else {
			sender.send(this.getCommandUsage(sender));
			return;
		}

	}

}
