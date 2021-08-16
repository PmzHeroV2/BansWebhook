package ir.pmzhero.banswebhook.spigot.advancedbans;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class AdvancedBansWebhook implements Listener {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = ir.pmzhero.banswebhook.spigot.BansWebhook.getInstance();

    @EventHandler
    public void onPunish(PunishmentEvent event) {

        PunishmentType type = event.getPunishment().getType();

        if (type.equals(PunishmentType.BAN) || type.equals(PunishmentType.TEMP_BAN) || (type.equals(PunishmentType.IP_BAN)) || type.equals(PunishmentType.TEMP_IP_BAN)) {
            if (!instance.getConfig().getConfigurationSection("ban").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(instance.getConfig().getConfigurationSection("ban").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(instance.getConfig().getConfigurationSection("ban").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Banned By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(instance.getConfig().getConfigurationSection("ban").getString("webhook-thumbnail"))
                    .setColor(0xFF0000)
                    .addField(field1)
                    .addField(field2)
                    .addField(field3)
                    .addField(field4)
                    .build();
            client.send(embed);
        } else if (type.equals(PunishmentType.WARNING) || (type.equals(PunishmentType.TEMP_WARNING))) {
            if (!instance.getConfig().getConfigurationSection("warn").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(instance.getConfig().getConfigurationSection("warn").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(instance.getConfig().getConfigurationSection("warn").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Warned By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(instance.getConfig().getConfigurationSection("warn").getString("webhook-thumbnail"))
                    .setColor(0xFF0000)
                    .addField(field1)
                    .addField(field2)
                    .addField(field3)
                    .addField(field4)
                    .build();
            client.send(embed);
        } else if (type.equals(PunishmentType.KICK)) {
            if (!instance.getConfig().getConfigurationSection("kick").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(instance.getConfig().getConfigurationSection("kick").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(instance.getConfig().getConfigurationSection("kick").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Kicked By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(instance.getConfig().getConfigurationSection("kick").getString("webhook-thumbnail"))
                    .setColor(0xFF0000)
                    .addField(field1)
                    .addField(field2)
                    .addField(field3)
                    .addField(field4)
                    .build();
            client.send(embed);
        } else if (type.equals(PunishmentType.MUTE) || (type.equals(PunishmentType.TEMP_MUTE))) {
            if (!instance.getConfig().getConfigurationSection("mute").getBoolean("enabled")) return;


            WebhookClient client = WebhookClient.withUrl(instance.getConfig().getConfigurationSection("mute").getString("webhook-url"));
            WebhookEmbed.EmbedTitle title = new WebhookEmbed.EmbedTitle(instance.getConfig().getConfigurationSection("mute").getString("webhook-title"), "");
            WebhookEmbed.EmbedField field1 = new WebhookEmbed.EmbedField(false, "Muted By", event.getPunishment().getOperator());
            WebhookEmbed.EmbedField field2 = new WebhookEmbed.EmbedField(false, "Username", event.getPunishment().getName());
            WebhookEmbed.EmbedField field3 = new WebhookEmbed.EmbedField(false, "Reason", event.getPunishment().getReason());
            WebhookEmbed.EmbedField field4 = new WebhookEmbed.EmbedField(false, "Duration", event.getPunishment().getDuration(true));
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setTitle(title)
                    .setThumbnailUrl(instance.getConfig().getConfigurationSection("mute").getString("webhook-thumbnail"))
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
