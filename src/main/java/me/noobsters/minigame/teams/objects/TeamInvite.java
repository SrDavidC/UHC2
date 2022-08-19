package me.noobsters.minigame.teams.objects;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

/*
 * Data object with team invite info
 */
@Data
public class TeamInvite {
    private @NonNull @Setter(value = AccessLevel.PRIVATE) UUID teamToJoin;
    private @NonNull @Setter(value = AccessLevel.PRIVATE) UUID sender;
    private @NonNull @Setter(value = AccessLevel.PRIVATE) UUID target;

}