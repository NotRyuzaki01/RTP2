package me.not_ryuzaki.rTP2;

import me.not_ryuzaki.mainScorePlugin.Combat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RTPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            // 🚫 Prevent if in combat
            if (Combat.isInCombat(player)) {
                player.sendMessage("§cYou can't use /rtp while in combat!");
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                        new TextComponent("§cYou're in combat!"));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                return true;
            }

            UUID playerId = player.getUniqueId();
            long currentTime = System.currentTimeMillis();
            long cooldownTime = 10 * 1000; // 10 seconds in milliseconds

            if (RTP2.getInstance().getCooldowns().containsKey(playerId)) {
                long timeLeft = (RTP2.getInstance().getCooldowns().get(playerId) + cooldownTime) - currentTime;
                if (timeLeft > 0) {
                    String message = "§cYou can't rtp for another " + (timeLeft / 1000) + "s";
                    player.sendMessage(message);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                    return true;
                }
            }

            ConfirmGUI.openRTPGUI(player);
            return true;
        }
        return false;
    }
}