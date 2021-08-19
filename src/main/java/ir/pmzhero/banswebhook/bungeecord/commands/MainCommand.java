package ir.pmzhero.banswebhook.bungeecord.commands;

import ir.pmzhero.banswebhook.universal.Universal;
import ir.pmzhero.banswebhook.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class MainCommand extends Command {

    public MainCommand() {
        super("bwh");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("bwh.reload")) {
            sender.sendMessage(TextComponent.fromLegacyText(Utils.bungeeTranslate("&cNo Permission!")));
            return;
        }
        if (args.length == 0) {
            sender.sendMessage(TextComponent.fromLegacyText(Utils.bungeeTranslate("/bwh <reload>")));
        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                Universal.getInstance().getConfigurationManager().reloadConfig();
                Configuration configuration = (Configuration) Universal.getInstance().getConfigurationManager().getConfig();
                sender.sendMessage(TextComponent.fromLegacyText(Utils.bungeeTranslate(configuration.getString("reload-message"))));
            } else {
                sender.sendMessage(TextComponent.fromLegacyText(Utils.bungeeTranslate("/bwh <reload>")));
            }
        }

    }
}
