package ir.pmzhero.banswebhook.utils.webhook;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class WebhookManager {

    // Punishment-related
    public String punishmentOperator;
    public String punishmentUser;
    public String punishmentReason;
    public String stringPunishmentDuration;

    // Embed-related
    public String embedTitle;
    public String embedThumbnailUrl;

    public static WebhookManager getInstance() {
        return new WebhookManager();
    }

    private WebhookManager() {
    }

    public final WebhookEmbed buildPunishmentEmbed(WebhookPunishment punishment) {


        WebhookEmbedBuilder builder = new WebhookEmbedBuilder()
                .setTitle(new WebhookEmbed.EmbedTitle(embedTitle == null ? punishment.typeDefinition : embedTitle, ""))
                .addField(new WebhookEmbed.EmbedField(false, punishment.defaultDefinition, punishmentOperator == null ? "Unknown" : punishmentOperator))
                .addField(new WebhookEmbed.EmbedField(false, "Username", isNull(punishmentUser) ? "Unknown" : punishmentUser))
                .addField(new WebhookEmbed.EmbedField(false, "Reason", isNull(punishmentReason) ? "None" : punishmentReason))
                .addField(new WebhookEmbed.EmbedField(false, "Duration", isNull(stringPunishmentDuration) ? "Unknown" : stringPunishmentDuration))
                .setColor(punishment.embedColor);

        if (isValidURL(embedThumbnailUrl) && !isNull(embedThumbnailUrl)) {
            builder.setThumbnailUrl(embedThumbnailUrl);
        }

        return builder.build();
    }



    private boolean isNull(Object ob) {
        return ob == null;
    }

    public boolean isValidURL(String url) {

        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }

        return true;
    }
}
