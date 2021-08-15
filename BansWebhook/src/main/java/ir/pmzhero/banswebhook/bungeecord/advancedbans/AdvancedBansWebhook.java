package ir.pmzhero.banswebhook.bungeecord.advancedbans;

import ir.pmzhero.banswebhook.utils.ConfigManager;
import ir.pmzhero.banswebhook.utils.DiscordWebhook;
import me.leoko.advancedban.bungee.event.PunishmentEvent;
import me.leoko.advancedban.utils.PunishmentType;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.awt.*;
import java.io.IOException;

public class AdvancedBansWebhook implements Listener {

    @EventHandler
    public void onPunish(PunishmentEvent event) {

        PunishmentType type = event.getPunishment().getType();

        if (type.equals(PunishmentType.BAN) || type.equals(PunishmentType.TEMP_BAN) || (type.equals(PunishmentType.IP_BAN)) || type.equals(PunishmentType.TEMP_IP_BAN)) {
            try {
                if (!ConfigManager.getConfig().getSection("ban").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(ConfigManager.getConfig().getSection("ban").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(ConfigManager.getConfig().getSection("ban").getString("webhook-title"))
                        .setThumbnail(ConfigManager.getConfig().getSection("ban").getString("webhook-thumbnail"))
                        .setColor(Color.RED)
                        .addField("Banned By", event.getPunishment().getOperator(), false)
                        .addField("Username:", event.getPunishment().getName(), false)
                        .addField("Reason:", event.getPunishment().getReason(), false)
                        .addField("Duration:", event.getPunishment().getDuration(true), false));
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals(PunishmentType.WARNING) || (type.equals(PunishmentType.TEMP_WARNING))) {
            try {
                if (!ConfigManager.getConfig().getSection("warn").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(ConfigManager.getConfig().getSection("warn").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(ConfigManager.getConfig().getSection("warn").getString("webhook-title"))
                        .setThumbnail(ConfigManager.getConfig().getSection("warn").getString("webhook-thumbnail"))
                        .setColor(Color.RED)
                        .addField("Warned By", event.getPunishment().getOperator(), false)
                        .addField("Username:", event.getPunishment().getName(), false)
                        .addField("Reason:", event.getPunishment().getReason(), false)
                        .addField("Duration:", event.getPunishment().getDuration(true), false));
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals(PunishmentType.KICK)) {
            try {
                if (!ConfigManager.getConfig().getSection("kick").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(ConfigManager.getConfig().getSection("kick").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(ConfigManager.getConfig().getSection("kick").getString("webhook-title"))
                        .setThumbnail(ConfigManager.getConfig().getSection("kick").getString("webhook-thumbnail"))
                        .setColor(Color.RED)
                        .addField("Kicked By", event.getPunishment().getOperator(), false)
                        .addField("Username:", event.getPunishment().getName(), false)
                        .addField("Reason:", event.getPunishment().getReason(), false)
                        .addField("Duration:", event.getPunishment().getDuration(true), false));
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals(PunishmentType.MUTE) || (type.equals(PunishmentType.TEMP_MUTE))) {
            try {
                if (!ConfigManager.getConfig().getSection("mute").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(ConfigManager.getConfig().getSection("mute").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(ConfigManager.getConfig().getSection("mute").getString("webhook-title"))
                        .setThumbnail(ConfigManager.getConfig().getSection("mute").getString("webhook-thumbnail"))
                        .setColor(Color.RED)
                        .addField("Muted By", event.getPunishment().getOperator(), false)
                        .addField("Username:", event.getPunishment().getName(), false)
                        .addField("Reason:", event.getPunishment().getReason(), false)
                        .addField("Duration:", event.getPunishment().getDuration(true), false));
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}