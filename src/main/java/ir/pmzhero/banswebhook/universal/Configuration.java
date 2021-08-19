package ir.pmzhero.banswebhook.universal;

public interface Configuration {

    void saveDefaultConfig();

    void saveConfig(Object configObj);

    void reloadConfig();

    Object getConfig();

    void loadConfig();

    Object getConfigurationFile(String path, String filename);

}
