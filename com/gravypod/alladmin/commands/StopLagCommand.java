package com.gravypod.alladmin.commands;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import com.gravypod.alladmin.IUser;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

public class StopLagCommand extends AllAdminCommand {

	boolean stoplagOn = false;
	//HashMap<Integer, boolean[]> spawnTypes = new HashMap<Integer, boolean[]>();

	public StopLagCommand(CommandPermissions perm, String name, String... alias) {
		super(perm, name, alias);
	}

	@Override
	void execute(IUser sender, String[] args) {

		WorldServer[] worlds = MinecraftServer.getServer().worldServers;
		
		if (!stoplagOn) {
			int removed = 0;
			for (WorldServer w : worlds) {
				
				for (Object o : w.loadedEntityList.toArray()) {

					if (!(o instanceof EntityVillager) && !(o instanceof EntityWolf) && !(o instanceof EntityPlayer)) {

						((Entity) o).setDead();
						removed++;
					}

				}
				
				
				//spawnTypes.put(w.provider.dimensionId, new boolean[] {
						
			//	});
				
				//w.setAllowedSpawnTypes(false, false);

			}
			sender.translate("stoplag", removed);
		} else {
			
			for (WorldServer w : worlds) {
				
				//w.setAllowedSpawnTypes(true, true);
				
			}
			
		}

	}

}
