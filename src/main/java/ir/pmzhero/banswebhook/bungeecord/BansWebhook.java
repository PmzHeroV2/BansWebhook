package ir.pmzhero.banswebhook.bungeecord;

import ir.pmzhero.banswebhook.bungeecord.advancedbans.AdvancedBansWebhook;
import ir.pmzhero.banswebhook.bungeecord.commands.MainCommand;
import ir.pmzhero.banswebhook.bungeecord.litebans.LitebansWebhook;
import ir.pmzhero.banswebhook.universal.Universal;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import net.md_5.bungee.api.plugin.Plugin;

public class BansWebhook extends Plugin {


    @Override
    public void onEnable() {


        VersionMentor.setVersionInstance(this);

        Universal universal = new Universal(Universal.ServerType.BUNGEE, new ConfigurationImpl());

        universal.getConfigurationManager().saveDefaultConfig();
        universal.getConfigurationManager().loadConfig();
        boolean oneMatch = false;
        if (getProxy().getPluginManager().getPlugin("LiteBans") != null) {
            oneMatch = true;
            LitebansWebhook.registerEvent("ban", "Banned By");
            LitebansWebhook.registerEvent("mute", "Muted By");
            LitebansWebhook.registerEvent("kick", "Kicked By");
            LitebansWebhook.registerEvent("warn", "Warned By");
            getLogger().info("LiteBans Detected | Plugin Hooked");
        }
        if (getProxy().getPluginManager().getPlugin("AdvancedBan") != null) {
            oneMatch = true;
            getProxy().getPluginManager().registerListener(this, new AdvancedBansWebhook());
            getLogger().info("AdvancedBan Detected | Plugin Hooked");
        }

        if (!oneMatch) {
            getLogger().info("No compatible Punishment Plugin found!");
            return;
        }

        getProxy().getPluginManager().registerCommand(this, new MainCommand());
    }

}
