package ir.pmzhero.banswebhook.spigot.commands;

import ir.pmzhero.banswebhook.spigot.BansWebhook;
import ir.pmzhero.banswebhook.universal.Universal;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import ir.pmzhero.banswebhook.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class MainCommand implements CommandExecutor {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = (BansWebhook) VersionMentor.getVersionInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                Universal.getInstance().getConfigurationManager().reloadConfig();

                FileConfiguration fileConfiguration = (FileConfiguration) Universal.getInstance().getConfigurationManager().getConfig();
                sender.sendMessage(Utils.spigotTranslate(fileConfiguration.getString("reload-message")));
            } else {
                return false;
            }
        }

        return true;
    }
}
