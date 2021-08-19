package ir.pmzhero.banswebhook.universal;

import ir.pmzhero.banswebhook.utils.webhook.WebhookPunishment;
import me.leoko.advancedban.utils.Punishment;

public class PunishmentHandler {

    /*
    later use
     */
    private final Punishment punishment;
    private final WebhookPunishment webhookPunishment;

    public PunishmentHandler(Punishment punishment, WebhookPunishment webhookPunishment) {
        this.punishment = punishment;
        this.webhookPunishment = webhookPunishment;
    }

    public void handlePunishment() {

    }
}
