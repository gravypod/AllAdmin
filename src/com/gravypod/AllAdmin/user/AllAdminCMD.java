/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.user;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gravypod.AllAdmin.AllAdmin;

public class AllAdminCMD implements IUser {
	
	final CommandSender sender;
	
	public AllAdminCMD(final CommandSender _sender) {
	
		sender = _sender;
	}
	
	public final String getUserName() {
	
		return "Console";
	}
	
	public final String getDisplayName() {
	
		return "Console";
	}
	
	public final Inventory getInventory() {
	
		return null;
	}
	
	public final boolean doesHaveItem(final Material m) {
	
		return false;
	}
	
	public final boolean doesHaveItem(final ItemStack m) {
	
		return false;
	}
	
	public final boolean doesHaveItem(final int m) {
	
		return false;
	}
	
	@Override
	public void sendCommandFaliure(final String command, final String why) {
	
		sender.sendMessage(AllAdmin.getMessages(why));
		
	}
	
	@Override
	public final boolean canUseCommand(final String command) {
	
		return true;
	}
	
	@Override
	public final boolean hasPermission(final String permission) {
	
		return true;
	}
	
	@Override
	public void sendMessage(final String message) {
	
		sender.sendMessage(message);
		
	}
	
	@Override
	public void saveData() {
	
	}
	
	@Override
	public boolean isPlayer() {
	
		return false;
	}
	
	@Override
	public boolean canColourChat() {
	
		return true;
	}
	
}
