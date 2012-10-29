package com.gravypod.AllAdmin.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;

public class PlayerChat implements Listener {
	
	List<String> badWords;
	
	final Random r = new Random();
	
	final char colorCode = "&".toCharArray()[0];
	
    public PlayerChat() {
    	
    	badWords = Settings.config.getStringList("AllAdmin.Chat.bad-words");
    	
    }
	
    @EventHandler()
	public void chatEvent(AsyncPlayerChatEvent event) {
		
		if (AllAdmin.getUser(event.getPlayer().getName()).canColourChat()) {
			event.setMessage(ChatColor.translateAlternateColorCodes(colorCode, event.getMessage()));
		}
		
		final String message = event.getMessage();
		
		for (String word : message.split(" ")) {
			
			if (badWords.contains(word)) {
				
				String newWord = word;
				
				for (int i = 0; i < word.length(); i++) {
					newWord += (char) r.nextInt();
				}
				
				message.replace(word, newWord);
				
			}
			
		}
		
	}
	
}
