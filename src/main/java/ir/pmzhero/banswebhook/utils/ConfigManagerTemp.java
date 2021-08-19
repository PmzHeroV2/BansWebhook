package ir.pmzhero.banswebhook.utils;

public class ConfigManagerTemp {
/*

    private static final BansWebhook instance = BansWebhook.getInstance();
    private static Configuration configuration = null;

    static {
        try {
            saveDefaultConfig();
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(instance.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getConfig() {
        return configuration;
    }

    public static void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(instance.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reloadConfig() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(instance.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveDefaultConfig() {
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


 */
}
