package ir.pmzhero.banswebhook.universal;

public class VersionMentor {

    private static Object versionInstance;

    public static Object getVersionInstance() {
        return versionInstance;
    }

    public static void setVersionInstance(Object versionObject) {
        versionInstance = versionObject;
    }
}
