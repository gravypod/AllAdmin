package com.gravypod.alladmin.user;

import java.util.HashMap;
import java.util.Locale;

import net.minecraft.command.ICommandSender;
import net.minecraft.scoreboard.ServerCommandScoreboard;
import net.minecraft.scoreboard.ServerCommandTestFor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.MinecraftServerGui;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.permissions.Group;
import com.gravypod.alladmin.permissions.Permissions;

public class AllAdminConsole implements IUser {
	
	@Override
	public boolean hasPermission(String permission) {
		return true;
	}

	@Override
	public void setRank(String rank) {
	}

	@Override
	public Group getRank() {
		return Permissions.getGroup(Permissions.getDefaultRank());
	}

	@Override
	public void heal() {
	}

	@Override
	public void setHealth(int h) {
	}

	@Override
	public void send(String message) {
		MinecraftServer.getServer().logInfo(message);
	}

	@Override
	public void translate(String key, Object... args) {
		send(String.format(AllAdmin.getString(key), args));
	}

	@Override
	public void logout() {
	}

	@Override
	public void teleport(int x, int y, int z) {
	}

	@Override
	public void teleport(int x, int y, int z, float pitch, float yaw) {
	}

	@Override
	public void setHome(String name) {
	}

	@Override
	public void setHome() {
	}

	@Override
	public void sendHome(String name) {
	}

	@Override
	public void sendHome() {
	}

	@Override
	public HashMap<String, SerializedLocation> getHomes() {
		return new HashMap<String, SerializedLocation>();
	}

	@Override
	public void feed() {
	}

	@Override
	public void feed(int food) {
	}

	@Override
	public void openEnderChest() {
	}

	@Override
	public void openWorkbench() {
	}

	@Override
	public void toggleMute() {
	}

	@Override
	public boolean isMute() {
		return false;
	}

	@Override
	public void openEnchantment() {
	}

	@Override
	public ICommandSender getICommandSender() {
		return null;
	}

	@Override
	public boolean isInvisible() {
		return false;
	}

	@Override
	public void setInvisible(boolean on) {
	}

	@Override
	public String getUsername() {
		return "UNKNOWN";
	}

	@Override
	public void changeDimension(int dim) {
	}

	@Override
	public int getDimension() {
		return 0;
	}

}
