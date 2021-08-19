package ir.pmzhero.banswebhook.bungeecord;

import ir.pmzhero.banswebhook.universal.Configuration;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigurationImpl implements Configuration {

    private static net.md_5.bungee.config.Configuration configurationObject;

    @Override
    public void saveDefaultConfig() {

        ir.pmzhero.banswebhook.bungeecord.BansWebhook instance = (ir.pmzhero.banswebhook.bungeecord.BansWebhook) VersionMentor.getVersionInstance();

        if (!instance.getDataFolder().exists())
            instance.getDataFolder().mkdir();

        File file = new File(instance.getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = instance.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void saveConfig(Object configObj) {
        ir.pmzhero.banswebhook.bungeecord.BansWebhook instance = (ir.pmzhero.banswebhook.bungeecord.BansWebhook) VersionMentor.getVersionInstance();

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configurationObject, (File) this.getConfigurationFile("plugins/Banswebhook/", "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reloadConfig() {
        loadConfig();
    }

    @Override
    public Object getConfig() {

        return configurationObject;

    }

    @Override
    public void loadConfig() {
        try {
            configurationObject = YamlConfiguration.getProvider(YamlConfiguration.class).load((File) this.getConfigurationFile("plugins/BansWebhook/", "config.yml"));
        } catch (IOException e) {
            System.out.println("Failed to load config.yml");
            e.printStackTrace();
        }


    }


    @Override
    public Object getConfigurationFile(String path, String filename) {
        File configFile = new File(path, filename);

        if (!configFile.exists()) {
            this.saveDefaultConfig();
            configFile = new File(path, filename);
        }

        return configFile;

    }
}
