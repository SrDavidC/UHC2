package me.noobsters.minigame.gui;

import lombok.Getter;
import me.noobsters.minigame.utils.RapidInv;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public abstract class CustomGui implements Listener {
    @Getter
    RapidInv gui;
    GUIType guiType;

    public CustomGui(RapidInv gui, GUIType guiType){
        this.gui = gui;
        this.guiType = guiType;
    }

    public RapidInv getRapidInv() {
        return gui;
    }
    public void setRapidInv(RapidInv gui) {
        this.gui = gui;
    }

    public void open(Player player){
        gui.open(player);
    }

    public abstract void update();

    public enum GUIType {
        MAIN,
        CONFIG,
        CONFIRMATION,
        CUSTOMCRAFT,
        CRAFTS,
        GAMELOOP,
        SWITCH,
        SCENARIOS
    }
}

