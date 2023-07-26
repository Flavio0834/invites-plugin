package fr.flavio0834.invites.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;

public class LuckpermsManager {
    LuckPerms api = LuckPermsProvider.get();

    public LuckpermsManager(JavaPlugin plugin) {

    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public static void addPlayerToGroup(Player player, String group) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " group add " + group);
        Utils.log("Player " + player.getName() + " has been added to group " + group);
    }

    public boolean doesGroupExist(String group) {
        Group x = api.getGroupManager().getGroup(group);
        return x != null;
    }

}
