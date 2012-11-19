package com.gravypod.AllAdmin.storage;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 
 * @param <T>
 *            - the type of data you are setting.
 * 
 */
public class AddData<T> implements ISave {
	
	private final T data;
	
	private final FileConfiguration config;
	
	private final String path;
	
	public AddData(final T data, final String path, final FileConfiguration config) {
	
		this.data = data;
		this.path = path;
		this.config = config;
		
	}
	
	@Override
	public void save() throws IOException {
	
		config.set(path, data);
		
	}
	
}
