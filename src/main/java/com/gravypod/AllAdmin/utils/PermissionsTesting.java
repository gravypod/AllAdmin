/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.utils;

import org.bukkit.entity.Player;

public class PermissionsTesting {

    public static boolean canUseCommand(Player player, String command) {
        return hasPermission(player, "alladmin.commands." + command);
    }

    public static boolean hasPermission(Player player, String node) {

        return player.hasPermission(node);

    }

}
