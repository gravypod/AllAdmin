package com.gravypod.alladmin.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.network.packet.Packet100OpenWindow;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.files.SerializedUser;
import com.gravypod.alladmin.files.UserFiles;
import com.gravypod.alladmin.minecraft.FakeCrafting;
import com.gravypod.alladmin.minecraft.FakeEnchantment;
import com.gravypod.alladmin.permissions.Group;
import com.gravypod.alladmin.permissions.Permissions;

public class AllAdminUser implements IUser {

	private final EntityPlayerMP sender;
	private final String name;
	private Group group;
	private SerializedUser serializedUser;
	private final HashMap<String, SerializedLocation> homes = new HashMap<String, SerializedLocation>();
	private boolean muted;
	
	public AllAdminUser(EntityPlayerMP sender) {

		this.sender = sender;
		this.name = sender.getCommandSenderName();
		this.serializedUser = UserFiles.loadUser(name);
		this.group = Permissions.findUser(name);
		this.muted = serializedUser.isMuted;
		
		if (serializedUser.homes != null) {
			homes.putAll(serializedUser.homes);
		}
	}

	@Override
	public boolean hasPermission(String permission) {
		return group.hasPermission(permission);

	}

	@Override
	public void setRank(String rank) {
		group = Permissions.changeRank(name, rank);
	}

	@Override
	public Group getRank() {

		return this.group;

	}

	@Override
	public void heal() {
		setHealth(Integer.MAX_VALUE);
	}

	@Override
	public void setHealth(int h) {
		sender.setHealth(h);
	}

	@Override
	public void send(String message) {
		sender.sendChatToPlayer(ChatMessageComponent.createFromText(message));
	}

	@Override
	public void translate(String key, Object ... args) {
		send(String.format(AllAdmin.getString(key), args));
	}

	@Override
	public void logout() {
		try {
			this.serializedUser.rank = getRank().getName();
			this.serializedUser.homes = getHomes();
			this.serializedUser.isMuted = isMute();
			UserFiles.unloadUser(name, this.serializedUser);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void teleport(int x, int y, int z) {
		teleport(x, y, z, 0, 0);
	}

	@Override
	public void teleport(int x, int y, int z, float pitch, float yaw) {
		sender.setPositionAndRotation(x, y, z, pitch, yaw);
	}

	@Override
	public void setHome(String name) {
		SerializedLocation location = new SerializedLocation();
		ChunkCoordinates coords = sender.getPlayerCoordinates();
		location.x = coords.posX;
		location.y = coords.posY;
		location.z = coords.posZ;
		location.pitch = sender.cameraPitch;
		location.yaw = sender.cameraYaw;
		
		homes.put(name, location);
	}

	@Override
	public void setHome() {
		setHome("default");
	}

	@Override
	public void sendHome(String name) {
		SerializedLocation location = homes.get(name);
		
		if (location == null) {
			return;
		}
		
		teleport(location.x, location.y, location.z, location.pitch, location.yaw);
		
	}

	@Override
	public void sendHome() {
		sendHome("default");
	}

	@Override
	public HashMap<String, SerializedLocation> getHomes() {
		return homes == null ? new HashMap<String, SerializedLocation>() : homes;
	}

	@Override
	public void feed() {
		feed(20);
	}

	@Override
	public void feed(int food) {
		sender.getFoodStats().addStats(food, food);
	}

	@Override
	public void openEnderChest() {
		InventoryEnderChest chest = this.sender.getInventoryEnderChest();
		sender.incrementWindowID();
        sender.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(sender.currentWindowId, 0, chest.getInvName(), chest.getSizeInventory(), chest.isInvNameLocalized()));
        sender.openContainer = new ContainerChest(sender.inventory, chest);
        sender.openContainer.windowId = sender.currentWindowId;
        sender.openContainer.addCraftingToCrafters(sender);
	}

	@Override
	public void openWorkbench() {
        sender.incrementWindowID();
        sender.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(sender.currentWindowId, 1, "Crafting", 9, true));
        sender.openContainer = new FakeCrafting(sender.inventory, sender.worldObj);
        sender.openContainer.windowId = sender.currentWindowId;
        sender.openContainer.addCraftingToCrafters(sender);
	}

	@Override
	public void toggleMute() {
		this.muted = !muted;
	}

	@Override
	public boolean isMute() {
		return muted;
	}

	@Override
	public void openEnchantment() {
		sender.incrementWindowID();
		sender.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(sender.currentWindowId, 4, "Enchanting Table - " + sender.username, 9, false));
		sender.openContainer = new FakeEnchantment(sender.inventory, sender.worldObj);
		sender.openContainer.windowId = sender.currentWindowId;
		sender.openContainer.addCraftingToCrafters(sender);
	}
	
	@Override
	public ICommandSender getICommandSender() {
		return sender;
	}
	

}
