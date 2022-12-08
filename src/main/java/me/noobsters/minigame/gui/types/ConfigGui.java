package me.noobsters.minigame.gui.types;

import fr.mrmicky.fastinv.ItemBuilder;
import me.noobsters.minigame.UHC;
import me.noobsters.minigame.gui.CustomGui;
import me.noobsters.minigame.utils.RapidInv;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.text.DecimalFormat;


public class ConfigGui extends CustomGui {
    UHC instance = UHC.getInstance();
    DecimalFormat numberFormat = new DecimalFormat("#0.0");
    String permissionConfig = "uhc.config.cmd";

    SwitchGui appleRateSwitch = new SwitchGui(new RapidInv(InventoryType.HOPPER, "Apple rate"), 0.1f);
    SwitchGui flintRateSwitch = new SwitchGui(new RapidInv(InventoryType.HOPPER, "Flint rate"), 0.1f);
    
    public ConfigGui(RapidInv gui) {
        super(gui, GUIType.MAIN);

        var apple = new ItemBuilder(Material.APPLE).name(ChatColor.YELLOW + "Apple rate").lore(ChatColor.GREEN + "Confirm").build();
        appleRateSwitch.getGui().setItem(2, apple, action->{
            var player = (Player) action.getWhoClicked();
            if(instance.getGame().getAppleRate() != appleRateSwitch.getValue()){
                Bukkit.dispatchCommand(player, "config apple-rate "+ appleRateSwitch.getValue());
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
            }else{
                player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, SoundCategory.VOICE, 1.0f, 1.0f);
            }
            getGui().open(player);
            
        });

        var flint = new ItemBuilder(Material.FLINT).name(ChatColor.YELLOW + "Flint rate").lore(ChatColor.GREEN + "Confirm").build();
        flintRateSwitch.getGui().setItem(2, flint, action->{
            var player = (Player) action.getWhoClicked();
            if(instance.getGame().getFlintRate() != flintRateSwitch.getValue()){
                Bukkit.dispatchCommand(player, "config flint-rate "+ flintRateSwitch.getValue());
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
            }else{
                player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, SoundCategory.VOICE, 1.0f, 1.0f);
            }
            getGui().open(player);
            
        });

        gui.setItem(17, new ItemBuilder(Material.KNOWLEDGE_BOOK).name(ChatColor.GREEN + "UHC Game").build(), action -> {
            var player = (Player) action.getWhoClicked();
            instance.getGuiManager().getMainGui().open((Player) action.getWhoClicked());
            player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, SoundCategory.VOICE, 1.0f, 1.0f);
        });
        
        update();

    }

    @Override
    public void update() {

        updateAppleRate();
        updateFlintRate();
        updateNether(null);
        updateAdvancements(null);
        updateHorses(null);
        updateBeds(null);
        updateBedsNerf(null);
        updateStrength(null);
        updateStrengthNerf(null);
        updatePotions(null);
        updateTrident(null);
        updateItemsBurn(null);
        updateTears(null);
        updateTrades(null);
        updatePotionsTier(null);
        updateCobWeb(null);
    }

    public void updateCobWeb(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.COBWEB).name(ChatColor.YELLOW + "Cobwebs")
            .addLore((game.isCobweb()? ChatColor.GREEN : ChatColor.RED) + "" + game.isCobweb()).build();
        gui.setItem(15, item);
            if (player != null){
                if(player.hasPermission(permissionConfig)){
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                    Bukkit.dispatchCommand(player, "config cobweb "+ !instance.getGame().isCobweb());
                    if (instance.getGame().isTrades()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                    }
                    gui.open(player);
                }
            }
    }

    public void updatePotionsTier(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.GLOWSTONE_DUST).name(ChatColor.YELLOW + "Potions tier II")
            .addLore((game.isPotionsTier()? ChatColor.GREEN : ChatColor.RED) + "" + game.isPotionsTier()).build();
        gui.setItem(14, item);
        if (player != null){
            if(player.hasPermission(permissionConfig)){
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config potions-tier "+ !instance.getGame().isPotionsTier());
                if (instance.getGame().isTrades()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }

    }

    public void updateTrades(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.EMERALD).name(ChatColor.YELLOW + "Trades")
            .addLore((game.isTrades() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isTrades()).build();

        gui.setItem(13, item);
        if (player != null){
            if(player.hasPermission(permissionConfig)){
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config trades "+ !instance.getGame().isTrades());
                if (instance.getGame().isTrades()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }

    public void updateTears(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.GHAST_TEAR).name(ChatColor.YELLOW + "Ghast tears")
            .addLore((game.isTears() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isTears()).build();
        gui.setItem(12, item);
        if (player != null){
            if(player.hasPermission(permissionConfig)){
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config tears "+ !instance.getGame().isTears());
                if (instance.getGame().isTears()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }

    public void updateItemsBurn(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.FLINT_AND_STEEL).name(ChatColor.YELLOW + "Items burn")
            .addLore((game.isItemsBurn() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isItemsBurn()).build();

        gui.setItem(11, item);
        if (player != null){
            if(player.hasPermission(permissionConfig)){
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config items-burn "+ !instance.getGame().isItemsBurn());
                if (instance.getGame().isItemsBurn()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }

    public void updateTrident(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.TRIDENT).name(ChatColor.YELLOW + "Trident")
            .addLore(ChatColor.GREEN + (game.isTrident() ? "100% drop" : "Vanilla")).flags(ItemFlag.HIDE_ATTRIBUTES).build();

        gui.setItem(10, item);
        if (player != null){
            if(player.hasPermission(permissionConfig)){
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config trident "+ !instance.getGame().isTrident());
                if (instance.getGame().isTrident()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }

    }

    public void updatePotions(Player player) {
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.BREWING_STAND).name(ChatColor.YELLOW + "Potions")
                .addLore((game.isPotions() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isPotions()).flags(ItemFlag.HIDE_ATTRIBUTES).build();

        gui.setItem(9, item);
        if (player != null) {
            if (player.hasPermission(permissionConfig)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config potions " + !instance.getGame().isPotions());
                if (instance.getGame().isPotions()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }

    public void updateStrengthNerf(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.SPLASH_POTION).name(ChatColor.YELLOW + "Nerfed strength 50%")
            .addLore((game.isStrengthNerf() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isStrengthNerf())
                .meta(PotionMeta.class, meta-> meta.setBasePotionData(new PotionData(PotionType.STRENGTH, false, false))).flags(ItemFlag.HIDE_POTION_EFFECTS).build();
        gui.setItem(8, item);
        if (player != null) {
            if (player.hasPermission(permissionConfig)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config strength-nerf "+ !instance.getGame().isStrengthNerf());
                if (instance.getGame().isStrengthNerf()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }


    public void updateStrength(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.POTION).name(ChatColor.YELLOW + "Strength")
            .addLore((game.isStrength() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isStrength())
                .meta(PotionMeta.class, meta-> meta.setBasePotionData(new PotionData(PotionType.STRENGTH, false, false))).flags(ItemFlag.HIDE_POTION_EFFECTS).build();
        gui.setItem(7, item);
            if (player != null) {
                if (player.hasPermission(permissionConfig)) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                    Bukkit.dispatchCommand(player, "config strength "+ !instance.getGame().isStrength());
                    if (instance.getGame().isStrengthNerf()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                    }
                    gui.open(player);
                }
            }

    }

    public void updateBedsNerf(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.LIGHT_BLUE_BED).name(ChatColor.YELLOW + "Nerfed bed explosion")
            .addLore((game.isBedsNerf() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isBedsNerf()).build();
        gui.setItem(6, item);
        if (player != null) {
            if (player.hasPermission(permissionConfig)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config beds-nerf "+ !instance.getGame().isBedsNerf());
                if (instance.getGame().isBedsNerf()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }

    public void updateBeds(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.BLUE_BED).name(ChatColor.YELLOW + "Beds")
            .addLore((game.isBeds() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isBeds()).build();
        gui.setItem(5, item);
        if (player != null) {
            if (player.hasPermission(permissionConfig)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config beds "+ !instance.getGame().isBeds());
                if (instance.getGame().isBeds()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }

    }

    public void updateHorses(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.SADDLE).name(ChatColor.YELLOW + "Horses")
            .addLore((game.isHorses() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isHorses()).build();
        gui.setItem(4, item);
        if (player != null) {
            if (player.hasPermission(permissionConfig)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config horses "+ !instance.getGame().isHorses());
                if (instance.getGame().isHorses()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }

    public void updateAdvancements(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.BOOKSHELF).name(ChatColor.YELLOW + "Advancements")
            .addLore((game.isAdvancements() ? ChatColor.GREEN : ChatColor.RED) + "" + game.isAdvancements()).build();
        gui.setItem(3, item);
        if (player != null) {
            if (player.hasPermission(permissionConfig)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config advancements "+ !instance.getGame().isAdvancements());
                if (instance.getGame().isAdvancements()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }

    }

    public void updateNether(Player player){
        var gui = getGui();
        var game = instance.getGame();
        var item = new ItemBuilder(Material.CRYING_OBSIDIAN).name(ChatColor.YELLOW + "Nether")
            .addLore((game.isNether() ? ChatColor.GREEN : ChatColor.RED) + "" + instance.getGame().isNether()).build();
        gui.setItem(2, item);
        if (player != null) {
            if (player.hasPermission(permissionConfig)) {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f, 0.1f);
                Bukkit.dispatchCommand(player, "config nether "+ !instance.getGame().isNether());
                if (instance.getGame().isNether()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1, 0.6f);
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, SoundCategory.VOICE, 1.0f, 1);
                }
                gui.open(player);
            }
        }
    }

    public void updateFlintRate(){
        var gui = getGui();
        var formatted = numberFormat.format(instance.getGame().getFlintRate());
        var item = new ItemBuilder(Material.FLINT).name(ChatColor.YELLOW + "Flint rate").addLore(ChatColor.GREEN + "" + formatted + "%").build();

        gui.setItem(1, item, action ->{
            var player = (Player) action.getWhoClicked();
            if(player.hasPermission(permissionConfig)){

                flintRateSwitch.setValue(instance.getGame().getFlintRate());
                flintRateSwitch.update();
                flintRateSwitch.open(player);
                
            }
        });
    }

    public void updateAppleRate(){
        var gui = getGui();
        var formatted = numberFormat.format(instance.getGame().getAppleRate());
        var item = new ItemBuilder(Material.APPLE).name(ChatColor.YELLOW + "Apple rate").addLore(ChatColor.GREEN + "" + formatted + "%").build();

        gui.setItem(0, item, action ->{
            var player = (Player) action.getWhoClicked();
            if(player.hasPermission(permissionConfig)){

                appleRateSwitch.setValue(instance.getGame().getAppleRate());
                appleRateSwitch.update();
                appleRateSwitch.open(player);
                
            }
        });
    }

}
