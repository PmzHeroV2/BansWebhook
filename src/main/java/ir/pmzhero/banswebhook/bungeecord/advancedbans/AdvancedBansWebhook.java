package ir.pmzhero.banswebhook.bungeecord.advancedbans;

import club.minnced.discord.webhook.WebhookClient;
import ir.pmzhero.banswebhook.utils.ConfigManager;
import ir.pmzhero.banswebhook.utils.webhook.WebhookManager;
import ir.pmzhero.banswebhook.utils.webhook.WebhookPunishment;
import me.leoko.advancedban.bungee.event.PunishmentEvent;
import me.leoko.advancedban.utils.Punishment;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class AdvancedBansWebhook implements Listener {

    @EventHandler
    public void onPunish(PunishmentEvent event) {

        Punishment punishment = event.getPunishment();
        WebhookPunishment webhookPunishment = WebhookPunishment.translatePunishmentType(punishment.getType());

        if (ConfigManager.getConfig().getSection(webhookPunishment.configurationSectionIdentifier).getBoolean("enabled", false))
            return;

        WebhookClient client = WebhookClient.withUrl(ConfigManager.getConfig().getSection(webhookPunishment.configurationSectionIdentifier).getString("webhook-url"));

        WebhookManager webhookManager = WebhookManager.getInstance();

        webhookManager.punishmentOperator = punishment.getOperator();
        webhookManager.punishmentUser = punishment.getName();
        webhookManager.punishmentReason = punishment.getReason();
        webhookManager.stringPunishmentDuration = punishment.getDuration(true);

        client.send(webhookManager.buildPunishmentEmbed(webhookPunishment));

        final boolean debug = ConfigManager.getConfig().getBoolean("debug", true);

    }


}