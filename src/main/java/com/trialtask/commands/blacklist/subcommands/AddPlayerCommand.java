package com.trialtask.commands.blacklist.subcommands;

import com.trialtask.commands.ICommand;
import com.trialtask.manager.BlacklistManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddPlayerCommand implements ICommand {


    private final BlacklistManager blacklistManager;
    public AddPlayerCommand(final BlacklistManager blacklistManager) {
        this.blacklistManager = blacklistManager;
    }

    @Override
    public String getName() {
        return "blacklist";
    }

    @Override
    public String getDescription() {
        return "Blacklist a player to use potions";
    }

    @Override
    public String getPermission() {
        return "potion.blacklist";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 2){
            sender.sendMessage("Usage: /potion add <player>");
            return;
        }

        Player target = sender.getServer().getPlayer(args[1]);
        if(target == null){
            sender.sendMessage("Player not found");
            return;
        }

        if(blacklistManager.isBlacklisted(target.getUniqueId())){
            sender.sendMessage("Player is already blacklisted");
            return;
        }
        blacklistManager.addPlayer(target.getUniqueId());
        sender.sendMessage("Player blacklisted");
    }
}
