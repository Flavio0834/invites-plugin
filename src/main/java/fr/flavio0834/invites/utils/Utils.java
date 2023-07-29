package fr.flavio0834.invites.utils;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Utils {
    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(final JavaPlugin plugin) {
        Utils.plugin = plugin;
    }

    public static void tell(final CommandSender sender, final String message) {
        sender.sendMessage(colorize(message));
    }

    public static void log(final String message) {
        tell(Bukkit.getConsoleSender(), "[" + Utils.getPlugin().getName() + "] " + message);
    }

    public static String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static int FirstGreaterThanXInSet(Set<String> set, int x) {
        int y = x;
        for (String s : set) {
            int z = Integer.parseInt(s);
            if ((z > x) && (z < y || y == x)) {
                y = z;
            }
        }
        return y;
    }

}
