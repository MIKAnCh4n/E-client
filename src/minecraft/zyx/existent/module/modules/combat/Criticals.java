package zyx.existent.module.modules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import zyx.existent.Existent;
import zyx.existent.event.Event;
import zyx.existent.event.EventTarget;
import zyx.existent.event.events.EventAttack;
import zyx.existent.event.events.EventPacket;
import zyx.existent.event.events.EventStep;
import zyx.existent.event.events.EventUpdate;
import zyx.existent.module.Category;
import zyx.existent.module.Module;
import zyx.existent.module.data.Options;
import zyx.existent.module.data.Setting;
import zyx.existent.module.modules.movement.Flight;
import zyx.existent.module.modules.movement.LongJump;
import zyx.existent.utils.ChatUtils;
import zyx.existent.utils.MoveUtils;
import zyx.existent.utils.timer.Timer;

public class Criticals extends Module {
    public final double[] hypixelOffsets = new double[] { 0.05000000074505806D, 0.0015999999595806003D, 0.029999999329447746D, 0.0015999999595806003D };
    Timer lastStep = new Timer();
    Timer timer = new Timer();
    int groundTicks, stage, count;

    public static String MODE = "MODE";
    public static String HURTTIME = "HURTTIME";

    public Criticals(String name, String desc, int keybind, Category category) {
        super(name, desc, keybind, category);

        settings.put(MODE, new Setting(MODE, new Options("Mode", "Packet", new String[]{"Hypixel", "Packet", "Fake"}), "Critical method"));
        settings.put(HURTTIME, new Setting<>(HURTTIME, 15, "The hurtTime tick to crit at.", 1, 0, 20));
    }

    @Override
    public void onEnable() {
        stage = 0;
        count = 0;
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        String currentMode = ((Options) settings.get(MODE).getValue()).getSelected();
        double hurttime = ((Number) settings.get(HURTTIME).getValue()).doubleValue();
        setSuffix(currentMode + " " + hurttime);

        if (MoveUtils.isOnGround(0.001)) {
            groundTicks++;
        } else if (!mc.thePlayer.onGround) {
            groundTicks = 0;
        }
    }
    @EventTarget
    public void onPacket(EventPacket event) {
        Packet<?> packet = event.getPacket();

        if (packet instanceof SPacketPlayerPosLook) {
            stage = 0;
        }
        if (packet instanceof CPacketConfirmTransaction) {
            CPacketConfirmTransaction confirmTransaction = (CPacketConfirmTransaction) packet;
            boolean accepted = confirmTransaction.isAccepted();
            int uid = confirmTransaction.getUid();
            if (accepted && uid == 0) {
                count++;
            }
        }
    }
    @EventTarget
    public void onAttack(EventAttack attack) {
        String currentMode = ((Options) settings.get(MODE).getValue()).getSelected();
        if (attack.getEntity() instanceof EntityLivingBase) {
            Entity entity = attack.getEntity();

            double x = mc.thePlayer.posX;
            double y = mc.thePlayer.posY;
            double z = mc.thePlayer.posZ;

            if (entity.hurtResistantTime <= ((Number) settings.get(HURTTIME).getValue()).doubleValue()) {
                switch (currentMode) {
                    case "Packet":
                        if (mc.thePlayer.onGround) {
                            double off = 0.0625;
                            mc.getConnection().sendPacket(new CPacketPlayer.Position(x, y + off, z, false));
                            mc.getConnection().sendPacket(new CPacketPlayer.Position(x, y, z, false));
                            mc.thePlayer.onCriticalHit(entity);
                        }
                        break;
                    case "Hypixel":
                        if (mc.thePlayer.isCollidedVertically && mc.thePlayer.onGround && !(Existent.getModuleManager().isEnabled(LongJump.class) || Existent.getModuleManager().isEnabled(Flight.class)) && timer.delay(200L)) {
                            for (double offset : hypixelOffsets) {
                                mc.getConnection().sendPacket(new CPacketPlayer.Position(mc.thePlayer.posX, mc.thePlayer.posY + offset, mc.thePlayer.posZ, false));
                                mc.thePlayer.onCriticalHit(entity);
                                timer.reset();
                            }
                        }
                        break;
                    case "Fake":
                        mc.thePlayer.onCriticalHit(entity);
                        break;
                }
            }
        }
    }
    @EventTarget
    public void onStep(EventStep event) {
        if (!event.isPre()) {
            lastStep.reset();
            if (!mc.thePlayer.isCollidedHorizontally) {
                stage = 0;
            }
        }
    }

    private static boolean canSetPositionUp(final double offset) {
        return mc.theWorld.getEntitiesWithinAABBExcludingEntity(mc.thePlayer, mc.thePlayer.boundingBox.offset(0.0, offset, 0.0)).size() <= 0;
    }
}
