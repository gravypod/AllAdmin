/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin;

import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.CommandHandling.ICommand;

import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Startup {

	final AllAdmin plugin;

    public Startup(final AllAdmin _plugin, final CommandHandler ch) {

        plugin = _plugin;
        
        new ConfigHandle(_plugin);
        
        loadCommands(ch);
        
    }

    public void loadCommands(final CommandHandler ch) {

    	final String classPath = "com.gravypod.AllAdmin.commands.";
    	final String packageName = classPath.replaceAll("\\.", "/");

        try {

        	final JarInputStream jarFile = new JarInputStream(new FileInputStream(plugin.jarLocation()));

            JarEntry jarEntry;

            while((jarEntry = jarFile.getNextJarEntry()) != null) {

                if ((jarEntry.getName().startsWith(packageName)) && (jarEntry.getName().endsWith(".class")) && !(jarEntry.getName().contains("$"))) {
                    try {
                        // Let it snow, let it snow, let it snow!
                        ((ICommand) Class.forName(jarEntry.getName().replaceAll("/", "\\.").replace(".class", "")).newInstance()).registerSelf(plugin, ch);
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
