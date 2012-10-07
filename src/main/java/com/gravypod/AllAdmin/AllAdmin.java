/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin;

import com.gravypod.AllAdmin.CommandHandling.CommandHandler;
import com.gravypod.AllAdmin.listeners.PlayerListener;
import com.gravypod.AllAdmin.user.AllAdminCMD;
import com.gravypod.AllAdmin.user.AllAdminUser;
import com.gravypod.AllAdmin.user.IUser;
import com.gravypod.AllAdmin.utils.MatchUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.util.TreeMap;

public class AllAdmin extends JavaPlugin {

    private final static TreeMap<String, IUser> userList = new TreeMap<String, IUser>();

    private static AllAdmin instance;

    private static CommandHandler ch;

    @Override
    public void onEnable() {

        instance = this;

        ch = new CommandHandler();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);

        File userdata = new File(this.getDataFolder(), "userdata/");

        userdata.mkdirs();

        new Startup(this, ch);

    }

    @Override
    public void onDisable() {

        userList.clear();

    }

    public static final IUser getUser(final String name) {

        if (!userList.containsKey(name)) {

            final Player p = MatchUser.matchOnlineUser(name);

            if (p == null) {
                userList.put(name, new AllAdminCMD(Bukkit.getConsoleSender()));

            } else {

                userList.put(name, new AllAdminUser(MatchUser.matchOnlineUser(name)));

            }

        }

        return userList.get(name);
    }

    /**
     * Taken from groupmanager
     *
     * @param fileName
     * @return
     */
    public final InputStream getResourceAsStream(final String fileName) {

        return this.getClassLoader().getResourceAsStream(fileName);
    }

    public static final TreeMap<String, IUser> getUserList() {
        return userList;
    }

    public static void removeUser(String name) {
        userList.remove(name);
    }

    public static AllAdmin getInstance() {

        return instance;
    }

    public File jarLocation() {

        return new File(this.getFile().getAbsolutePath());

    }


    public static final CommandHandler getCommandHandler() {

        return ch;
    }

}
