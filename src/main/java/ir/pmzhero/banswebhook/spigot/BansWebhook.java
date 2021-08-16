package ir.pmzhero.banswebhook.spigot;


import ir.pmzhero.banswebhook.spigot.advancedbans.AdvancedBansWebhook;
import ir.pmzhero.banswebhook.spigot.commands.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BansWebhook extends JavaPlugin {

    public static ir.pmzhero.banswebhook.spigot.BansWebhook instance;

    public static ir.pmzhero.banswebhook.spigot.BansWebhook getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        if (getServer().getPluginManager().getPlugin("LiteBans") != null) {
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("ban", "Banned By");
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("mute", "Muted By");
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("kick", "Kicked By");
            ir.pmzhero.banswebhook.spigot.litebans.LitebansWebhook.registerEvent("warn", "Warned By");
            getLogger().info("LiteBans Detected | Plugin Hooked");
        }
        if (getServer().getPluginManager().getPlugin("AdvancedBan") != null) {
            getServer().getPluginManager().registerEvents(new AdvancedBansWebhook(), this);
            getLogger().info("AdvancedBan Detected | Plugin Hooked");
        }

        getCommand("bwh").setExecutor(new MainCommand());

    }

    @Override
    public void onDisable() {

    }
}
