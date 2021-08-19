package ir.pmzhero.banswebhook.spigot;

import ir.pmzhero.banswebhook.universal.Configuration;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigurationImpl implements Configuration {

    private static FileConfiguration configurationObject;

    @Override
    public void saveDefaultConfig() {

        BansWebhook instance = (BansWebhook) VersionMentor.getVersionInstance();

        if (!instance.getDataFolder().exists())
            instance.getDataFolder().mkdir();

        File file = new File(instance.getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = instance.getResource("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void saveConfig(Object configObj) {
        BansWebhook instance = (BansWebhook) VersionMentor.getVersionInstance();

        FileConfiguration configuration = (FileConfiguration) configObj;

        try {
            configuration.save((File) this.getConfigurationFile("plugins/BansWebhook/", "config.yml"));
        } catch (IOException e) {
            System.out.println("Failed saving config.yml");
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
        File configFile = new File("plugins/BansWebhook/", "config.yml");

        if (!configFile.exists()) {
            this.saveDefaultConfig();
        }

        FileConfiguration configuration = new YamlConfiguration();

        try {
            configuration.load(configFile);
            configurationObject = configuration;
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println("Failed loading config.yml");
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
