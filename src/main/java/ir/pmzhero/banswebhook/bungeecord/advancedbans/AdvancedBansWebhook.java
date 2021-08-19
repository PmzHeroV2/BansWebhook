package ir.pmzhero.banswebhook.bungeecord.advancedbans;

import club.minnced.discord.webhook.WebhookClient;
import ir.pmzhero.banswebhook.bungeecord.BansWebhook;
import ir.pmzhero.banswebhook.universal.Universal;
import ir.pmzhero.banswebhook.universal.VersionMentor;
import ir.pmzhero.banswebhook.utils.webhook.WebhookManager;
import ir.pmzhero.banswebhook.utils.webhook.WebhookPunishment;
import me.leoko.advancedban.bungee.event.PunishmentEvent;
import me.leoko.advancedban.utils.Punishment;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class AdvancedBansWebhook implements Listener {

    private static final ir.pmzhero.banswebhook.bungeecord.BansWebhook instance = (BansWebhook) VersionMentor.getVersionInstance();


    @EventHandler
    public void onPunish(PunishmentEvent event) {


        Configuration config = (Configuration) Universal.getInstance().getConfigurationManager().getConfig();
        final boolean debug = config.getBoolean("debug-message");


        instance.getLogger().info("onPunish received! " + event.getPunishment().toString());

        Punishment punishment = event.getPunishment();
        WebhookPunishment webhookPunishment = WebhookPunishment.translatePunishmentType(punishment.getType());


        if (!config.getSection(webhookPunishment.configurationSectionIdentifier).getBoolean("enabled", true)) {
            if (debug)
                instance.getLogger().info("Punishment for " + punishment.toString() + " not sent: Configuration for " + webhookPunishment.configurationSectionIdentifier + ": enabled is set to false");

            return;
        }


        WebhookClient client = WebhookClient.withUrl(config.getSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-url"));

        WebhookManager webhookManager = WebhookManager.getInstance();

        webhookManager.punishmentOperator = punishment.getOperator();
        webhookManager.punishmentUser = punishment.getName();
        webhookManager.punishmentReason = punishment.getReason();
        webhookManager.stringPunishmentDuration = punishment.getDuration(true);

        webhookManager.embedThumbnailUrl = config.getSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-thumbnail");
        webhookManager.embedTitle = config.getSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-title");

        client.send(webhookManager.buildPunishmentEmbed(webhookPunishment));

        if (debug)
            instance.getLogger().info("Successfully sent Punishment to Webhook: " + punishment.toString());

    }


}