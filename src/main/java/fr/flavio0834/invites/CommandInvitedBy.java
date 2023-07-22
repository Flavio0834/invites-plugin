package fr.flavio0834.invites;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class CommandInvitedBy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utils.log("Command /invitedby has been executed by " + sender.getName());
        return true;
    }
}