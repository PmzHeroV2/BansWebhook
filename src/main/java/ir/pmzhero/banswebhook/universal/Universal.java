package ir.pmzhero.banswebhook.universal;

public class Universal {

    private static Universal universal;
    private final ServerType serverType;
    private final Configuration configuration;


    public Universal(ServerType type, Configuration configuration) {

        if (universal != null) {
            throw new IllegalArgumentException("Universal already initialized! Using " + this.getServerType());
        }

        this.serverType = type;
        this.configuration = configuration;

        universal = this;
    }

    public static Universal getInstance() {
        return universal;
    }

    public final Configuration getConfigurationManager() {
        return configuration;
    }

    public final ServerType getServerType() {
        return serverType;
    }

    public enum ServerType {
        BUNGEE,
        SPIGOT
    }
}


