package com.gravypod.AllAdmin.utils;

import com.gravypod.AllAdmin.AllAdmin;
import org.bukkit.entity.Player;

public class MatchUser {

    public static Player matchOnlineUser(String name) {

        return AllAdmin.getInstance().getServer().getPlayer(name);

    }

}
