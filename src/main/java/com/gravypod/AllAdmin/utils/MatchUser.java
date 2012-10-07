/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.utils;

import com.gravypod.AllAdmin.AllAdmin;
import org.bukkit.entity.Player;

public class MatchUser {

    public static Player matchOnlineUser(String name) {

        return AllAdmin.getInstance().getServer().getPlayer(name);

    }

}
