package com.gravypod.alladmin.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.world.ChunkCoordIntPair;

import com.gravypod.alladmin.AllAdmin;
import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.files.SerializedLocation;
import com.gravypod.alladmin.files.SerializedUser;
import com.gravypod.alladmin.files.UserFiles;
import com.gravypod.alladmin.minecraft.FakeCrafting;
import com.gravypod.alladmin.minecraft.FakeEnchantment;
import com.gravypod.alladmin.permissions.Group;
import com.gravypod.alladmin.permissions.Permissions;
import com.gravypod.alladmin.permissions.Permissions.CommandPermissions;

public class AllAdminUser implements IUser {

	private final EntityPlayerMP sender;
	private final String name;
	private Group group;
	private SerializedUser serializedUser;
	private boolean isInvisible;
	private final HashMap<String, SerializedLocation> homes = new HashMap<String, SerializedLocation>();
	private boolean muted;

	public AllAdminUser(EntityPlayerMP sender) {

		this.sender = sender;
		this.name = getHandle().getCommandSenderName();
		this.serializedUser = UserFiles.loadUser(name);
		this.group = Permissions.getGroup(serializedUser.rank);
		this.muted = serializedUser.isMuted;
		this.isInvisible = serializedUser.isInvisible;

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
		getHandle().setHealth(h);
	}

	@Override
	public void send(String message) {
		getHandle().sendChatToPlayer(
				ChatMessageComponent.createFromText(message));
	}

	@Override
	public void translate(String key, Object... args) {
		send(String.format(AllAdmin.getString(key), args));
	}

	@Override
	public void logout() {
		try {
			this.serializedUser.rank = getRank().getName();
			this.serializedUser.homes = getHomes();
			this.serializedUser.isMuted = isMute();
			this.serializedUser.isInvisible = isInvisible();
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
		getHandle().setPositionAndRotation(x, y, z, pitch, yaw);
	}

	@Override
	public void setHome(String name) {
		SerializedLocation location = new SerializedLocation();
		ChunkCoordinates coords = getHandle().getPlayerCoordinates();
		location.x = coords.posX;
		location.y = coords.posY;
		location.z = coords.posZ;
		location.pitch = getHandle().cameraPitch;
		location.yaw = getHandle().cameraYaw;
		location.dim = getDimension();

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

		if (location.dim != getDimension()) {
			changeDimension(location.dim);
		}

		teleport(location.x, location.y, location.z, location.pitch,
				location.yaw);

	}

	@Override
	public void sendHome() {
		sendHome("default");
	}

	@Override
	public HashMap<String, SerializedLocation> getHomes() {
		return homes == null ? new HashMap<String, SerializedLocation>()
				: homes;
	}

	@Override
	public void feed() {
		feed(20);
	}

	@Override
	public void feed(int food) {
		getHandle().getFoodStats().addStats(food, food);
	}

	@Override
	public void openEnderChest() {
		InventoryEnderChest chest = this.getHandle().getInventoryEnderChest();
		getHandle().incrementWindowID();
		getHandle().playerNetServerHandler
				.sendPacketToPlayer(new Packet100OpenWindow(
						getHandle().currentWindowId, 0, chest.getInvName(),
						chest.getSizeInventory(), chest.isInvNameLocalized()));
		getHandle().openContainer = new ContainerChest(getHandle().inventory,
				chest);
		getHandle().openContainer.windowId = getHandle().currentWindowId;
		getHandle().openContainer.addCraftingToCrafters(sender);
	}

	@Override
	public void openWorkbench() {
		getHandle().incrementWindowID();
		getHandle().playerNetServerHandler
				.sendPacketToPlayer(new Packet100OpenWindow(
						getHandle().currentWindowId, 1, "Crafting", 9, true));
		getHandle().openContainer = new FakeCrafting(getHandle().inventory,
				getHandle().worldObj);
		getHandle().openContainer.windowId = getHandle().currentWindowId;
		getHandle().openContainer.addCraftingToCrafters(sender);
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
		getHandle().incrementWindowID();
		getHandle().playerNetServerHandler
				.sendPacketToPlayer(new Packet100OpenWindow(
						getHandle().currentWindowId, 4, "Enchanting Table - "
								+ getHandle().username, 9, false));
		getHandle().openContainer = new FakeEnchantment(getHandle().inventory,
				getHandle().worldObj);
		getHandle().openContainer.windowId = getHandle().currentWindowId;
		getHandle().openContainer.addCraftingToCrafters(sender);
	}

	@Override
	public ICommandSender getICommandSender() {
		return getHandle();
	}

	@Override
	public boolean isInvisible() {
		return isInvisible;
	}

	@Override
	public void setInvisible(boolean on) {
		this.isInvisible = on;
	}

	@Override
	public String getUsername() {
		return getHandle().username;
	}

	@Override
	public void changeDimension(int dim) {
		getHandle().travelToDimension(dim);
	}

	@Override
	public int getDimension() {
		return getHandle().worldObj.provider.dimensionId;
	}

	@Override
	public boolean hasPermission(CommandPermissions perm) {
		return hasPermission(perm.getPermission());
	}

	@Override
	public void allowFlight() {
		boolean currentToggle = getHandle().capabilities.allowFlying;
		getHandle().capabilities.allowFlying = !currentToggle;
		getHandle().sendPlayerAbilities();
	}

	@Override
	public EntityPlayerMP getHandle() {
		return sender;
	}

}