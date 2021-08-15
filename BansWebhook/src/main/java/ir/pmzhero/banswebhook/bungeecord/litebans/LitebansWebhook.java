package ir.pmzhero.banswebhook.bungeecord.litebans;

import ir.pmzhero.banswebhook.bungeecord.BansWebhook;
import ir.pmzhero.banswebhook.utils.ConfigManager;
import ir.pmzhero.banswebhook.utils.DiscordWebhook;
import litebans.api.Database;
import litebans.api.Entry;
import litebans.api.Events;

import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LitebansWebhook {

    private static final BansWebhook instance = BansWebhook.getInstance();

    public static void registerEvent(String entryType, String punishedBy) {
        Events.get().register(new Events.Listener() {
            @Override
            public void entryAdded(Entry entry) {
                if (entry.getType().equals(entryType)) {
                    if (entry.isSilent() && ConfigManager.getConfig().getBoolean("litebans-do-not-send-silent-punishments")) return;
                    if (!ConfigManager.getConfig().getSection(entryType).getBoolean("enabled")) return;

                    try {
                        DiscordWebhook webhook = new DiscordWebhook(ConfigManager.getConfig().getSection(entryType).getString("webhook-url"));

                        String uuid = entry.getUuid();
                        String query = "SELECT name FROM {history} WHERE uuid=? ORDER BY date DESC LIMIT 1";
                        try (PreparedStatement st = Database.get().prepareStatement(query)) {
                            st.setString(1, uuid);
                            try (ResultSet rs = st.executeQuery()) {
                                if (rs.next()) {
                                    String playerName = rs.getString("name");
                                    webhook.addEmbed(new DiscordWebhook.EmbedObject()
                                            .setTitle(ConfigManager.getConfig().getSection(entryType).getString("webhook-title"))
                                            .setThumbnail(ConfigManager.getConfig().getSection(entryType).getString("webhook-thumbnail"))
                                            .setColor(Color.RED)
                                            .addField(punishedBy, entry.getExecutorName(), false)
                                            .addField("Username:", playerName, false)
                                            .addField("Reason:", entry.getReason(), false)
                                            .addField("Duration:", entry.getDurationString(), false));
                                    webhook.execute();
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
    }
}
