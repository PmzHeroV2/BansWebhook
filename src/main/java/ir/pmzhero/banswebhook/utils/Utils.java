package ir.pmzhero.banswebhook.utils;

import ir.pmzhero.banswebhook.utils.webhook.WebhookPunishment;
import me.leoko.advancedban.utils.PunishmentType;
import net.md_5.bungee.api.ChatColor;

import java.util.Objects;

public class Utils {

    public static String bungeeTranslate(String text) {
        return ChatColor.translateAlternateColorCodes('&',text);
    }
    public static String spigotTranslate(String text) { return org.bukkit.ChatColor.translateAlternateColorCodes('&',text); }


}
