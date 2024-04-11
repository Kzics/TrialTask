package com.trialtask.commands.blacklist;

import com.trialtask.Main;
import com.trialtask.commands.CommandBase;
import com.trialtask.commands.ICommand;
import com.trialtask.commands.blacklist.subcommands.AddPlayerCommand;
import com.trialtask.commands.blacklist.subcommands.HelpCommand;
import com.trialtask.commands.blacklist.subcommands.RemovePlayerCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class PotionBlacklistCommand extends CommandBase {

    private final Main main;
    public PotionBlacklistCommand(final Main main) {
        this.main = main;

        registerSubCommand("add", new AddPlayerCommand(main.getBlacklistManager()));
        registerSubCommand("remove", new RemovePlayerCommand(main.getBlacklistManager()));
        registerSubCommand("help", new HelpCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            ICommand subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                if (!sender.hasPermission(subCommand.getPermission())) {
                    sender.sendMessage("You don't have permission to execute this command.");
                    return true;
                }
                subCommand.execute(sender, args);
                return true;
            }
            subCommands.get("help").execute(sender, args);
        }
        return false;
    }
}
