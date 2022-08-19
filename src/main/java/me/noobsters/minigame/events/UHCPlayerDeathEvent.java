package me.noobsters.minigame.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UHCPlayerDeathEvent extends Event {
    /*
     * Methods Required by BukkitAPI
     */
    private static final @Getter HandlerList HandlerList = new HandlerList();
    @SuppressWarnings({"java:S116", "java:S1170"})
    private final @Getter HandlerList Handlers = HandlerList;
    
}