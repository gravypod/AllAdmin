package com.gravypod.alladmin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.command.CommandHandler;
import net.minecraft.command.CommandServerEmote;
import net.minecraft.command.CommandServerMessage;
import net.minecraft.command.CommandServerSay;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;

import com.gravypod.alladmin.commands.AllAdminCommandManager;
import com.gravypod.alladmin.commands.Commands;
import com.gravypod.alladmin.files.PermissionFiles;
import com.gravypod.alladmin.user.AllAdminConsole;
import com.gravypod.alladmin.user.AllAdminUser;

import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
// used in 1.6.2
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "AllAdmin", name = "AllAdmin", version = AllAdmin.version)
@NetworkMod(clientSideRequired = false)
public class AllAdmin {
	
	public static final String version = "0.0.0";
	
	public static boolean running;
	
	public static HashMap<String, IUser> users = new HashMap<String, IUser>();
	
	public static final I18n localization = new I18n();

	@Mod.EventHandler
	public static void preInit(FMLServerStartingEvent event) {
		
		running = event.getSide().isServer();
		
		if (!running) {
			return;
		}
		
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		
		System.out.println("[AllAdmin] Starting! Created by gravypod. Version " + version);
		if (getDataDir().exists() || !getDataDir().isDirectory()) {
			getDataDir().mkdirs();
			try {
				PermissionFiles.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		CommandHandler ch = (CommandHandler) MinecraftServer.getServer().getCommandManager();
		AllAdminCommandManager manager = new AllAdminCommandManager(ch);
		
		for (Field f : MinecraftServer.class.getFields()) {
			
			if (f.getType().isInstance(ch)) {
				try {
					System.out.println("AllAdmin has found the command handler and is now wrapping it. Any errors after this point should be sent to gravypod.");
					f.setAccessible(true);
					f.set(MinecraftServer.getServer(), manager);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		for (Commands commands : Commands.values()) {
			manager.registerCommand(commands.getCommand());
		}
		GameRegistry.registerPlayerTracker(new IPlayerTracker() {
			
			@Override
			public void onPlayerRespawn(EntityPlayer player) {
			}
			
			@Override
			public void onPlayerLogout(EntityPlayer player) {
				IUser user = users.remove(player.username);
				user.logout();
			}
			
			@Override
			public void onPlayerLogin(EntityPlayer player) {
			}
			
			@Override
			public void onPlayerChangedDimension(EntityPlayer player) {
			}
		});
	}
	
	@Mod.EventHandler
	public static void stopping(FMLServerStoppingEvent event) {
		if (!running) {
			return;
		}
		
		for (IUser user : users.values()) {
			user.logout();
		}
		
		try {
			PermissionFiles.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static File getDataDir() {
		return new File("config/AllAdmin");
	}
	
	/**
	 * Get an {@link IUser}
	 * @param name - name of the users
	 * @return IUser who has the name provided or null if no user existed or the plugin has not loaded
	 */
	public static IUser getUser(Object sender) {
		
		if (!running) {
			return null;
		}
		
		String name = null;
		
		if (sender instanceof EntityPlayerMP) {
			name = ((EntityPlayerMP) sender).getCommandSenderName();
		} else if (sender instanceof String) {
			name = (String) sender;
		}
		
		if (!users.containsKey(name)) {
			if (sender instanceof EntityPlayerMP) {
				users.put(name, new AllAdminUser((EntityPlayerMP) sender));
			} else if (sender instanceof String) {
				users.put(name, new AllAdminUser(MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername((String) sender)));
			} else {
				users.put(name, new AllAdminConsole());	
			}
		}
		
		return users.get(name);
		
	}

	public static String getString(String name) {
		return localization.getColoredMessage(name);
	}

}