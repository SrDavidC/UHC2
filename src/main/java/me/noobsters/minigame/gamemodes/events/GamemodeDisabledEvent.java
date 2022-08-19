package me.noobsters.minigame.gamemodes.events;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.noobsters.minigame.gamemodes.IGamemode;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@RequiredArgsConstructor
public class GamemodeDisabledEvent extends Event {
    /*
     * Methods Required by BukkitAPI
     */
    private @Getter static final HandlerList HandlerList = new HandlerList();
    private @Getter final HandlerList Handlers = HandlerList;
    private @Getter @NonNull IGamemode gamemode;

    public GamemodeDisabledEvent(IGamemode gamemode, boolean async) {
        super(async);
        this.gamemode = gamemode;
    }

}