package com.gravypod.alladmin.minecraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;

public class FakeChest extends ContainerChest {
	IInventory other, user;
	public FakeChest(IInventory other, IInventory user) {
		super(other, user);
		this.other = other;
		this.user = user;
	}
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}
}
