package me.noobsters.minigame.gamemodes.interfaces;

import lombok.Data;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;

import java.util.UUID;

@Data
public class TimeBombData {
    ArmorStand armorStand;
    Block left;
    Block right;
    UUID uuid;

    public TimeBombData(ArmorStand armorStand, Block left, Block right, UUID uuid){
        this.armorStand = armorStand;
        this.left = left;
        this.right = right;
        this.uuid = uuid;
    }
}
