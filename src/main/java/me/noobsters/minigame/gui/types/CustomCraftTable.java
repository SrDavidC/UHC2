package me.noobsters.minigame.gui.types;

import me.noobsters.minigame.UHC;
import me.noobsters.minigame.gui.CustomGui;
import me.noobsters.minigame.utils.RapidInv;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class CustomCraftTable extends CustomGui {

    UHC instance = UHC.getInstance();
    List<ItemStack> items = new ArrayList<>();

    public CustomCraftTable(RapidInv gui, List<ItemStack> items) {
        super(gui, GUIType.CRAFTS);
        this.items = items;
        update();

    }

    @Override
    public void update() {
        var gui = getGui();
        var i = 0;
        for (ItemStack itemStack : items) {
            if(itemStack != null){
                gui.setItem(i, itemStack, action->{
                    var player = (Player) action.getWhoClicked();
                    player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, SoundCategory.VOICE, 1.0f, 1.0f);
                    instance.getGuiManager().getMainGui().getEnabledCraftingGui().getGui().open(player);
                });
            }
            i++;
        }
    }
}
