package ir.pmzhero.banswebhook.spigot.advancedbans;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import ir.pmzhero.banswebhook.utils.ConfigManager;
import me.leoko.advancedban.bungee.event.PunishmentEvent;
import me.leoko.advancedban.utils.PunishmentType;
import net.md_5.bungee.event.EventHandler;
import org.bukkit.event.Listener;


public class AdvancedBansWebhook implements Listener {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = ir.pmzhero.banswebhook.spigot.BansWebhook.getInstance();

    @EventHandler
    public void onPunish(PunishmentEvent event) {

        PunishmentType type = event.getPunishment().getType();

        if (type.equals(PunishmentType.BAN) || type.equals(PunishmentType.TEMP_BAN) || (type.equals(PunishmentType.IP_BAN)) || type.equals(PunishmentType.TEMP_IP_BAN)) {
            if (!ConfigManager.getConfig().getSection("ban").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(ConfigManager.getConfig().getSection("ban").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(ConfigManager.getConfig().getSection("ban").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Banned By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(ConfigManager.getConfig().getSection("ban").getString("webhook-thumbnail"))
                    .setColor(0xFF0000)
                    .addField(field1)
                    .addField(field2)
                    .addField(field3)
                    .addField(field4)
                    .build();
            client.send(embed);
        } else if (type.equals(PunishmentType.WARNING) || (type.equals(PunishmentType.TEMP_WARNING))) {
            if (!ConfigManager.getConfig().getSection("warn").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(ConfigManager.getConfig().getSection("warn").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(ConfigManager.getConfig().getSection("warn").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Warned By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(ConfigManager.getConfig().getSection("warn").getString("webhook-thumbnail"))
                    .setColor(0xFF0000)
                    .addField(field1)
                    .addField(field2)
                    .addField(field3)
                    .addField(field4)
                    .build();
            client.send(embed);
        } else if (type.equals(PunishmentType.KICK)) {
            if (!ConfigManager.getConfig().getSection("kick").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(ConfigManager.getConfig().getSection("kick").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(ConfigManager.getConfig().getSection("kick").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Kicked By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(ConfigManager.getConfig().getSection("kick").getString("webhook-thumbnail"))
                    .setColor(0xFF0000)
                    .addField(field1)
                    .addField(field2)
                    .addField(field3)
                    .addField(field4)
                    .build();
            client.send(embed);
        } else if (type.equals(PunishmentType.MUTE) || (type.equals(PunishmentType.TEMP_MUTE))) {
            if (!ConfigManager.getConfig().getSection("mute").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(ConfigManager.getConfig().getSection("mute").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(ConfigManager.getConfig().getSection("mute").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Muted By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(ConfigManager.getConfig().getSection("mute").getString("webhook-thumbnail"))
                    .setColor(0xFF0000)
                    .addField(field1)
                    .addField(field2)
                    .addField(field3)
                    .addField(field4)
                    .build();
            client.send(embed);
        }

    }


}
