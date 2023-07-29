package fr.flavio0834.invites.commands;

import java.util.List;

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
        List<String> invitedPlayers = null;

        if (args.length == 0) {
            invitedPlayers = invitesManager.getInvitedPlayers(sender.getName());
        } else if ((args.length == 1) && sender.hasPermission("invites.invited.other")) {
            invitedPlayers = invitesManager.getInvitedPlayers(args[0]);
        } else {
            return false;
        }

        if (!invitedPlayers.isEmpty()) {
            sender.sendMessage("Number of invited players : " + ChatColor.GREEN + invitedPlayers.size());
            sender.sendMessage(String.join(", ", invitedPlayers));
        } else {
            sender.sendMessage(ChatColor.RED + (invitedPlayers.isEmpty() ? "You have not" : "This player has not")
                    + " invited anyone yet.");
        }

        Utils.log(
                "Command /invited has been executed by " + sender.getName() + "with args : " + String.join(" ", args));
        return true;
    }
}