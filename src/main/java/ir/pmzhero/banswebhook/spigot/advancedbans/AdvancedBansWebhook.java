package ir.pmzhero.banswebhook.spigot.advancedbans;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import ir.pmzhero.banswebhook.utils.ConfigManager;
import ir.pmzhero.banswebhook.utils.Utils;
import ir.pmzhero.banswebhook.utils.webhook.WebhookManager;
import ir.pmzhero.banswebhook.utils.webhook.WebhookPunishment;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AdvancedBansWebhook implements Listener {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = ir.pmzhero.banswebhook.spigot.BansWebhook.getInstance();


    @EventHandler
    public void onPunish(PunishmentEvent event) {
        final boolean debug = instance.getConfig().getBoolean("debug");

        instance.getLogger().info("onPunish received! "+event.getPunishment().toString());

        Punishment punishment = event.getPunishment();
        WebhookPunishment webhookPunishment = WebhookPunishment.translatePunishmentType(punishment.getType());


        if (!instance.getConfig().getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getBoolean("enabled", true)) {
            if (debug)
                instance.getLogger().info("Punishment for "+punishment.toString()+" not sent: Configuration for "+webhookPunishment.configurationSectionIdentifier +": enabled is set to false");

            return;
        }


        WebhookClient client = WebhookClient.withUrl(instance.getConfig().getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-url"));

        WebhookManager webhookManager = WebhookManager.getInstance();

        webhookManager.punishmentOperator = punishment.getOperator();
        webhookManager.punishmentUser = punishment.getName();
        webhookManager.punishmentReason = punishment.getReason();
        webhookManager.stringPunishmentDuration = punishment.getDuration(true);

        webhookManager.embedThumbnailUrl = instance.getConfig().getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-thumbnail");
        webhookManager.embedTitle = instance.getConfig().getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-title");

        client.send(webhookManager.buildPunishmentEmbed(webhookPunishment));

        if (debug)
            instance.getLogger().info("Successfully sent Punishment to Webhook: "+punishment.toString());

    }


}