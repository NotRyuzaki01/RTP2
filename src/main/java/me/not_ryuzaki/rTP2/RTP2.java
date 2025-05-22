package me.not_ryuzaki.rTP2;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

public final class RTP2 extends JavaPlugin {
    private static RTP2 instance;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        getCommand("rtp").setExecutor(new RTPCommand());
        new ConfirmClickListener(this);
    }

    public static RTP2 getInstance() {
        return instance;
    }

    public HashMap<UUID, Long> getCooldowns() {
        return cooldowns;
    }
}