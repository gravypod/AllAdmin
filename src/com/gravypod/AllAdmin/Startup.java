package com.gravypod.AllAdmin;

import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Startup {
	
	AllAdmin plugin;
	
	public Startup(AllAdmin _plugin) {
	
		plugin = _plugin;
		
		loadCommands();
		
	}
	
	public void loadCommands() {
	
		String classPath = "com.gravypod.AllAdmin.commands.";
		String packageName = classPath.replaceAll("\\.", "/");
		
		try {
			
			JarInputStream jarFile = new JarInputStream(new FileInputStream(plugin.jarLocation()));
			
			JarEntry jarEntry;
			
			while(true) {
				
				jarEntry = jarFile.getNextJarEntry();
				
				if (jarEntry == null) {
					break;
				}
				
				if ((jarEntry.getName().startsWith(packageName)) && (jarEntry.getName().endsWith(".class")) && !(jarEntry.getName().contains("$"))) {
					try {
						((ICommand) Class.forName(jarEntry.getName().replaceAll("/", "\\.").replace(".class", "")).newInstance()).registerSelf(plugin);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
