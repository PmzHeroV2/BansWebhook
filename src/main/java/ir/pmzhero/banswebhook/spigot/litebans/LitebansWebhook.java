package ir.pmzhero.banswebhook.spigot.litebans;


import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import ir.pmzhero.banswebhook.spigot.BansWebhook;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import litebans.api.Database;
import litebans.api.Entry;
import litebans.api.Events;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LitebansWebhook {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = (BansWebhook) VersionMentor.getVersionInstance();


    public static void registerEvent(String entryType, String punishedBy) {
        Events.get().register(new Events.Listener() {
            @Override
            public void entryAdded(Entry entry) {
                if (entry.getType().equals(entryType)) {
                    if (entry.isSilent() && instance.getConfig().getBoolean("litebans-do-not-send-silent-punishments"))
                        return;
                    if (!instance.getConfig().getConfigurationSection(entryType).getBoolean("enabled")) return;
                    String uuid = entry.getUuid();
                    String query = "SELECT name FROM {history} WHERE uuid=? ORDER BY date DESC LIMIT 1";
                    try (PreparedStatement st = Database.get().prepareStatement(query)) {
                        st.setString(1, uuid);
                        try (ResultSet rs = st.executeQuery()) {
                            if (rs.next()) {
                                String playerName = rs.getString("name");
                                WebhookClient client = WebhookClient.withUrl(instance.getConfig().getConfigurationSection(entryType).getString("webhook-url"));
                                WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(instance.getConfig().getConfigurationSection(entryType).getString("webhook-title"), "");
                                WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, punishedBy, Objects.requireNonNull(entry.getExecutorName()));
                                WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", playerName);
                                WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", Objects.requireNonNull(entry.getReason()));
                                WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", Objects.requireNonNull(entry.getDurationString()));
                                WebhookEmbed embed = new WebhookEmbedBuilder()
                                        .setTitle(title)
                                        .setThumbnailUrl(instance.getConfig().getConfigurationSection(entryType).getString("webhook-thumbnail"))
                                        .setColor(0xFF0000)
                                        .addField(field1)
                                        .addField(field2)
                                        .addField(field3)
                                        .addField(field4)
                                        .build();
                                client.send(embed);


                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
    }


}
