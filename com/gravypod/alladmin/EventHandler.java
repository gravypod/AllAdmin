package com.gravypod.alladmin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class EventHandler {
	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void chatMessage(ServerChatEvent events) {
		IUser user = AllAdmin.getUser(events.player);
		
		if (user.isMute()) {
			events.setCanceled(true);
		}
		
		String format = user.getRank().getMessageFormat();
		format = format.replace("{USERNAME}", events.username);
		format = format.replace("{GROUPNAME}", user.getRank().getName());
		format = format.replace("{WORLD}", events.player.worldObj.getWorldInfo().getWorldName());
		format = format.replace("{MESSAGE}", events.message);
		
		
		events.component = ChatMessageComponent.createFromText(Utils.translateAlternateColorCodes(format));
	}
	
	@ForgeSubscribe(priority = EventPriority.LOW)
	public void hurtEvent(LivingHurtEvent event) {
		
		if (!(event.entity instanceof EntityPlayer)) {
			return;
		}
		
		IUser user = AllAdmin.getUser(event.entity);
		
		if (user == null) {
			return;
		}
		
		if (user.hasGodMode()) {
			event.setCanceled(true);
		}
		
	}
	
	@ForgeSubscribe
	public void blockBreak(BreakEvent event) {
		
		IUser user = AllAdmin.getUser(event.getPlayer());
		
		if (user == null) {
			return;
		}
		
		
		if (user.isJailed() || !user.canBreak(event.block.blockID)) {
			event.setCanceled(true);
		}
		
	}
	
	/*@ForgeSubscribe
	public void interact(BreakSpeed event) {
		
		IUser user = AllAdmin.getUser(event.entityPlayer);
		
		if (user == null) {
			return;
		}
		
		
		if (!user.canPlace(event.block.blockID)) {
			event.setCanceled(true);
		}
		
	}*/
	
}
