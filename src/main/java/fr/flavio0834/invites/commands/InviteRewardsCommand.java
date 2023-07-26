package fr.flavio0834.invites.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.flavio0834.invites.utils.Utils;
import fr.flavio0834.invites.utils.RewardsManager;

public class InviteRewardsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            return false;
        }

        RewardsManager rewardsManager = new RewardsManager(Utils.getPlugin());

        sender.sendMessage("Rewards :");
        for (String number : rewardsManager.getAllGroups().keySet()) {
            sender.sendMessage(number + " players : " + rewardsManager.getGroup(number));
        }

        Utils.log("Command /inviterewards has been executed by " + sender.getName() + " with args : "
                + String.join(" ", args));
        return true;
    }
}