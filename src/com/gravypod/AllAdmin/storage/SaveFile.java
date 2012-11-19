package com.gravypod.AllAdmin.storage;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

public class SaveFile implements ISave {
	
	private final File destFile;
	
	private final FileConfiguration config;
	
	public SaveFile(final File f, final FileConfiguration fc) {
	
		destFile = f;
		config = fc;
		
	}
	
	@Override
	public void save() throws IOException {
	
		if (!destFile.canWrite()) {
			throw new IOException("Could not write to file. Check the files permissions");
		}
		
		if (!destFile.exists()) {
			
			if (!destFile.createNewFile()) {
				throw new IOException("Could not write to file. Check the files permissions");
			}
			
		}
		
		config.save(destFile);
		
	}
	
}
