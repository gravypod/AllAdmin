package com.gravypod.alladmin;

import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;

public class EventHandler {
	@ForgeSubscribe
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
}
