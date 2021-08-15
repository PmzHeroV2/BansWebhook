package ir.pmzhero.banswebhook.bungeecord.litebans;

import ir.pmzhero.banswebhook.bungeecord.BansWebhook;
import ir.pmzhero.banswebhook.utils.ConfigManager;
import ir.pmzhero.banswebhook.utils.DiscordWebhook;
import litebans.api.Database;
import litebans.api.Entry;
import litebans.api.Events;
import net.md_5.bungee.config.Configuration;

import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LitebansWebhookTest {
    private static final BansWebhook instance = BansWebhook.getInstance();

    public static void registerEvent(String entryType) {
        Events.get().register(new Events.Listener() {
            @Override
            public void entryAdded(Entry entry) {
                if (entry.getType().equals(entryType)) {
                    if (entry.isSilent() && ConfigManager.getConfig().getBoolean("litebans-do-not-send-silent-punishments")) return;
                    if (!ConfigManager.getConfig().getSection(entryType).getSection("options").getBoolean("send-enabled")) return;

                    try {
                        DiscordWebhook webhook = new DiscordWebhook(ConfigManager.getConfig().getSection(entryType).getString("webhook-url"));
                        DiscordWebhook.EmbedObject embedObject = new DiscordWebhook.EmbedObject();

                        String uuid = entry.getUuid();
                        String query = "SELECT name FROM {history} WHERE uuid=? ORDER BY date DESC LIMIT 1";
                        try (PreparedStatement st = Database.get().prepareStatement(query)) {
                            st.setString(1, uuid);
                            try (ResultSet rs = st.executeQuery()) {
                                if (rs.next()) {
                                    String playerName = rs.getString("name");

                                    Configuration options = ConfigManager.getConfig().getSection(entryType).getSection("options");
                                    Configuration thumbnail = options.getSection("thumbnail");
                                    Configuration webhookSettings = options.getSection("webhook-settings");
                                    if (thumbnail.getBoolean("enabled")) {
                                        embedObject.setThumbnail(thumbnail.getString("url"));
                                    }
                                    

                                    embedObject.setColor(Color.decode("#FF0000"));


                                    webhook.addEmbed(embedObject);
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
