/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigLoad {

    static AllAdmin plugin;

    static FileConfiguration config;

    static File configFile;

    static File warpsList;

    public ConfigLoad(final AllAdmin _plugin, final File _configFile) {

        plugin = _plugin;
        configFile = _configFile;
        warpsList = new File(_plugin.getDataFolder(), "warps.yml");
        loadData();

    }

    public static void loadData() {

        FileConfiguration cheapConfig = plugin.getConfig();

        try {

            cheapConfig.load(configFile);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            Settings.useHomes = cheapConfig.getBoolean("AllAdmin.use-homes");
            Settings.useWarps = cheapConfig.getBoolean("AllAdmin.use-warps");

            try {
                cheapConfig.save(configFile);
            } catch (IOException e) {
            }

        }

        FileConfiguration warpYamlFile = new YamlConfiguration();

        try {

            warpYamlFile.load(warpsList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            Settings.warpsList = warpsList;
            Settings.warpsYamlFile = warpYamlFile;

        }

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}