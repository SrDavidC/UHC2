package me.noobsters.minigame.teams.events;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.noobsters.minigame.teams.objects.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@RequiredArgsConstructor
public class PlayerJoinedTeamEvent extends Event implements Quiet {
    /*
     * Methods Required by BukkitAPI
     */
    private static final @Getter HandlerList HandlerList = new HandlerList();
    private final @Getter HandlerList Handlers = HandlerList;
    /*
     * Custom data, use @NonNull for the constructor
     */
    private @NonNull @Getter Team team;
    private @NonNull @Getter Player player;
    private @Getter @Setter boolean quiet;

}