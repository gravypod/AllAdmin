package com.gravypod.alladmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.minecraft.util.EnumChatFormatting;

public class I18n {
	
	/** Our native language */
	private final String language = Locale.getDefault().getLanguage();
	
	/** Out country */
	private final String country = Locale.getDefault().getCountry();
	
	/** Our native local */
	private final Locale locale = new Locale(language, country);
	
	/** default messages file */
	private final ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
	
	/** backup file. Messages_eu.prop */
	private final ResourceBundle defaultBundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
	
	/** Messages in our plugin.DataFolder() */
	private final ResourceBundle customMessages = ResourceBundle.getBundle("Messages", locale, new FileResClassLoader(I18n.class.getClassLoader(), AllAdmin.getDataDir()));
	
	private final HashMap<String, String> loadedMessages = new HashMap<String, String>();
	
	/**
	 * Get a message from out I18n data file.
	 * 
	 * @param message
	 *            - message title. Such as 'noPlayer'
	 * @return - What the set message really is
	 * 
	 */
	public String getColoredMessage(final String message) {
		
		
		if (!loadedMessages.containsKey(message)) {
			
			String translation = null;
			
			try {
				
				try {
					translation = Utils.translateAlternateColorCodes(customMessages.getString(message));
					
				} catch (final MissingResourceException ex) {
					
					translation = Utils.translateAlternateColorCodes(messages.getString(message));
					
				}
					
			} catch (final MissingResourceException ex) {
				translation = defaultBundle.getString(message);
			}
			
			loadedMessages.put(message, translation);
			
			return translation;
			
		}
		
		return loadedMessages.get(message);
		
	}
	

    
	/**
	 * Taken from essentials
	 */
	private static class FileResClassLoader extends ClassLoader {
		
		private final transient File dataFolder;
		
		public FileResClassLoader(final ClassLoader classLoader, final File data) {
		
			super(classLoader);
			dataFolder = data;
			
		}
		
		@Override
		public URL getResource(final String string) {
			final File file = new File(dataFolder, string);
			if (file.exists()) {
				try {
					return file.toURI().toURL();
				} catch (final MalformedURLException ex) {
				}
			}
			return super.getResource(string);
		}
		
		@Override
		public InputStream getResourceAsStream(final String string) {
		
			final File file = new File(dataFolder, string);
			
			if (file.exists()) {
				try {
					return new FileInputStream(file);
				} catch (final FileNotFoundException ex) {
				}
			}
			
			return super.getResourceAsStream(string);
			
		}
		
	}
	
}
