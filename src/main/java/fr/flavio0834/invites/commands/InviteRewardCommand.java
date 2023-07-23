package fr.flavio0834.invites.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.flavio0834.invites.utils.Utils;
import fr.flavio0834.invites.utils.RewardsManager;

public class InviteRewardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            return false;
        }

        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage("The first argument must be a number.");
            return false;
        }

        RewardsManager rewardsManager = new RewardsManager(Utils.getPlugin());

        if (rewardsManager.isRewardDefined(args[0])) {
            sender.sendMessage("This reward was defined with group : " + rewardsManager.getGroup(args[0]));
        }
        rewardsManager.addReward(args[0], args[1]);
        sender.sendMessage("The reward for inviting " + args[0] + " players is now group : " + args[1]);

        Utils.log("Command /inviterewards has been executed by " + sender.getName() + " with args : "
                + String.join(" ", args));
        return true;
    }
}