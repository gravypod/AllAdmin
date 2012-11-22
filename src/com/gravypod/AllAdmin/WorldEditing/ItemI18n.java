package com.gravypod.AllAdmin.WorldEditing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.bukkit.Material;

import com.gravypod.AllAdmin.AllAdmin;

public class ItemI18n {
	
	/** Our native language */
	private final String language = Locale.getDefault().getLanguage();
	
	/** Out country */
	private final String country = Locale.getDefault().getCountry();
	
	/** Our native local */
	private final Locale locale = new Locale(language, country);
	
	/** default messages file */
	private final ResourceBundle messages = ResourceBundle.getBundle("Items", locale);
	
	/** backup file. Messages_eu.prop */
	private final ResourceBundle defaultBundle = ResourceBundle.getBundle("Items", Locale.ENGLISH);
	
	/** Messages in our plugin.DataFolder() */
	private final ResourceBundle customMessages = ResourceBundle.getBundle("Items", locale, new FileResClassLoader(ItemI18n.class.getClassLoader(), AllAdmin.getInstance()));
	
	public int getItem(final String message) {
	
		int id;
		
		try {
			
			try {
				
				try {
					id = Integer.parseInt(customMessages.getString(message));
				} catch (Exception e) {
					Material m = Material.getMaterial(customMessages.getString(message).toUpperCase());
					
					id = m != null ? m.getId() : -1;
				}
				
			} catch (final MissingResourceException ex) {
				try {
					id = Integer.parseInt(messages.getString(message));
				} catch (Exception e) {
					Material m = Material.getMaterial(messages.getString(message).toUpperCase());
					
					id = m != null ? m.getId() : -1;
				}
			}
			
		} catch (final MissingResourceException ex) {
			try {
				id = Integer.parseInt(defaultBundle.getString(message));
			} catch (Exception e) {
				
				Material m = Material.getMaterial(defaultBundle.getString(message).toUpperCase());
				
				id = m != null ? m.getId() : -1;
			}
		}
		
		return id;
		
	}
	
	/**
	 * Taken from essentials
	 */
	private static class FileResClassLoader extends ClassLoader {
		
		private final transient File dataFolder;
		
		public FileResClassLoader(final ClassLoader classLoader, final AllAdmin allAdmin) {
		
			super(classLoader);
			dataFolder = allAdmin.getDataFolder();
			
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
