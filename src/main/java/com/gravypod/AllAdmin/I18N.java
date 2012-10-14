package com.gravypod.AllAdmin;

import java.util.Locale;
import java.util.ResourceBundle;
import org.bukkit.ChatColor;

public class I18N {
	
	final String language = Locale.getDefault().getLanguage();
	
	final String country = Locale.getDefault().getCountry();
	
	final char colorCode = "&".toCharArray()[0];
	
	final Locale locale = new Locale(language, country);
	
	final ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
	
	public String getColoredMessage(final String message) {
		
		return ChatColor.translateAlternateColorCodes(colorCode, messages.getString(message));
		
	}
	
}
