package me.noobsters.minigame.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryCloseEvent.Reason;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Manager for handling inventory events to RapidInv
 *
 * @author MrMicky
 *
 * @since v0.0.1
 */
public final class RapidInvManager {

    private static final AtomicBoolean REGISTER = new AtomicBoolean(false);

    /**
     * Register events for RapidInv
     *
     * @param plugin Plugin to register
     * @throws NullPointerException  if plugin is null
     * @throws IllegalStateException if RapidInv is already registered
     */
    public static void register(Plugin plugin) {
        Objects.requireNonNull(plugin, "plugin");

        if (REGISTER.getAndSet(true)) {
            throw new IllegalStateException("RapidInv is already registered");
        }

        Bukkit.getPluginManager().registerEvents(new InventoryListener(plugin), plugin);
    }

    /**
     * Close all open RapidInv inventories
     */
    public static void closeAll() {
        Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.getOpenInventory().getTopInventory().getHolder() instanceof RapidInv)
                .forEach(Player::closeInventory);
    }

    public static final class InventoryListener implements Listener {

        private final Plugin plugin;

        public InventoryListener(Plugin plugin) {
            this.plugin = plugin;
        }

        @EventHandler(priority = EventPriority.LOW)
        public void onInventoryClick(InventoryClickEvent e) {
            if (e.getInventory().getHolder() instanceof RapidInv && e.getClickedInventory() != null) {
                RapidInv inv = (RapidInv) e.getInventory().getHolder();

                boolean wasCancelled = e.isCancelled();
                e.setCancelled(true);

                inv.handleClick(e);

                // This prevent to uncancel the event if an other plugin cancelled it before
                if (!wasCancelled && !e.isCancelled()) {
                    e.setCancelled(false);
                }
            }
        }

        // PATCHED BY JCEDENO
        @EventHandler(priority = EventPriority.HIGHEST)
        public void preventMoving(InventoryClickEvent e) {
            var clickedInventory = e.getClickedInventory();
            var inv = e.getInventory();
            if (isRapidInventory(inv) || isRapidInventory(clickedInventory)) {
                e.setCancelled(true);
            }

        }

        private boolean isRapidInventory(Inventory inv) {
            return inv != null && inv.getHolder() instanceof RapidInv;
        }

        @EventHandler
        public void onInventoryOpen(InventoryOpenEvent e) {
            if (e.getInventory().getHolder() instanceof RapidInv) {
                RapidInv inv = (RapidInv) e.getInventory().getHolder();

                inv.handleOpen(e);
            }
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent e) {
            if (e.getInventory().getHolder() instanceof RapidInv) {
                RapidInv inv = (RapidInv) e.getInventory().getHolder();
                if (e.getReason() == Reason.PLAYER || e.getReason() == Reason.DISCONNECT) {
                    // TODO: HANDLE CLOSE
                }

                if (inv.handleClose(e)) {
                    Bukkit.getScheduler().runTask(plugin, () -> inv.open((Player) e.getPlayer()));
                }
            }
        }

        @EventHandler
        public void onPluginDisable(PluginDisableEvent e) {
            if (e.getPlugin() == plugin) {
                closeAll();

                REGISTER.set(false);
            }
        }
    }
}