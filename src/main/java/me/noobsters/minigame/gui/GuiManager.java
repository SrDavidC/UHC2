package me.noobsters.minigame.gui;

import lombok.Getter;
import lombok.Setter;
import me.noobsters.minigame.UHC;
import me.noobsters.minigame.crafting.events.CustomRecipeAddedEvent;
import me.noobsters.minigame.crafting.events.CustomRecipeRemovedEvent;
import me.noobsters.minigame.events.ConfigChangeEvent;
import me.noobsters.minigame.gamemodes.events.GamemodeDisabledEvent;
import me.noobsters.minigame.gamemodes.events.GamemodeEnabledEvent;
import me.noobsters.minigame.gui.types.ConfigGui;
import me.noobsters.minigame.gui.types.MainGui;
import me.noobsters.minigame.utils.RapidInv;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GuiManager implements Listener {

    UHC instance;

    private @Getter
    @Setter
    MainGui mainGui = new MainGui(new RapidInv(InventoryType.HOPPER, "UHC Game"));
    String permissionConfig = "uhc.config.cmd";
    ArrayList<String> name;
    public GuiManager(UHC instance) {
        this.instance = instance;
        instance.getServer().getPluginManager().registerEvents(this, instance);
        name = new ArrayList<>();
        name.add("UHC Game");
        name.add("Game Loop");
        name.add("Scenarios");
        name.add("Settings");
        name.add("Custom Crafting");
        name.add("All Scenarios");

    }

    private void getInventoryType(MainGui m, String name, Player player, ItemStack item, int slot) {

        if (name.equals("UHC Game")) {
            executeUHCGameGUI(m,item.getType(),player);
        } else if (name.equals("Game Loop")) {
            if (item.getType() == Material.KNOWLEDGE_BOOK) {
                m.open(player);
            }
        } else if (name.equals("Scenarios")) {
            executeScenariosAll(m,item,player);
            // nothing to do, gui is not functional yet
        } else if (name.equals("Settings")) {
            executeConfigGUI(m,item.getType(),player);
        } else if (name.equals("Custom Crafting")) {
            executeCraftingAll(m,item,player);
        }
    }

    private void executeScenariosAll(MainGui m, ItemStack item, Player player) {
        if (item.getType() != Material.TIPPED_ARROW && item.getType() != Material.KNOWLEDGE_BOOK) {
            if (item.getItemMeta().getDisplayName() != null && player.hasPermission(permissionConfig) ) {
                Bukkit.dispatchCommand(player
                        ,"scenarios toggle "
                                + ChatColor.stripColor(item.getItemMeta().getDisplayName()));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f,
                        0.1f);
            }
        } else {
            switch (item.getType()) {
                case KNOWLEDGE_BOOK:
                    m.open(player);
                    break;
                case TIPPED_ARROW:
                    m.getScenarioPages().get(1).open(player);
                    break;
            }
        }
    }

    private void executeCraftingAll(MainGui m, ItemStack item, Player player) {
        if (item.getType() != Material.TIPPED_ARROW && item.getType() != Material.KNOWLEDGE_BOOK) {
            if (item.getItemMeta().getDisplayName() != null && player.hasPermission(permissionConfig) ) {
                Bukkit.dispatchCommand(player
                        ,"crafting toggle "
                                + ChatColor.stripColor(item.getItemMeta().getDisplayName()));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, SoundCategory.VOICE, 1.0f,
                        0.1f);
            }
        } else {
            switch (item.getType()) {
                case KNOWLEDGE_BOOK:
                    m.open(player);
                    break;
                case TIPPED_ARROW:
                    // not next pages yet
                    break;
            }
        }
    }


    private void executeConfigGUI(MainGui m, Material material, Player p) {
        ConfigGui configGui = m.getConfigGui();
        if (p.hasPermission(permissionConfig)) {
            switch (material) {
                case EMERALD:
                    configGui.updateTrades(p);
                    break;
                case GHAST_TEAR:
                    configGui.updateTears(p);
                    break;
                case FLINT_AND_STEEL:
                    configGui.updateItemsBurn(p);
                    break;
                case CRYING_OBSIDIAN:
                    configGui.updateNether(p);
                    break;
                case SADDLE:
                    configGui.updateHorses(p);
                    break;
                case BLUE_BED:
                    configGui.updateBeds(p);
                    break;
                case LIGHT_BLUE_BED:
                    configGui.updateBedsNerf(p);
                    break;
                case POTION:
                    configGui.updateStrength(p);
                    break;
                case SPLASH_POTION:
                    configGui.updateStrengthNerf(p);
                    break;
                case BREWING_STAND:
                    configGui.updatePotions(p);
                    break;
                case TRIDENT:
                    configGui.updateTrident(p);
                    break;
                case COBWEB:
                    configGui.updateCobWeb(p);
                    break;
                case GLOWSTONE_DUST:
                    configGui.updatePotionsTier(p);
                case BOOKSHELF:
                    configGui.updatePotionsTier(p);
                case KNOWLEDGE_BOOK:
                    mainGui.open(p);
            }
        }
    }

    private void executeUHCGameGUI(MainGui m, Material material, Player p) {
        switch (material) {
            case IRON_SWORD:
                m.getGameLoopGui().open(p);
                break;
            case CRAFTING_TABLE:
                m.getToggleCraftingGui().open(p);
                break;
            case TOTEM_OF_UNDYING:
                m.getEnabledScenariosGui().open(p);
                break;
            case ANVIL:
                m.getConfigGui().open(p);
                break;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null)
            return;

        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (name.contains(e.getView().getTitle()) ) {
            e.setCancelled(true);
            getInventoryType(this.mainGui, e.getView().getTitle(),p,item, e.getSlot());
            p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_TURTLE, SoundCategory.VOICE, 1.0f, 1.0f);
        }
    }

    @EventHandler
    public void scenarioEnable(GamemodeEnabledEvent e) {
        mainGui.getEnabledScenariosGui().update();
        mainGui.updateGame();
        mainGui.updateScenarioPages(null);
    }

    @EventHandler
    public void scenarioDisable(GamemodeDisabledEvent e) {
        mainGui.getEnabledScenariosGui().update();
        mainGui.updateGame();
        mainGui.updateScenarioPages(null);
    }

    @EventHandler
    public void craftAdded(CustomRecipeAddedEvent e){
        mainGui.getEnabledCraftingGui().update();
        mainGui.updateCrafting();
        mainGui.updateToggleCraftingGui();
    }

    @EventHandler
    public void craftRemoved(CustomRecipeRemovedEvent e){
        mainGui.getEnabledCraftingGui().update();
        mainGui.updateCrafting();
        mainGui.updateToggleCraftingGui();
    }

    @EventHandler
    public void configChange(ConfigChangeEvent e){
        mainGui.updateSettings();
        mainGui.updateGameLoop();
        var configGui = mainGui.getConfigGui();
        var gameLoopGui = mainGui.getGameLoopGui();
        switch (e.getConfigType()) {

            case PVP_TIME: gameLoopGui.updatePvpTime();
                break;
            case HEAL_TIME: gameLoopGui.updateHealTime();
                break;
            case BORDER_TIME: gameLoopGui.updateBorderTime();
                break;
            case BORDER_SIZE: gameLoopGui.updateBorderSize();
                break;
            case BORDER_CENTER_TIME: gameLoopGui.updateBorderCenterTime();
                break;
            case TEAM_SIZE: mainGui.updateGame();
                break;
            case SLOTS:
            case HOSTNAME:
            case GAME: mainGui.updateInfo();
                break;
            case APPLE_RATE: configGui.updateAppleRate();
                break;
            case FLINT_RATE: configGui.updateFlintRate();
                break;
            case NETHER: configGui.updateNether(null);
                break;
            case ADVANCEMENTS: configGui.updateAdvancements(null);
                break;
            case HORSES: configGui.updateHorses(null);
                break;
            case BEDS: configGui.updateBeds(null);
                break;
            case BEDS_NERF: configGui.updateBedsNerf(null);
                break;
            case POTIONS: configGui.updatePotions(null);
                break;
            case POTIONS_TIER: configGui.updatePotionsTier(null);
                break;
            case STRENGTH: configGui.updateStrength(null);
                break;
            case STRENGTH_NERF: configGui.updateStrengthNerf(null);
                break;
            case TRADES: configGui.updateTrades(null);
                break;
            case ITEMS_BURN: configGui.updateItemsBurn(null);
                break;
            case TRIDENT: configGui.updateTrident(null);
                break;
            case TEARS: configGui.updateTears(null);
                break;
            case COBWEB: configGui.updateCobWeb(null);
                break;
        
            default:
                break;
        }
    }


}

