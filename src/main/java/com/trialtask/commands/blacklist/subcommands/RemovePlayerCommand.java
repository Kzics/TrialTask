package com.trialtask.commands.blacklist.subcommands;

import com.trialtask.commands.ICommand;
import com.trialtask.manager.BlacklistManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemovePlayerCommand implements ICommand {

    private final BlacklistManager blacklistManager;
    public RemovePlayerCommand(final BlacklistManager blacklistManager) {
        this.blacklistManager = blacklistManager;
    }
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Remove a player from the blacklist";
    }

    @Override
    public String getPermission() {
        return "potion.blacklist";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(args.length != 2){
            sender.sendMessage("Usage: /potion remove <player>");
            return;
        }
        Player target = sender.getServer().getPlayer(args[1]);
        if(target == null){
            sender.sendMessage("Player not found");
            return;
        }
        blacklistManager.removePlayer(target.getUniqueId());
        sender.sendMessage("Player removed from blacklist");

    }
}
