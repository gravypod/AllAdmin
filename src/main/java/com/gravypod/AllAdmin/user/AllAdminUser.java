/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.gravypod.AllAdmin.user;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.utils.PermissionsTesting;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class AllAdminUser implements IUser {

    private Player bukkitPlayer;

    private Location lastLocation;

    private File userDataFile;

    private FileConfiguration userData = new YamlConfiguration();

    public AllAdminUser(Player _bukkitPlayer) {

        bukkitPlayer = _bukkitPlayer;

        userDataFile = new File(AllAdmin.getInstance().getDataFolder(), "userdata/" + bukkitPlayer.getName() + ".yml");

        try {

            userData.load(userDataFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getUserName() {

        return bukkitPlayer.getName();
    }

    public String getDisplayName() {

        return bukkitPlayer.getDisplayName();
    }

    public Inventory getInventory() {

        return bukkitPlayer.getInventory();
    }

    public boolean doesHaveItem(Material m) {

        return getInventory().contains(m);
    }

    public boolean doesHaveItem(ItemStack m) {

        return getInventory().contains(m);
    }

    public boolean doesHaveItem(int m) {

        return getInventory().contains(m);
    }

    public void sendCommandFaliure(String command) {

        bukkitPlayer.sendMessage(ChatColor.RED + "We could not exacute the command " + command + "!");
    }

    public boolean canUseCommand(String command) {

        return PermissionsTesting.canUseCommand(bukkitPlayer, command);
    }

    public boolean hasPermission(String permission) {

        return PermissionsTesting.hasPermission(bukkitPlayer, permission);
    }

    public Player getBukkitPlayer() {

        return this.bukkitPlayer;
    }

    public Location getLastLocation() {

        return lastLocation;
    }

    public void setHome(Location loc) {

        userData.set("homes.world", loc.getWorld().getName());
        userData.set("homes.x", loc.getX());
        userData.set("homes.y", loc.getY());
        userData.set("homes.z", loc.getZ());

        try {
            userData.save(userDataFile);
        } catch (IOException e) {
        }

    }

    public Location getHome() {

        return new Location(AllAdmin.getInstance().getServer().getWorld(userData.getString("homes.world")), userData.getDouble("homes.x"), userData.getDouble("homes.y"), userData.getDouble("homes.z"));
    }

    public void updateLastLocation() {

        this.lastLocation = this.bukkitPlayer.getLocation();
    }

    @Override
    public void sendMessage(String message) {

        this.bukkitPlayer.sendMessage(message);

    }
}
