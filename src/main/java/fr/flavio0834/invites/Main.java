package fr.flavio0834.invites;

import org.bukkit.plugin.java.JavaPlugin;

import fr.flavio0834.invites.commands.InvitedCommand;
import fr.flavio0834.invites.commands.InviteRewardCommand;
import fr.flavio0834.invites.commands.InviteRewardsCommand;
import fr.flavio0834.invites.commands.InvitedByCommand;
import fr.flavio0834.invites.utils.InvitesManager;
import fr.flavio0834.invites.utils.RewardsManager;
import fr.flavio0834.invites.utils.Utils;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public final class Main extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();
    InvitesManager invitesManager = new InvitesManager(this);
    RewardsManager rewardsManager = new RewardsManager(this);

    @Override
    public void onEnable() {
        Utils.setPlugin(this);

        config.addDefault("testConfig", true);
        config.options().copyDefaults(true);
        saveConfig();

        // Enable our class to check for new players using onPlayerJoin()
        getServer().getPluginManager().registerEvents(this, this);

        // Command Subscriptions
        this.getCommand("invitedby").setExecutor(new InvitedByCommand());
        this.getCommand("invited").setExecutor(new InvitedCommand());
        this.getCommand("invitereward").setExecutor(new InviteRewardCommand());
        this.getCommand("inviterewards").setExecutor(new InviteRewardsCommand());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        // Init the inviter to null for this player if it is his first join
        invitesManager.loadInvites();
        if (!invitesManager.isPlayerRegistered(playerName)) {
            invitesManager.addInvite(playerName, null);
        }

        rewardsManager.loadRewards();
        rewardsManager.checkRewardsState(player);
    }
}