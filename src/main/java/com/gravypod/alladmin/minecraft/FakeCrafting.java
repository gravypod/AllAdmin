package com.gravypod.alladmin.minecraft;

import com.gravypod.alladmin.commands.WorkbenchCommand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;
/**
 * Fake crafting table GUI to allow for {@link WorkbenchCommand}
 * @author gravypod
 *
 */
public class FakeCrafting extends ContainerWorkbench {

	public FakeCrafting(InventoryPlayer par1InventoryPlayer, World par2World) {
		super(par1InventoryPlayer, par2World, 0, 0, 0);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}

}
