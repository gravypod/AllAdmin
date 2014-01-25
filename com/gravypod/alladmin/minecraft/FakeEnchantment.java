package com.gravypod.alladmin.minecraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.world.World;

public class FakeEnchantment extends ContainerEnchantment {

	public FakeEnchantment(InventoryPlayer inventory, World world) {
		super(inventory, world, 0, 0, 0);
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}
	
}
