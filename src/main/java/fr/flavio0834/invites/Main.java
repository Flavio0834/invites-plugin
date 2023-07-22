package fr.flavio0834.invites;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public final class Main extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        Utils.setPlugin(this);
        // Enable our class to check for new players using onPlayerJoin()
        getServer().getPluginManager().registerEvents(this, this);

        // Command Subscriptions
        this.getCommand("invitedby").setExecutor(new CommandInvitedBy());
        this.getCommand("invited").setExecutor(new CommandInvited());
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Utils.log("Player " + player.getName() + " has joined the server!");
    }
}