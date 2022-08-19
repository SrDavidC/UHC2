package me.noobsters.minigame.gamemodes;

import lombok.Getter;
import lombok.Setter;
import me.noobsters.minigame.UHC;
import me.noobsters.minigame.gamemodes.types.*;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GamemodeManager {
    UHC instance;
    private @Getter List<IGamemode> gamemodesList = new ArrayList<>();
    private @Getter @Setter int extraOreAmount = 0;
    private @Getter @Setter int extraGold = 0;

    public GamemodeManager(UHC instance) {
        this.instance = instance;
        // Scenarios
        registerGamemode(new Cutclean(instance));
        //registerGamemode(new EnderRespawn(instance));
        // registerGamemode(new Moles(instance));
        registerGamemode(new GoToHell(instance));
        registerGamemode(new BowLess(instance));
        registerGamemode(new UnDamaxe(instance));
        registerGamemode(new SwordLess(instance));
        registerGamemode(new ShieldLess(instance));
        registerGamemode(new FireLess(instance));
        registerGamemode(new NoFall(instance));
        registerGamemode(new HasteyBoys(instance));
        registerGamemode(new Limits(instance));
        registerGamemode(new LuckyLeaves(instance));
        registerGamemode(new Timber(instance));
        registerGamemode(new DoubleOres(instance));
        registerGamemode(new TripleOres(instance));
        registerGamemode(new BloodHunter(instance));
        registerGamemode(new SkyHigh(instance));
        registerGamemode(new BackPack(instance));
        registerGamemode(new Switcheroo(instance));
        registerGamemode(new InfiniteEnchanter(instance));
        registerGamemode(new GoneFishing(instance));
        registerGamemode(new BloodDiamonds(instance));
        registerGamemode(new AdvancementHunter(instance));
        registerGamemode(new GoldenRetriever(instance));
        registerGamemode(new FlorPoderosa(instance));
        registerGamemode(new TiempoBomba(instance));
        registerGamemode(new NoClean(instance));
        //registerGamemode(new FastLeaves());
        registerGamemode(new NineSlots(instance));
        registerGamemode(new DoubleGold(instance));
        registerGamemode(new ThunderKill(instance));
        registerGamemode(new XPHunter(instance));
        registerGamemode(new FastSmelting(instance));
        //registerGamemode(new MonstersInc(instance));
        registerGamemode(new PermaGlow(instance));
        registerGamemode(new BloodExperience(instance));
        registerGamemode(new HasteyBoysPlus(instance));
        registerGamemode(new DoubleLifeBar(instance));
        registerGamemode(new MeetupDoubleLifeBar(instance));
        registerGamemode(new Metrallesta(instance));
        registerGamemode(new ColdWeapons(instance));
        registerGamemode(new HeavyPockets(instance));
        registerGamemode(new HasteyBabies(instance));
        registerGamemode(new DamageCycle(instance));
        registerGamemode(new CrossBowLess(instance));
        registerGamemode(new Popeye(instance));
        registerGamemode(new DiamondLess(instance));
        registerGamemode(new SuperHeroes(instance));
        registerGamemode(new BareBones(instance));
        registerGamemode(new Cripple(instance));
        registerGamemode(new FastGetaway(instance));
        registerGamemode(new VengefulSpirits(instance));
        registerGamemode(new FallOut(instance));
        registerGamemode(new Soup(instance));
        registerGamemode(new ChickenFight(instance));
        registerGamemode(new EnderRespawnLeader(instance));
        registerGamemode(new BleedingSweets(instance));

        // Scenarios pack
        registerGamemode(new UHCRun(instance, this));
        registerGamemode(new MetaGame(instance));
        
        // Invitados
        //registerGamemode(new UHCVandalico(instance));
        //registerGamemode(new UHCLatam(instance));
        //registerGamemode(new UHCGuest(instance));

        // mode
        //registerGamemode(new UHCMeetup(instance));

        instance.getCommandManager().getCommandCompletions().registerAsyncCompletion("scenarios",
                c -> gamemodesList.stream().map(IGamemode::getName).collect(Collectors.toList()));

    }

    public void registerGamemode(IGamemode gamemode) {
        gamemodesList.add(gamemode);
    }

    public Collection<IGamemode> getEnabledGamemodes() {
        final List<IGamemode> list = new ArrayList<>();
        // Remove all scenarios inside pack if pack is toggled.
        var uhcRun = getScenario(UHCRun.class);
        if (uhcRun.isEnabled()) {
            list.removeAll(uhcRun.getGamemodes());
        }

        var metaGame = getScenario(MetaGame.class);
        if (metaGame.isEnabled()) {
            list.removeAll(metaGame.getGamemodes());
        }

        gamemodesList.forEach(gamemode -> {
            if (gamemode.isEnabled())
                list.add(gamemode);
        });

        return list;
    }

    public String[] getEnabledAsArray() {
        /** Helpers */
        UHCRun uhcRun = null;
        MetaGame metaGame = null;
        /** Scenario Array */
        var str = new ArrayList<IGamemode>();
        for (var gm : gamemodesList) {
            if (gm.isEnabled()) {
                if (gm instanceof UHCRun) {
                    uhcRun = (UHCRun) gm;
                } else if (gm instanceof MetaGame) {
                    metaGame = (MetaGame) gm;
                }
                str.add(gm);
            }
        }
        if (uhcRun != null)
            str.removeAll(uhcRun.getGamemodes());

        if (metaGame != null)
            str.removeAll(metaGame.getGamemodes());
        return str.stream().map(IGamemode::getName).toArray(String[]::new);
    }

    public <T extends IGamemode> T getScenario(Class<T> clazz) {
        for (var gamemode : gamemodesList)
            if (gamemode.getClass() == clazz)
                return clazz.cast(gamemode);

        return null;
    }

    public <T extends IGamemode> boolean isScenarioEnable(Class<T> clazz) {
        for (var gamemode : gamemodesList) {
            if (gamemode.isEnabled() && gamemode.getClass() == clazz) {
                return true;
            }

        }
        return false;
    }

    //

    public String getEnabledGamemodesToString() {
        var sb = new StringBuilder();
        var enabledScenarios = getEnabledGamemodes();

        var uhcRun = getScenario(UHCRun.class);
        if (uhcRun.isEnabled()) {
            enabledScenarios.removeAll(uhcRun.getGamemodes());
        }

        var metaGame = getScenario(MetaGame.class);
        if (metaGame.isEnabled()) {
            enabledScenarios.removeAll(metaGame.getGamemodes());
        }

        var iter = enabledScenarios.iterator();

        if (iter.hasNext())
            while (iter.hasNext())
                sb.append(iter.next().getName() + (iter.hasNext() ? ", " : "."));
        else
            sb.append("Vanilla+");

        return sb.toString();
    }

    public String getFirstEnabledScenario() {
        for (var gm : gamemodesList) {
            if (gm.isEnabled() && gm.getClass() != UHCMeetup.class && gm.getClass() != NoClean.class)
                return gm.getName();
        }
        return "Vanilla";
    }

    public BaseComponent[] getScenariosWithDescription() {
        var componentBuilder = new ComponentBuilder();
        var enabledScenarios = getEnabledGamemodes();

        var uhcRun = getScenario(UHCRun.class);
        if (uhcRun.isEnabled()) {
            enabledScenarios.removeAll(uhcRun.getGamemodes());
        }

        var metaGame = getScenario(MetaGame.class);
        if (metaGame.isEnabled()) {
            enabledScenarios.removeAll(metaGame.getGamemodes());
        }

        var iter = enabledScenarios.iterator();
        componentBuilder.color(ChatColor.WHITE);

        if (iter.hasNext()) {
            while (iter.hasNext()) {
                var scenario = iter.next();
                componentBuilder.append(scenario.getName() + (iter.hasNext() ? ", " : "."))
                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(scenario.getDescription())));
            }

        } else {
            componentBuilder.append("Vanilla+").event(
                    new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Classic Minecraft Vanilla UHC experience.")));
        }

        return componentBuilder.create();
    }

}