package com.gravypod.AllAdmin.commands;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.CommandUtil;
import com.gravypod.AllAdmin.permissions.LoadPermissions;
import com.gravypod.AllAdmin.permissions.PermissionData;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;

public class Groups extends CommandUtil {

	enum arguments {
		promote, change, check, reload, flag, info, add;
	}

	@Override
	public void registerSelf(final AllAdmin plugin,
			final CommandHandler commandHandler) {

		plugin.getCommand("groups").setExecutor(commandHandler);
	}

	@Override
	public boolean doCommand(final CommandSender sender, final Command command,
			final String cmd, final String[] args) {

		if (!canUseCommand(sender, cmd, false, true)) {
			return true;
		}

		arguments arg = null;

		if (!(args.length >= 1)) {
			incorrectUsage(sender);
			return true;
		}

		try {
			arg = arguments.valueOf(args[0]);
		} catch (final Exception e) {
			incorrectUsage(sender);
			return true;
		}

		switch (arg) {
		case reload:
			new LoadPermissions();
			sender.sendMessage("Permissions was reloaded.");
			break;
		case change:
		case promote:
			if (!(args.length == 3)) {
				incorrectUsage(sender);
				return true;
			}

			final String groupName = args[1];
			final String userName = args[2];

			if (PermissionData.getGroups().containsKey(groupName)) {
				sender.sendMessage("That is not a real group!");
				return true;
			}

			final IUser user = AllAdmin.getUser(userName);

			if (!(user instanceof AllAdminUser)) {
				sender.sendMessage("That is not a real player!");
				return true;
			}

			((AllAdminUser) user).setGroup(groupName);

			sender.sendMessage("You have changed the group of that player!");

			break;
		case info:
		case check:

			if (!(args.length >= 2)) {
				incorrectUsage(sender);
				return true;
			}

			final IUser secifiedUser = AllAdmin.getUser(args[1]);

			if (!(secifiedUser instanceof AllAdminUser)) {
				incorrectUsage(sender);
				return true;
			}

			sender.sendMessage("That user is in "
					+ ((AllAdminUser) secifiedUser).getGroup().getName());

			break;
		case flag:
			if (!(args.length >= 4)) {
				incorrectUsage(sender);
				return true;
			}
			final IUser fromUser = AllAdmin.getUser(sender.getName());
			if (((AllAdminUser) fromUser).getGroup().getPermissions()
					.contains("edit_flags_" + args[1])) {
				final HashMap<String, Boolean> tempFlagList = ((AllAdminUser) fromUser)
						.getGroup().getFlags();
				boolean tempValue;
				if (tempValue = Boolean.parseBoolean(args[3])) {
					if (tempFlagList.containsKey(args[2])) {

						if (tempFlagList.get(args[2])) {
							tempFlagList.remove(args[2]);
							tempFlagList.put(args[2], tempValue);
							return true;
						}
					}
				}
			} else
				break;
		case add:
			// TODO figure out how to add a group in YMAL format
			break;
		}

		return true;
	}

	private String ourArguments() {

		return Arrays.toString(arguments.values());
	}

	private void incorrectUsage(final CommandSender sender) {

		sender.sendMessage("That was incorrect useage of the Groups command.");
		sender.sendMessage("You can use it correctly like this: "
				+ commandHelp());
	}

	@Override
	public String commandHelp() {

		return "/groups " + ourArguments();
	}

}
