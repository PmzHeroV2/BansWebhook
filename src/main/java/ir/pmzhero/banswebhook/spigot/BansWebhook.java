package ir.pmzhero.banswebhook.spigot;


import ir.pmzhero.banswebhook.spigot.advancedbans.AdvancedBansWebhook;
import ir.pmzhero.banswebhook.spigot.commands.MainCommand;
import ir.pmzhero.banswebhook.universal.Universal;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BansWebhook extends JavaPlugin {

    public static Universal getUniversalInstance() {
        return Universal.getInstance();
    }

    @Override
    public void onEnable() {

        // set VersionMentor for instance gathering
        VersionMentor.setVersionInstance(this);

        Universal universal = new Universal(Universal.ServerType.SPIGOT, new ConfigurationImpl());

        universal.getConfigurationManager().saveDefaultConfig();
        universal.getConfigurationManager().loadConfig();

        boolean oneMatch = false;

        if (getServer().getPluginManager().getPlugin("LiteBans") != null) {
            oneMatch = true;
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("ban", "Banned By");
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("mute", "Muted By");
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("kick", "Kicked By");
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("warn", "Warned By");
            getLogger().info("LiteBans Detected | Plugin Hooked");
        }
        if (getServer().getPluginManager().getPlugin("AdvancedBan") != null) {
            oneMatch = true;
            getServer().getPluginManager().registerEvents(new AdvancedBansWebhook(), this);
            getLogger().info("AdvancedBan Detected | Plugin Hooked");
        }

        if (!oneMatch) {
            getLogger().severe("No compatible Punishment Plugin found! Disabling plugin..");
            getPluginLoader().disablePlugin(this);
            return;
        }

        getCommand("bwh").setExecutor(new MainCommand());

    }

    @Override
    public void onDisable() {

    }
}
