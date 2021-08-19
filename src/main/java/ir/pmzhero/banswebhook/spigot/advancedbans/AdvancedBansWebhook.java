package ir.pmzhero.banswebhook.spigot.advancedbans;

import club.minnced.discord.webhook.WebhookClient;
import ir.pmzhero.banswebhook.spigot.BansWebhook;
import ir.pmzhero.banswebhook.universal.Universal;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import ir.pmzhero.banswebhook.utils.webhook.WebhookManager;
import ir.pmzhero.banswebhook.utils.webhook.WebhookPunishment;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import me.leoko.advancedban.utils.Punishment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AdvancedBansWebhook implements Listener {

    private static final ir.pmzhero.banswebhook.spigot.BansWebhook instance = (BansWebhook) VersionMentor.getVersionInstance();


    @EventHandler
    public void onPunish(PunishmentEvent event) {

        FileConfiguration config = (FileConfiguration) Universal.getInstance().getConfigurationManager().getConfig();
        final boolean debug = config.getBoolean("debug-message");


        instance.getLogger().info("onPunish received! " + event.getPunishment().toString());

        Punishment punishment = event.getPunishment();
        WebhookPunishment webhookPunishment = WebhookPunishment.translatePunishmentType(punishment.getType());


        if (!config.getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getBoolean("enabled", true)) {
            if (debug)
                instance.getLogger().info("Punishment for " + punishment.toString() + " not sent: Configuration for " + webhookPunishment.configurationSectionIdentifier + ": enabled is set to false");

            return;
        }


        WebhookClient client = WebhookClient.withUrl(config.getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-url"));

        WebhookManager webhookManager = WebhookManager.getInstance();

        webhookManager.punishmentOperator = punishment.getOperator();
        webhookManager.punishmentUser = punishment.getName();
        webhookManager.punishmentReason = punishment.getReason();
        webhookManager.stringPunishmentDuration = punishment.getDuration(true);

        webhookManager.embedThumbnailUrl = config.getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-thumbnail");
        webhookManager.embedTitle = config.getConfigurationSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-title");

        client.send(webhookManager.buildPunishmentEmbed(webhookPunishment));

        if (debug)
            instance.getLogger().info("Successfully sent Punishment to Webhook: " + punishment.toString());

    }


}