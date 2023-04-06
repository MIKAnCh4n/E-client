package zyx.existent.module.modules.movement;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import zyx.existent.event.EventTarget;
import zyx.existent.event.events.EventEntityCollision;
import zyx.existent.event.events.EventJump;
import zyx.existent.event.events.EventMove;
import zyx.existent.event.events.EventStep;
import zyx.existent.event.events.EventUpdate;
import zyx.existent.module.Category;
import zyx.existent.module.Module;
import zyx.existent.module.data.Options;
import zyx.existent.module.data.Setting;
import zyx.existent.utils.BlockUtils;
import zyx.existent.utils.ChatUtils;
import zyx.existent.utils.MoveUtils;
import zyx.existent.utils.timer.Timer;

public class Step extends Module {
    private final double[][] offsets = new double[][]{{0.42D, 0.753D}, {0.42D, 0.75D, 1.0D, 1.16D, 1.23D, 1.2D}, {0.42D, 0.78D, 0.63D, 0.51D, 0.9D, 1.21D, 1.45D, 1.43D}};
    public static Timer lastStep = new Timer();
    public static Timer time = new Timer();
    private String MODE = "MODE";
    private String HEIGHT = "HEIGHT";
    private String DELAY = "DELAY";
    private String REVERSE = "REVERSE";
    boolean resetTimer;
    private boolean jumped;
    private Timer timer2 = new Timer();
    private Timer timer3 = new Timer();
    private Timer timer4 = new Timer();
    private Timer timer5 = new Timer();
    private Timer timer6 = new Timer();
    public int state;
	public int state2;
	public int state3;
	public int state4;
	public int state5;
	public int state6;
	public int state7;
    public Step(String name, String desc, int keybind, Category category) {
        super(name, desc, keybind, category);

        settings.put(MODE, new Setting<>(MODE, new Options("Mode", "NCP", new String[]{"Normal", "Motion", "NCP", "Cube","RealMotion","Bromomento"}), "Step method"));
        settings.put(HEIGHT, new Setting<>(HEIGHT, 1.5, "StepHeight.", 0.1, 1, 4.0));
        settings.put(DELAY, new Setting<>(DELAY, 0.15, "Delay.", 0.01, 0, 5.00));
        settings.put(REVERSE, new Setting<>(REVERSE, false, "ReverseStep."));
    }

    @Override
    public void onEnable() {
        resetTimer = false;
        super.onEnable();
    }
    @Override
    public void onDisable() {
        mc.thePlayer.stepHeight = 0.625f;
        super.onDisable();
    }
    @EventTarget
    public void onUpdate3(EventEntityCollision event) {
       if(((Options) settings.get(MODE).getValue()).getSelected() == "RealMotion") {
    	if (event.getBlockPos().getY() > mc.thePlayer.posY) {
    		event.setCancelled(true);
    	}
       }
    }
    @EventTarget
    public void onUpdate2(EventMove event) {
    	if(this.isEnabled()) {
    		if(((Options) settings.get(MODE).getValue()).getSelected() == "Bromomento") {
    			for (float nigeria = 0.01f; nigeria < 0.5;nigeria += 0.01f) {
    			float increment2aer = nigeria;
    			double X =  Math.sin(Math.toRadians(-mc.thePlayer.rotationYaw)) * increment2aer;
    	        double Y = mc.thePlayer.posY;
    	        double Z = Math.cos(Math.toRadians(-mc.thePlayer.rotationYaw)) * increment2aer;
    	         if(!mc.theWorld.getCollisionBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0D+X, 0.0d, 0.0D+Z)).isEmpty()) {
    	        	//ChatUtils.printChat("niggers");
    				if(mc.thePlayer.onGround) {
        	    		//	event.setY(mc.thePlayer.motionY = 0.39f);
        	    			event.setY(mc.thePlayer.motionY = 0.385f);
        	    			float f = mc.thePlayer.rotationYaw * 0.017453292F;
        	    			mc.thePlayer.motionX -= (double) (MathHelper.sin(f) * 0.3F);
        	    			mc.thePlayer.motionZ += (double) (MathHelper.cos(f) * 0.3F);
        	    			ChatUtils.printChat(""+nigeria);
        	    			
        	    			break;
    				}
    				//MoveUtils.setMotion(event,0.25);
    			 }
    			}
    		}
    		if(((Options) settings.get(MODE).getValue()).getSelected() == "RealMotion") {
    			float increment2aer = 0.01F;
    			double X =  Math.sin(Math.toRadians(-mc.thePlayer.rotationYaw)) * increment2aer;
    	        double Y = mc.thePlayer.posY;
    	        double Z = Math.cos(Math.toRadians(-mc.thePlayer.rotationYaw)) * increment2aer;
    	    	if(!mc.theWorld.getCollisionBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0D, 0.0d, 0.0D)).isEmpty()) {
    	    		
    	    		if(mc.thePlayer.onGround) {
    	    		//	event.setY(mc.thePlayer.motionY = 0.39f);
    	    			event.setY(mc.thePlayer.motionY = 0.385f);
    	    			
    	    			timer3.reset();
    	    		}else {
    	    			//event.setY(0);
    	    		}
    	    		MoveUtils.setMotion(event,-0.0);
    	    	}else {
    	    		if(mc.theWorld.getCollisionBoxes(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0D+X, 0.0d, 0.0D+Z)).isEmpty()) {
    	                double forward = MovementInput.moveForward;
    	                double strafe = MovementInput.moveStrafe;
    	                float yaw = mc.thePlayer.rotationYaw;
    	                if (forward != 0.0D) {
    	                    if (strafe > 0.0D) {
    	                        yaw += (forward > 0.0D ? -45 : 45);
    	                    } else if (strafe < 0.0D) {
    	                        yaw += (forward > 0.0D ? 45 : -45);
    	                    }
    	                    strafe = 0.0D;
    	                    if (forward > 0.0D) {
    	                        forward = 1;
    	                    } else if (forward < 0.0D) {
    	                        forward = -1;
    	                    }
    	                }
    	                double x = mc.thePlayer.posX;
    	                double y = mc.thePlayer.posY;
    	                double z = mc.thePlayer.posZ;
    	                double xspeed = forward * 0.0000001 * Math.cos(Math.toRadians(yaw + 90.0F)) + strafe * 0.0000001 * Math.sin(Math.toRadians(yaw + 90.0F));
    	                double zspeed = forward * 0.0000001 * Math.sin(Math.toRadians(yaw + 90.0F)) - strafe * 0.0000001 * Math.cos(Math.toRadians(yaw + 90.0F));
    	                mc.thePlayer.setPosition(x + xspeed, y, z + zspeed);
    	              //  MoveUtils.setMotion(event,-0.0);
    	            }
    	    	}
    	    	
    		}
    	}
    }
    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        if (((Boolean) settings.get(REVERSE).getValue())) {
            if (mc.thePlayer.onGround)
                jumped = false;
            if (mc.thePlayer.motionY > 0)
                jumped = true;
            if (BlockUtils.collideBlock(mc.thePlayer.getEntityBoundingBox(), block -> block instanceof BlockLiquid) || BlockUtils.collideBlock(new AxisAlignedBB(mc.thePlayer.getEntityBoundingBox().maxX, mc.thePlayer.getEntityBoundingBox().maxY, mc.thePlayer.getEntityBoundingBox().maxZ, mc.thePlayer.getEntityBoundingBox().minX, mc.thePlayer.getEntityBoundingBox().minY - 0.01D, mc.thePlayer.getEntityBoundingBox().minZ), block -> block instanceof BlockLiquid))
                return;
            if (!mc.gameSettings.keyBindJump.isKeyDown() && !mc.thePlayer.onGround && !MovementInput.jump && mc.thePlayer.motionY <= 0D && mc.thePlayer.fallDistance <= 1F && !jumped)
                mc.thePlayer.motionY = -1.0F;
        }
    }

    @EventTarget
    public void onJump(EventJump ej) {
        String currentmode = ((Options) settings.get(MODE).getValue()).getSelected();
        if (ej.isPre()) {
            if (!lastStep.delay(60) && currentmode.equalsIgnoreCase("Cube")) {
                ej.setCancelled(true);
            }
            jumped = true;
        }
    }

    @EventTarget
    public void onStep(EventStep step) {
        String currentmode = ((Options) settings.get(MODE).getValue()).getSelected();
        double stepValue = ((Number) settings.get(HEIGHT).getValue()).doubleValue();
        final float delay = ((Number) settings.get(DELAY).getValue()).floatValue() * 1000;
        final float timer = 0.37F;
        if(((Options) settings.get(MODE).getValue()).getSelected() == "RealMotion"||((Options) settings.get(MODE).getValue()).getSelected() == "Bromomento") {
            return;
        }
        if (resetTimer) {
            resetTimer = !resetTimer;
            mc.timer.timerSpeed = 1;
        }
        if (!mc.thePlayer.isInLiquid()) {
            if (step.isPre()) {
                if (mc.thePlayer.isCollidedVertically && !mc.gameSettings.keyBindJump.isPressed() && time.delay(delay)) {
                    step.setStepHeight(stepValue);
                    step.setActive(true);
                }
            } else {
                double rheight = mc.thePlayer.getEntityBoundingBox().minY - mc.thePlayer.posY;
                boolean canStep = rheight >= 0.625;
                if (canStep) {
                    lastStep.reset();
                    time.reset();
                }
                switch (currentmode) {
                    case "Motion":
                        if (canStep) {
                            mc.timer.timerSpeed = timer - (rheight >= 1 ? Math.abs(1 - (float) rheight) * (timer * 0.7f) : 0);
                            if (mc.timer.timerSpeed <= 0.05f) {
                                mc.timer.timerSpeed = 0.05f;
                            }
                            resetTimer = true;
                            ncpStep(rheight);
                        }
                        break;
                    case "NCP":
                        if (canStep) {
                            mc.thePlayer.setSprinting(false);
                            resetTimer = true;
                            ncpStep(rheight);
                        }
                        break;
                    case "Cube":
                        if (canStep) {
                            cubeStep(rheight);
                            resetTimer = true;
                            mc.timer.timerSpeed = rheight < 2 ? 0.6f : 0.3f;
                        }
                        break;
                }
            }
        }
    }

    void cubeStep(double height) {
        double posX = mc.thePlayer.posX;
        double posZ = mc.thePlayer.posZ;
        double y = mc.thePlayer.posY;
        double first = 0.42;
        double second = 0.75;
        mc.thePlayer.connection.sendPacket(new CPacketPlayer.Position(posX, y + first, posZ, false));
    }
    void ncpStep(double height) {
        List<Double> offset = Arrays.asList(0.42, 0.333, 0.248, 0.083, -0.078);
        double posX = mc.thePlayer.posX;
        double posZ = mc.thePlayer.posZ;
        double y = mc.thePlayer.posY;
        if (height < 1.1) {
            double first = 0.42;
            double second = 0.75;
            if (height != 1) {
                first *= height;
                second *= height;
                if (first > 0.425) {
                    first = 0.425;
                }
                if (second > 0.78) {
                    second = 0.78;
                }
                if (second < 0.49) {
                    second = 0.49;
                }
            }
            if (first == 0.42)
                first = 0.41999998688698;
            mc.thePlayer.connection.sendPacket(new CPacketPlayer.Position(posX, y + first, posZ, false));
            if (y + second < y + height)
                mc.thePlayer.connection.sendPacket(new CPacketPlayer.Position(posX, y + second, posZ, false));
            return;
        } else if (height < 1.6) {
            for (int i = 0; i < offset.size(); i++) {
                double off = offset.get(i);
                y += off;
                mc.thePlayer.connection.sendPacket(new CPacketPlayer.Position(posX, y, posZ, false));
            }
        } else if (height < 2.1) {
            double[] heights = {0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869};
            for (double off : heights) {
                mc.thePlayer.connection.sendPacket(new CPacketPlayer.Position(posX, y + off, posZ, false));
            }
        } else {
            double[] heights = {0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907};
            for (double off : heights) {
                mc.thePlayer.connection.sendPacket(new CPacketPlayer.Position(posX, y + off, posZ, false));
            }
        }
    }
}
