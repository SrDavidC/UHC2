package me.noobsters.minigame.gamemodes.interfaces;

import lombok.Data;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;

@Data
public class TimeBombData {
    ArmorStand armorStand;
    Block left;
    Block right;

    public TimeBombData(ArmorStand armorStand, Block left, Block right){
        this.armorStand = armorStand;
        this.left = left;
        this.right = right;
    }
}
