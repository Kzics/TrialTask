package com.trialtask.commands.blacklist.subcommands;

import com.trialtask.commands.ICommand;
import org.bukkit.command.CommandSender;

public class HelpCommand implements ICommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows help message";
    }

    @Override
    public String getPermission() {
        return "potion.help";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("Usage: /potion <add|remove|help> <player>");
    }
}
