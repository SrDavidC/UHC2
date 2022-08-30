package me.noobsters.minigame.gamemodes;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.noobsters.minigame.UHC;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * GamemodesCMD
 */
@CommandAlias("scenario|scenarios")
@RequiredArgsConstructor
public class GamemodesCMD extends BaseCommand {
    private @NonNull UHC instance;
    private String permissionDebug = "uhc.configchanges.see";

    @Default
    public void scenarios(CommandSender sender) {
        /* SCENARIOS ENABLED GUI */
        //instance.getGuiManager().getMainGui().getEnabledScenariosGui().open((Player) sender);
    }

    @Subcommand("all")
    public void allList(Player sender) {

        //instance.getGuiManager().getMainGui().getScenarioPages().get(0).open(sender);
    }


    @Subcommand("toggle")
    @CommandPermission("uhc.scenarios")
    @CommandCompletion("@scenarios")
    public void onEnable(CommandSender sender, String scenario) {
        var gamemode = getScenarioFromName(scenario);
        var senderName = ChatColor.GRAY + "[" + sender.getName().toString() + "] ";
        if (gamemode != null) {
            if (!gamemode.isEnabled()) {
                if(!gamemode.callEnable()){
                    sender.sendMessage(ChatColor.RED + "Couldn't enable " + gamemode.getName() + ".");
                }else{
                    Bukkit.getServer().broadcast  (Component.text((
                        senderName + ChatColor.YELLOW + "Scenario " + gamemode.getName() + " has been enabled.")));
                }

            } else {
                if(!gamemode.callDisable()){
                    sender.sendMessage(ChatColor.RED + "Couldn't disable " + gamemode.getName() + ".");
                }else{

                    Bukkit.getServer().broadcast  (Component.text(
                            senderName + ChatColor.YELLOW + "Scenario " + gamemode.getName() + " has been disabled."));
                }

            }
        }else if(scenario != null){
            sender.sendMessage(ChatColor.RED + "Scenario " + scenario + " doesn't exist");
        }
    }

    private IGamemode getScenarioFromName(String name) {
        for (var scenario : instance.getGamemodeManager().getGamemodesList())
            if (scenario.getName().equalsIgnoreCase(name))
                return scenario;
        return null;
    }

}