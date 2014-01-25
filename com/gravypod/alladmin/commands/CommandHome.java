package com.gravypod.alladmin.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.command.ICommandSender;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class CommandHome extends AllAdminCommand {

	public CommandHome(CommandPermissions permission, String name,
			String... alias) {
		super(permission, name, alias);
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		IUser user = AllAdmin.getUser(sender);

		if (user == null) {
			return super.addTabCompletionOptions(sender, args);
		}

		return new ArrayList(user.getHomes().keySet());

	}

	@Override
	public void execute(IUser sender, String[] args) {
		final HashMap<String, SerializedLocation> homes = sender.getHomes();
		if (homes.size() == 0) {
			sender.translate("nohomes");
			return;

		}

		switch (args.length) {
		case 0:
			if (!sender.getHomes().containsKey("default")) {
				sender.translate("nodefaulthome");
				return;
			}
			sender.sendHome();
			sender.translate("welcomehome");
			break;
		case 1:
			if (!sender.getHomes().containsKey(args[0])) {
				sender.translate("nohome", args[0]);
				return;
			}
			sender.sendHome(args[0]);
			sender.translate("welcomehome");
			break;
		}

	}

}
