/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.utils;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.Settings;
import com.gravypod.AllAdmin.user.AllAdminUser;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * A work in progress utils class for EVERYTHING you could need for teleporting. Needs work
 * @author gravypod
 *
 */
public class TeleportUtils {
	
	public static Location getLocation(FileConfiguration db, String location, String name) {
		
		final double x = Settings.warpsYamlFile.getDouble(location + "." + name + ".x");
		final double y = Settings.warpsYamlFile.getDouble(location + "." + name + ".y");
		final double z = Settings.warpsYamlFile.getDouble(location + "." + name + ".z");
		final float pitch = (float) Settings.warpsYamlFile.get(location + "." + name + ".pitch");
		final float yaw = (float) Settings.warpsYamlFile.get(location + "." + name + ".yaw");
		final World world = AllAdmin.getInstance().getServer().getWorld(Settings.warpsYamlFile.getString(location + "." + name + ".world"));
	
		if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z) || world == null || Float.isNaN(pitch) || Float.isNaN(yaw)) {
			return null;
		}
		
		return new Location(world, x, y, z, yaw, pitch);
		
	}
	
    public static void delayedTeleport(final AllAdminUser player, final Location toGo, final long time) {

        player.sendMessage(ChatColor.AQUA + "Do not move for " + time + " seconds");

        player.updateLastLocation();

        final Location playersLocation = player.getLastLocation();

        AllAdmin.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(AllAdmin.getInstance(), new Runnable() {

            @Override
            public void run() {
                player.updateLastLocation();

                if ((playersLocation != player.getLastLocation()))
                    player.sendMessage(ChatColor.RED + "You have not been teleported. You moved");

                player.getBukkitPlayer().teleport(safeLocation(toGo));

                player.sendMessage(ChatColor.RED + "You have been teleported");

            }

        }, time);

    }

    public static final Location safeLocation(final Location spawnLocation) {

        Location blockLocation = spawnLocation;
        double yLoc = blockLocation.getY();

        //find the first non air block below us
        while (blockLocation.getBlock().getType() != Material.AIR) {
            blockLocation.setY(yLoc + 1);
        }

        // set to 1 block up so we are not sunk in the ground
        blockLocation.setY(yLoc + 1);

        return blockLocation;

    }

}
