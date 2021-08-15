package ir.pmzhero.banswebhook.spigot.commands;

import ir.pmzhero.banswebhook.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = ir.pmzhero.banswebhook.spigot.BansWebhook.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                instance.reloadConfig();
                sender.sendMessage(Utils.spigotTranslate(instance.getConfig().getString("reload-message")));
            } else {
                return false;
            }
        }

        return true;
    }
}
