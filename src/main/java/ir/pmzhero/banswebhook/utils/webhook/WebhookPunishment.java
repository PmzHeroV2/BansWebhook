package ir.pmzhero.banswebhook.utils.webhook;

import me.leoko.advancedban.utils.PunishmentType;

public enum WebhookPunishment {
    /*
    Later test use
     */
    BAN("ban", "New Ban!", "Banned By", 0xFF0000), // includes BAN, TEMP_BAN, TEMP_IP_BAN, IP_BAN
    WARN("warn", "New Warn!", "Warned By", 0xFF0000), // includes WARNING, TEMP_WARNING
    KICK("kick", "New Kick!", "Kicked By", 0xFF0000), // includes KICK
    MUTE("mute", "New Mute!", "Muted By", 0xFF0000); // includes MUTE, TEMP_MUTE

    public final String configurationSectionIdentifier;
    public final String typeDefinition;
    public final String defaultDefinition;
    public final int embedColor;

    WebhookPunishment(String configurationSectionIdentifier, String typeDefinition, String defaultDefinition, int embedColor) {
        this.configurationSectionIdentifier = configurationSectionIdentifier;
        this.typeDefinition = typeDefinition;
        this.defaultDefinition = defaultDefinition;
        this.embedColor = embedColor;
    }

    public static WebhookPunishment translatePunishmentType(PunishmentType type) {
        switch (type) {
            case BAN:
            case IP_BAN:
            case TEMP_BAN:
            case TEMP_IP_BAN:
                return BAN;

            case KICK:
                return KICK;

            case MUTE:
            case TEMP_MUTE:
                return MUTE;

            case WARNING:
            case TEMP_WARNING:
                return WARN;

            default:
                return null;
        }
    }


}
