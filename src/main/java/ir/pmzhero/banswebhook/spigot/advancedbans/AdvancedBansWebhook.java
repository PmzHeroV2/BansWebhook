package ir.pmzhero.banswebhook.spigot.advancedbans;

import ir.pmzhero.banswebhook.utils.DiscordWebhook;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.io.IOException;

public class AdvancedBansWebhook implements Listener {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = ir.pmzhero.banswebhook.spigot.BansWebhook.getInstance();

    @EventHandler
    public void onPunish(PunishmentEvent event) {

        PunishmentType type = event.getPunishment().getType();

        if (type.equals(PunishmentType.BAN) || type.equals(PunishmentType.TEMP_BAN) || (type.equals(PunishmentType.IP_BAN)) || type.equals(PunishmentType.TEMP_IP_BAN)) {
            try {
                if (!instance.getConfig().getConfigurationSection("ban").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(instance.getConfig().getConfigurationSection("ban").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(instance.getConfig().getConfigurationSection("ban").getString("webhook-title"))
                        .setThumbnail(instance.getConfig().getConfigurationSection("ban").getString("webhook-thumbnail"))
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
                if (!instance.getConfig().getConfigurationSection("warn").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(instance.getConfig().getConfigurationSection("warn").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(instance.getConfig().getConfigurationSection("warn").getString("webhook-title"))
                        .setThumbnail(instance.getConfig().getConfigurationSection("warn").getString("webhook-thumbnail"))
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
                if (!instance.getConfig().getConfigurationSection("kick").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(instance.getConfig().getConfigurationSection("kick").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(instance.getConfig().getConfigurationSection("kick").getString("webhook-title"))
                        .setThumbnail(instance.getConfig().getConfigurationSection("kick").getString("webhook-thumbnail"))
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
                if (!instance.getConfig().getConfigurationSection("mute").getBoolean("enabled")) return;
                DiscordWebhook webhook = new DiscordWebhook(instance.getConfig().getConfigurationSection("mute").getString("webhook-url"));
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(instance.getConfig().getConfigurationSection("mute").getString("webhook-title"))
                        .setThumbnail(instance.getConfig().getConfigurationSection("mute").getString("webhook-thumbnail"))
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
