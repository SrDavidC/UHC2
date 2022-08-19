package me.noobsters.minigame.teams.events;

import me.noobsters.minigame.teams.objects.Team;
import org.bukkit.entity.Player;

public class PlayerKickedFromTeamEvent extends PlayerLeftTeamEvent {
    /*
     * PlayerKicked is a PlayerLeftTeamEvent but with different meaning.
     */
    public PlayerKickedFromTeamEvent(final Team team, final Player player) {
        super(team, player);
    }
}
