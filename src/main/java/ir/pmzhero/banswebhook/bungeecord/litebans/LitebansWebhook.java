package ir.pmzhero.banswebhook.bungeecord.litebans;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import ir.pmzhero.banswebhook.bungeecord.BansWebhook;
import ir.pmzhero.banswebhook.utils.ConfigManager;
import litebans.api.Database;
import litebans.api.Entry;
import litebans.api.Events;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LitebansWebhook {

    private static final BansWebhook instance = BansWebhook.getInstance();

    public static void registerEvent(String entryType, String punishedBy) {
        Events.get().register(new Events.Listener() {
            @Override
            public void entryAdded(Entry entry) {
                if (entry.getType().equals(entryType)) {
                    if (entry.isSilent() && ConfigManager.getConfig().getBoolean("litebans-do-not-send-silent-punishments")) return;
                    if (!ConfigManager.getConfig().getSection(entryType).getBoolean("enabled")) return;
                        String uuid = entry.getUuid();
                        String query = "SELECT name FROM {history} WHERE uuid=? ORDER BY date DESC LIMIT 1";
                        try (PreparedStatement st = Database.get().prepareStatement(query)) {
                            st.setString(1, uuid);
                            try (ResultSet rs = st.executeQuery()) {
                                if (rs.next()) {
                                    String playerName = rs.getString("name");
                                    WebhookClient client = WebhookClient.withUrl(ConfigManager.getConfig().getSection(entryType).getString("webhook-url"));
                                    WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(ConfigManager.getConfig().getSection(entryType).getString("webhook-title"), "");
                                    WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, punishedBy, Objects.requireNonNull(entry.getExecutorName()));
                                    WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", playerName);
                                    WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", Objects.requireNonNull(entry.getReason()));
                                    WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", Objects.requireNonNull(entry.getDurationString()));
                                    WebhookEmbed embed = new WebhookEmbedBuilder()
                                            .setTitle(title)
                                            .setThumbnailUrl(ConfigManager.getConfig().getSection(entryType).getString("webhook-thumbnail"))
                                            .setColor(0xFF0000)
                                            .addField(field1)
                                            .addField(field2)
                                            .addField(field3)
                                            .addField(field4)
                                            .build();
                                    client.send(embed);


                                    /*

                                    webhook.addEmbed(new DiscordWebhook.EmbedObject()
                                            .setTitle(ConfigManager.getConfig().getSection(entryType).getString("webhook-title"))
                                            .setThumbnail(ConfigManager.getConfig().getSection(entryType).getString("webhook-thumbnail"))
                                            .setColor(Color.RED)
                                            .addField(punishedBy, entry.getExecutorName(), false)
                                            .addField("Username:", playerName, false)
                                            .addField("Reason:", entry.getReason(), false)
                                            .addField("Duration:", entry.getDurationString(), false));
                                    webhook.execute();

                                     */
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
