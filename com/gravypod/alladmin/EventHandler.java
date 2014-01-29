package com.gravypod.alladmin;

import java.util.HashMap;

import com.gravypod.alladmin.commands.wrapped.BanCommand;
import com.gravypod.alladmin.commands.wrapped.BanIpCommand;
import com.gravypod.alladmin.commands.wrapped.DebugCommand;
import com.gravypod.alladmin.commands.wrapped.DefaultGameModeCommand;
import com.gravypod.alladmin.commands.wrapped.DeopCommand;
import com.gravypod.alladmin.commands.wrapped.DifficultyCommand;
import com.gravypod.alladmin.commands.wrapped.EffectCommand;
import com.gravypod.alladmin.commands.wrapped.EnchantCommand;
import com.gravypod.alladmin.commands.wrapped.GameModeCommand;
import com.gravypod.alladmin.commands.wrapped.GameRuleCommand;
import com.gravypod.alladmin.commands.wrapped.GiveCommand;
import com.gravypod.alladmin.commands.wrapped.HelpCommand;
import com.gravypod.alladmin.commands.wrapped.KickCommand;
import com.gravypod.alladmin.commands.wrapped.ListCommand;
import com.gravypod.alladmin.commands.wrapped.OpCommand;
import com.gravypod.alladmin.commands.wrapped.PardonCommand;
import com.gravypod.alladmin.commands.wrapped.PardonIpCommand;
import com.gravypod.alladmin.commands.wrapped.PlaySoundCommand;
import com.gravypod.alladmin.commands.wrapped.PlayerTimeoutCommand;
import com.gravypod.alladmin.commands.wrapped.SaveAllCommand;
import com.gravypod.alladmin.commands.wrapped.SaveOffCommand;
import com.gravypod.alladmin.commands.wrapped.SaveOnCommand;
import com.gravypod.alladmin.commands.wrapped.ScoreboardCommand;
import com.gravypod.alladmin.commands.wrapped.ServerEmoteCommand;
import com.gravypod.alladmin.commands.wrapped.ServerMessageCommand;
import com.gravypod.alladmin.commands.wrapped.ServerTpCommand;
import com.gravypod.alladmin.commands.wrapped.SetSpawnpointCommand;
import com.gravypod.alladmin.commands.wrapped.ShowSeedCommand;
import com.gravypod.alladmin.commands.wrapped.SpreadPlayersCommand;
import com.gravypod.alladmin.commands.wrapped.StopCommand;
import com.gravypod.alladmin.commands.wrapped.TimeCommand;
import com.gravypod.alladmin.commands.wrapped.ToggleDownfallCommand;
import com.gravypod.alladmin.commands.wrapped.WeatherCommand;
import com.gravypod.alladmin.commands.wrapped.WhitelistCommand;
import com.gravypod.alladmin.commands.wrapped.XPCommand;
import com.gravypod.alladmin.permissions.PermissionManager.CommandPermissions;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class EventHandler {
	
	@ForgeSubscribe(priority = EventPriority.HIGHEST,receiveCanceled=false)
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
	
	@ForgeSubscribe(priority = EventPriority.LOW,receiveCanceled=false)
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
	
	/*@ForgeSubscribe(receiveCanceled = false, priority = EventPriority.HIGHEST)
	public void command(CommandEvent event) { // Handle negated permission
		String name = event.command.getCommandName();
		
		ICommandSender sender = event.sender;
		
		CommandBase cmd = this.replacedCommands.get(name);
		
		if (cmd == null) {
			return;
		}
		
		if (cmd.canCommandSenderUseCommand(sender)) {
			event.setCanceled(true);
			cmd.processCommand(sender, event.parameters);
		} else {
			System.out.println(name + " " + sender.getCommandSenderName() + " could not use");
		}
		
	}*/
	
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
