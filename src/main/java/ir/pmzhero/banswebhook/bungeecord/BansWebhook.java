package ir.pmzhero.banswebhook.bungeecord;

import ir.pmzhero.banswebhook.bungeecord.advancedbans.AdvancedBansWebhook;
import ir.pmzhero.banswebhook.bungeecord.commands.MainCommand;
import ir.pmzhero.banswebhook.bungeecord.litebans.LitebansWebhook;
import ir.pmzhero.banswebhook.utils.ConfigManager;
import net.md_5.bungee.api.plugin.Plugin;

public class BansWebhook extends Plugin {

    public static BansWebhook instance;
    public static BansWebhook getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        ConfigManager.saveDefaultConfig();
        if (getProxy().getPluginManager().getPlugin("LiteBans") != null) {
            LitebansWebhook.registerEvent("ban", "Banned By");
            LitebansWebhook.registerEvent("mute", "Muted By");
            LitebansWebhook.registerEvent("kick", "Kicked By");
            LitebansWebhook.registerEvent("warn", "Warned By");
            getLogger().info("LiteBans Detected | Plugin Hooked");
        }
        if (getProxy().getPluginManager().getPlugin("AdvancedBan") != null) {
            getProxy().getPluginManager().registerListener(this, new AdvancedBansWebhook());
            getLogger().info("AdvancedBan Detected | Plugin Hooked");
        }

        getProxy().getPluginManager().registerCommand(this, new MainCommand());
    }

}
