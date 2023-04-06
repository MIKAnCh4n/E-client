package zyx.existent.module;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import zyx.existent.module.modules.combat.AntiBot;
import zyx.existent.module.modules.combat.AutoClicker;
import zyx.existent.module.modules.combat.AutoSword;
import zyx.existent.module.modules.combat.Criticals;
import zyx.existent.module.modules.combat.ExtendedReach;
import zyx.existent.module.modules.combat.HitBox;
import zyx.existent.module.modules.combat.KillAura;
import zyx.existent.module.modules.combat.Regen;
import zyx.existent.module.modules.combat.SuperKB;
import zyx.existent.module.modules.combat.Velocity;
import zyx.existent.module.modules.combat.WTap;
import zyx.existent.module.modules.hud.ClickGui;
import zyx.existent.module.modules.hud.HUD;
import zyx.existent.module.modules.hud.TabGui;
import zyx.existent.module.modules.misc.AdminChecker;
import zyx.existent.module.modules.misc.Annoy;
import zyx.existent.module.modules.misc.AntiDebuff;
import zyx.existent.module.modules.misc.AntiDesync;
import zyx.existent.module.modules.misc.AntiImmobilizer;
import zyx.existent.module.modules.misc.AntiTabComplete;
import zyx.existent.module.modules.misc.AntiVanish;
import zyx.existent.module.modules.misc.AutoL;
import zyx.existent.module.modules.misc.BetterSounds;
import zyx.existent.module.modules.misc.Blink;
import zyx.existent.module.modules.misc.ChatFilter;
import zyx.existent.module.modules.misc.Civbreak2;
import zyx.existent.module.modules.misc.Disabler;
import zyx.existent.module.modules.misc.Freecam;
import zyx.existent.module.modules.misc.PingSpoof;
import zyx.existent.module.modules.misc.Plugins;
import zyx.existent.module.modules.misc.PotionSaver;
import zyx.existent.module.modules.misc.ServerCrasher;
import zyx.existent.module.modules.misc.Spammer;
import zyx.existent.module.modules.misc.StreamerMode;
import zyx.existent.module.modules.misc.TextureStatus;
import zyx.existent.module.modules.movement.AntiVoid;
import zyx.existent.module.modules.movement.Flight;
import zyx.existent.module.modules.movement.Glide;
import zyx.existent.module.modules.movement.Jesus;
import zyx.existent.module.modules.movement.KeepSprint;
import zyx.existent.module.modules.movement.LongJump;
import zyx.existent.module.modules.movement.NoSlowDown;
import zyx.existent.module.modules.movement.Phase;
import zyx.existent.module.modules.movement.Scaffold;
import zyx.existent.module.modules.movement.Sneak;
import zyx.existent.module.modules.movement.Speed;
import zyx.existent.module.modules.movement.Sprint;
import zyx.existent.module.modules.movement.Step;
import zyx.existent.module.modules.movement.TargetStrafe;
import zyx.existent.module.modules.movement.TerrainSpeed;
import zyx.existent.module.modules.movement.WaterSpeed;
import zyx.existent.module.modules.other.Debug;
import zyx.existent.module.modules.other.LagCheck;
import zyx.existent.module.modules.player.AutoArmor;
import zyx.existent.module.modules.player.AutoRespawn;
import zyx.existent.module.modules.player.AutoTool;
import zyx.existent.module.modules.player.ChestStealer;
import zyx.existent.module.modules.player.FastUse;
import zyx.existent.module.modules.player.InvMove;
import zyx.existent.module.modules.player.InventoryManager;
import zyx.existent.module.modules.player.NoFall;
import zyx.existent.module.modules.player.SkinFlash;
import zyx.existent.module.modules.player.SpeedMine;
import zyx.existent.module.modules.visual.Ambience;
import zyx.existent.module.modules.visual.Animations;
import zyx.existent.module.modules.visual.Chams;
import zyx.existent.module.modules.visual.ChestESP;
import zyx.existent.module.modules.visual.Cosmetics;
import zyx.existent.module.modules.visual.Crack;
import zyx.existent.module.modules.visual.Croshair;
import zyx.existent.module.modules.visual.ESP2;
import zyx.existent.module.modules.visual.FullBright;
import zyx.existent.module.modules.visual.Hurtcam;
import zyx.existent.module.modules.visual.ItemPhysic;
import zyx.existent.module.modules.visual.KillCounter;
import zyx.existent.module.modules.visual.NameTags;
import zyx.existent.module.modules.visual.NameTags2;
import zyx.existent.module.modules.visual.NoTitle;
import zyx.existent.module.modules.visual.Outline;
import zyx.existent.module.modules.visual.Projectiles;
import zyx.existent.module.modules.visual.Rader;
import zyx.existent.module.modules.visual.ScoreBoard;
import zyx.existent.module.modules.visual.ShowInvis;
import zyx.existent.module.modules.visual.Skeleton;
import zyx.existent.module.modules.visual.TargetHUD;
import zyx.existent.module.modules.visual.ViewClip;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<Module>();
    private boolean isSetup;

    /**
     * Setup
     */
    public void setup() {
        add(new Criticals("Criticals", "", 0, Category.Combat));
        add(new Sprint("Sprint", "AutoSprinting", 0, Category.Movement));
        add(new NameTags("NameTags", "", 0, Category.Visual));
        add(new NoFall("NoFall", "", 0, Category.Player));
        add(new ESP2("ESP", "", 0, Category.Visual));
        add(new WTap("WTap", "", 0, Category.Combat));
        add(new AutoArmor("Auto Armor", "", 0, Category.Player));
        add(new InvMove("Inventory Move", "", 0, Category.Player));
        add(new KeepSprint("Keep Sprint", "", 0, Category.Movement));
        add(new NoSlowDown("No Slow Down", "", 0, Category.Movement));
        add(new KillAura("Kill Aura", "", Keyboard.KEY_R, Category.Combat));
        add(new Step("Step", "", 0, Category.Movement));
        add(new ShowInvis("Show Invis", "", 0, Category.Visual));
        add(new PingSpoof("Ping Spoof", "", 0, Category.Misc));
        add(new Speed("Speed", "", Keyboard.KEY_V, Category.Movement));
        add(new Glide("Glide", "", Keyboard.KEY_B, Category.Movement));
        add(new AntiBot("Anti Bot", "", 0, Category.Combat));
        add(new TextureStatus("Texture Status", "", 0, Category.Misc));
        add(new AntiVoid("Anti Void", "", 0, Category.Movement));
        add(new TargetStrafe("Target Strafe", "", 0, Category.Movement));
        add(new Jesus("Jesus", "", 0, Category.Movement));
        add(new AutoSword("Auto Sword", "", 0, Category.Combat));
        add(new Velocity("Velocity", "", 0, Category.Combat));
        add(new ChatFilter("Chat Filter", "", 0, Category.Misc));
        add(new Flight("Flight", "You can Fly!!", Keyboard.KEY_G, Category.Movement));
        add(new ChestStealer("Chest Stealer", "", 0, Category.Player));
        add(new Skeleton("Skeleton", "", 0, Category.Visual));
        add(new AntiDesync("Anti Desync", "No Rotation", 0, Category.Misc));
        add(new Ambience("Ambience", "", 0, Category.Visual));
        add(new Spammer("Spammer", "", 0, Category.Misc));
        add(new Scaffold("Scaffold", "", Keyboard.KEY_F, Category.Movement));
        add(new TargetHUD("TargetHUD", "", 0, Category.Visual));
        add(new LongJump("Long Jump", "", 0, Category.Movement));
        add(new BetterSounds("Better Sounds", "", 0, Category.Misc));
        add(new Animations("Animations", "", 0, Category.Visual));
        add(new InventoryManager("Inventory Manager", "", 0, Category.Player));
        add(new Chams("Chams", "", 0, Category.Visual));
        add(new Croshair("Croshair", "", 0, Category.Visual));
        add(new Outline("Outline", "", 0, Category.Visual));
        add(new Disabler("Disabler", "", 0, Category.Misc));
        add(new SpeedMine("Speed Mine", "", 0, Category.Player));
        add(new ServerCrasher("Server Crasher", "", 0, Category.Misc));
        add(new Annoy("Annoy", "", 0, Category.Misc));
        add(new Crack("Crack", "", 0, Category.Visual));
        add(new AntiTabComplete("Anti TabComplete", "", 0, Category.Misc));
        add(new SkinFlash("Skin Flash", "", 0, Category.Player));
        add(new StreamerMode("StreamerMode", "", 0, Category.Misc));
        add(new Hurtcam("Hurtcam", "", 0, Category.Visual));
        add(new ChestESP("ChestESP", "", 0, Category.Visual));
        add(new Regen("Regen", "", 0, Category.Combat));
        add(new ItemPhysic("Item Physic", "", 0, Category.Visual));
        add(new Plugins("Plugin Checker", "", 0, Category.Misc));
        add(new FullBright("FullBright", "", 0, Category.Visual));
        add(new NoTitle("No Title", "Delete TitleMassage", 0, Category.Visual));
        add(new ScoreBoard("Scoreboard", "", 0, Category.Visual));
        add(new FastUse("FastUse", "", 0, Category.Player));
        add(new AntiVanish("Anti Vanish", "", 0, Category.Misc));
        add(new AutoTool("Auto Tool", "", 0, Category.Player));
        add(new AutoRespawn("Auto Respawn", "", 0, Category.Player));
        add(new Sneak("Sneak", "", 0, Category.Movement));
        add(new PotionSaver("Potion Saver", "", 0, Category.Misc));
        add(new HitBox("HitBox", "", 0, Category.Combat));
        add(new Blink("Blink", "", 0, Category.Misc));
        add(new ExtendedReach("Extended Reach", "", 0, Category.Combat));
        add(new Freecam("Freecam", "", Keyboard.KEY_U, Category.Misc));
        add(new AntiDebuff("Anti Debuff", "", 0, Category.Misc));
        add(new LagCheck("LagCheck", "", 0, Category.Other));
        add(new Rader("Rader", "", 0, Category.Visual));
        add(new AutoL("AutoL", "", 0, Category.Misc));
        add(new SuperKB("SuperKB", "", 0, Category.Combat));
        add(new Cosmetics("Cosmetics", "", 0, Category.Visual));
        add(new NameTags2("NameTags2", "", 0, Category.Visual));
        add(new AdminChecker("Admin Checker", "", 0, Category.Misc));
        add(new Civbreak2("Civbreak", "", Keyboard.KEY_X, Category.Misc));
        add(new WaterSpeed("WaterSpeed", "", 0, Category.Movement));
        add(new Projectiles("Projectiles", "", 0, Category.Visual));
        add(new AutoClicker("Auto Clicker", "", 0, Category.Combat));
        add(new AntiImmobilizer("Anti Immobilizer", "", 0, Category.Misc));
        add(new TerrainSpeed("TerrainSpeed", "", 0, Category.Movement));
        add(new Phase("Phase", "", Keyboard.KEY_N, Category.Movement));
        add(new KillCounter("Kill Counter", "" , 0, Category.Visual));
        add(new ViewClip("ViewClip", "", 0, Category.Visual));

        add(new Debug("Debug", "", 0, Category.Other));
        add(new HUD("HUD", "Display the HUD on the screen.", 0, Category.Other));
        add(new ClickGui("ClickGui", "", Keyboard.KEY_RSHIFT, Category.Other));
        add(new TabGui("TabGui", "", 0, Category.Other));

        if (!isEnabled(HUD.class)) {
            getClazz(HUD.class).toggle();
        }
        if (!isEnabled(TabGui.class)) {
            getClazz(TabGui.class).toggle();
        }

        isSetup = true;
    }

    public void add(Module mod){
        modules.add(mod);
    }
    public boolean isEnabled(Class<?> clazz) {
        Module module = getClazz(clazz);
        return (module != null && module.isEnabled());
    }
    public Module getClazz(Class<?> clazz) {
        try {
            for (Module feature : getModules()) {
                if (feature.getClass() == clazz)
                    return feature;
            }
        } catch (Exception ignored) {
        }
        return null;
    }
    public Module getString(String clazz) {
        for (Module module : getModules()) {
            if (module.getName().toLowerCase().replaceAll(" ", "").equals(clazz.toLowerCase())) {
                return module;
            }
        }
        return null;
    }
    public ArrayList<Module> getModuleByCategory(Category cat) {
        ArrayList<Module> mods = new ArrayList<>();
        for (Module module : getModules()) {
            if (module.getCategory() == cat && !module.isHidden()) {
                mods.add(module);
            }
        }
        return mods.isEmpty() ? null : mods;
    }
    public ArrayList<Module> getModules(){
        return this.modules;
    }

    public boolean isSetup() {
        return isSetup;
    }
}
