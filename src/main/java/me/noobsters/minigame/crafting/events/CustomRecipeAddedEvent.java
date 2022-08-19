package me.noobsters.minigame.crafting.events;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@RequiredArgsConstructor
public class CustomRecipeAddedEvent extends Event {
    /*
     * Methods Required by BukkitAPI
     */
    private @Getter static final HandlerList HandlerList = new HandlerList();
    private @Getter final HandlerList Handlers = HandlerList;
    private @Getter @NonNull CustomRecipe customRecipe;

    public CustomRecipeAddedEvent(CustomRecipe customRecipe, boolean async) {
        super(async);
        this.customRecipe = customRecipe;
    }

}