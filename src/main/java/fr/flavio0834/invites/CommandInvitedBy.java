package fr.flavio0834.invites;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.command.CommandExecutor;

public class CommandInvitedBy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            return false;
        }

        InvitesManager invitesManager = new InvitesManager(Utils.getPlugin());
        String inviter = invitesManager.getInviter(sender.getName());
        if (inviter != null) {
            sender.sendMessage(ChatColor.RED + "You already defined who invited you. This can't be changed.");
        } else {
            if (!invitesManager.isPlayerRegistered(args[0])) {
                sender.sendMessage(ChatColor.RED + "This player is not registered.");
                return true;
            }
            invitesManager.addInvite(sender.getName(), args[0]);
            sender.sendMessage(
                    ChatColor.GREEN + "You have been registered as invited by " + args[0]
                            + ". You cannot use this command anymore.");
        }

        Utils.log("Command /invitedby has been executed by " + sender.getName() + " with args : " + args[0]);
        return true;
    }
}