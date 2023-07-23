package fr.flavio0834.invites.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.flavio0834.invites.utils.InvitesManager;
import fr.flavio0834.invites.utils.Utils;

import org.bukkit.command.CommandExecutor;

public class InvitedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        InvitesManager invitesManager = new InvitesManager(Utils.getPlugin());
        List<String> invitedPlayers = invitesManager.getInvitedPlayers(sender.getName());

        if (invitedPlayers.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "You have not invited anyone yet.");
        } else {
            sender.sendMessage("Number of invited players : " + ChatColor.GREEN + invitedPlayers.size());
            sender.sendMessage(String.join(", ", invitedPlayers));
        }

        Utils.log("Command /invited has been executed by " + sender.getName());
        return true;
    }
}