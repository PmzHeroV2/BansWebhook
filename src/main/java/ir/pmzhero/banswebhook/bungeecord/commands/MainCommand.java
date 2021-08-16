package ir.pmzhero.banswebhook.bungeecord.commands;

import ir.pmzhero.banswebhook.utils.ConfigManager;
import ir.pmzhero.banswebhook.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

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
                ConfigManager.reloadConfig();
                sender.sendMessage(TextComponent.fromLegacyText(Utils.bungeeTranslate(ConfigManager.getConfig().getString("reload-message"))));
            } else {
                sender.sendMessage(TextComponent.fromLegacyText(Utils.bungeeTranslate("/bwh <reload>")));
            }
        }

    }
}
