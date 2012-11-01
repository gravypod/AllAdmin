/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.gravypod.AllAdmin.utils;

import org.bukkit.entity.Player;

import com.gravypod.AllAdmin.AllAdmin;

public class MatchUser {
	
	public static Player matchOnlineUser(final String name) {
	
		return AllAdmin.getInstance().getServer().getPlayer(name);
		
	}
	
}
