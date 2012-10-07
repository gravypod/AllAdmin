/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.listeners;

import com.gravypod.AllAdmin.AllAdmin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public static void onLogin(PlayerLoginEvent event) {

        if (event.getPlayer().getName().contains("["))
            return;

        AllAdmin.getUser(event.getPlayer().getName());

    }

    @EventHandler(ignoreCancelled = false)
    public static void onLogout(PlayerQuitEvent event) {

        if (event.getPlayer().getName().contains("["))
            return;

        AllAdmin.removeUser(event.getPlayer().getName());

    }

}
