package zyx.existent.module.modules.movement;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import zyx.existent.event.EventTarget;
import zyx.existent.event.events.EventEntityCollision;
import zyx.existent.event.events.EventPacket;
import zyx.existent.event.events.EventPushOutBlock;
import zyx.existent.event.events.EventUpdate;
import zyx.existent.module.Category;
import zyx.existent.module.Module;
import zyx.existent.module.data.Options;
import zyx.existent.module.data.Setting;
import zyx.existent.utils.MoveUtils;
import zyx.existent.utils.timer.Timer;

public class Phase extends Module {
   private final String MODE = "MODE";
   private final Timer timer = new Timer();
   private int delay;
   double multiplier;
   double mx;
   double mz;
   double x;
   double z;
   double rot1;
   double rot2;
   boolean shouldSpeed = false;
   float yaw;
   float pitch;

   public Phase(String name, String desc, int keybind, Category category) {
      super(name, desc, keybind, category);
      this.settings.put("MODE", new Setting("MODE", new Options("Phase Mode", "Hypixel", new String[]{"Hypixel", "Yumi", "NCP"}), "Phase exploit method."));
   }

   public void onDisable() {
      mc.timer.timerSpeed = 1.0F;
      super.onDisable();
   }

   @EventTarget
   public void onPacket(EventPacket event) {
      String currentPhase = ((Options)((Setting)this.settings.get("MODE")).getValue()).getSelected();
      byte var4 = -1;
      switch(currentPhase.hashCode()) {
      case -1248403467:
         if(currentPhase.equals("Hypixel")) {
            var4 = 0;
         }
         break;
      case 77115:
         if(currentPhase.equals("NCP")) {
            var4 = 2;
         }
         break;
      case 2767320:
         if(currentPhase.equals("Yumi")) {
            var4 = 1;
         }
      }

      switch(var4) {
      case 0:
         if(this.isInsideBlock()) {
            return;
         }

         this.multiplier = 0.2D;
         this.mx = Math.cos(Math.toRadians((double)(mc.thePlayer.rotationYaw + 90.0F)));
         this.mz = Math.sin(Math.toRadians((double)(mc.thePlayer.rotationYaw + 90.0F)));
         this.x = (double)MovementInput.moveForward * 0.2D * this.mx + (double)MovementInput.moveStrafe * 0.2D * this.mz;
         this.z = (double)MovementInput.moveForward * 0.2D * this.mz - (double)MovementInput.moveStrafe * 0.2D * this.mx;
         if(mc.thePlayer.isCollidedHorizontally && event.getPacket() instanceof CPacketPlayer) {
            ++this.delay;
            CPacketPlayer p1 = (CPacketPlayer)event.getPacket();
            if(this.delay >= 5) {
               p1.x += this.x;
               p1.z += this.z;
               --p1.y;
               this.delay = 0;
            }
         }
         break;
      case 1:
      case 2:
         Packet p = event.getPacket();
         if(p instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook multiplier = (SPacketPlayerPosLook)p;
            multiplier.yaw = mc.thePlayer.rotationYaw;
            multiplier.pitch = mc.thePlayer.rotationPitch;
            this.shouldSpeed = true;
            if(!this.shouldSpeed) {
               this.rot2 = 0.0D;
            }
         }

         if(event.isOutgoing()) {
            if(this.isInsideBlock()) {
               return;
            }

            double multiplier1 = 0.2D;
            double mx = Math.cos(Math.toRadians((double)(mc.thePlayer.rotationYaw + 90.0F)));
            double mz = Math.sin(Math.toRadians((double)(mc.thePlayer.rotationYaw + 90.0F)));
            double x = (double)MovementInput.moveForward * 0.2D * mx + (double)MovementInput.moveStrafe * 0.2D * mz;
            double z = (double)MovementInput.moveForward * 0.2D * mz - (double)MovementInput.moveStrafe * 0.2D * mx;
            if(mc.thePlayer.isCollidedHorizontally && p instanceof CPacketPlayer) {
               ++this.delay;
               CPacketPlayer player = (CPacketPlayer)p;
               if(this.delay >= 5) {
                  player.x += x;
                  player.z += z;
                  --player.y;
                  this.delay = 0;
               }
            }
         }
      }

   }

   @EventTarget
   public void onBB(EventEntityCollision event) {
      String currentPhase = ((Options)((Setting)this.settings.get("MODE")).getValue()).getSelected();
      byte var4 = -1;
      switch(currentPhase.hashCode()) {
      case -1248403467:
         if(currentPhase.equals("Hypixel")) {
            var4 = 0;
         }
         break;
      case 77115:
         if(currentPhase.equals("NCP")) {
            var4 = 2;
         }
         break;
      case 2767320:
         if(currentPhase.equals("Yumi")) {
            var4 = 1;
         }
      }

      switch(var4) {
      case 0:
         if(event.getBoundingBox() != null && event.getBoundingBox().maxY > mc.thePlayer.boundingBox.minY && mc.thePlayer.isSneaking()) {
            event.setBoundingBox((AxisAlignedBB)null);
         }

         if(mc.thePlayer == null) {
            return;
         }

         mc.thePlayer.noClip = true;
         if((double)event.getBlockPos().getY() > mc.thePlayer.posY + (double)(this.isInsideBlock()?0:1)) {
            event.setBoundingBox((AxisAlignedBB)null);
         }

         if(mc.thePlayer.isCollidedHorizontally && (double)event.getBlockPos().getY() > mc.thePlayer.boundingBox.minY - 0.4D) {
            event.setBoundingBox((AxisAlignedBB)null);
         }
         break;
      case 1:
      case 2:
         if(event.getBoundingBox() != null && event.getBoundingBox().maxY > mc.thePlayer.boundingBox.minY) {
            event.setCancelled(true);
         }
      }

   }

   @EventTarget
   public void onPush(EventPushOutBlock event) {
      if((((Options)((Setting)this.settings.get("MODE")).getValue()).getSelected().equalsIgnoreCase("NCP") || ((Options)((Setting)this.settings.get("MODE")).getValue()).getSelected().equalsIgnoreCase("Yumi"))) {
         event.setCancelled(true);
      }

   }

   @EventTarget
   public void onUpdate(EventUpdate event) {
      String currentPhase = ((Options)((Setting)this.settings.get("MODE")).getValue()).getSelected();
      byte var4 = -1;
      switch(currentPhase.hashCode()) {
      case -1248403467:
         if(currentPhase.equals("Hypixel")) {
            var4 = 0;
         }
         break;
      case 77115:
         if(currentPhase.equals("NCP")) {
            var4 = 2;
         }
         break;
      case 2767320:
         if(currentPhase.equals("Yumi")) {
            var4 = 1;
         }
      }

      double sin;
      double Y;
      switch(var4) {
      case 0:
         if(event.isPost()) {
            this.multiplier = 0.3D;
            this.mx = Math.cos(Math.toRadians((double)(mc.thePlayer.rotationYaw + 90.0F)));
            this.mz = Math.sin(Math.toRadians((double)(mc.thePlayer.rotationYaw + 90.0F)));
            this.x = (double)MovementInput.moveForward * this.multiplier * this.mx + (double)MovementInput.moveStrafe * this.multiplier * this.mz;
            this.z = (double)MovementInput.moveForward * this.multiplier * this.mz - (double)MovementInput.moveStrafe * this.multiplier * this.mx;
            if(mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isOnLadder() && !this.isInsideBlock()) {
               mc.thePlayer.connection.sendPacket(new Position(mc.thePlayer.posX + this.x, mc.thePlayer.posY, mc.thePlayer.posZ + this.z, false));
               Y = mc.thePlayer.posX;
               sin = mc.thePlayer.posY;
               mc.thePlayer.connection.sendPacket(new Position(Y, sin - (this.isOnLiquid()?9000.0D:0.09D), mc.thePlayer.posZ, false));
               mc.thePlayer.setPosition(mc.thePlayer.posX + this.x, mc.thePlayer.posY, mc.thePlayer.posZ + this.z);
            }
         }
         break;
      case 1:
      case 2:
         if(currentPhase.equalsIgnoreCase("Yumi") && mc.gameSettings.keyBindUseItem.isKeyDown()) {
            MoveUtils.setMotion(0.0D);
            this.teleport(0.0652D);
         }

         if(!this.shouldSpeed) {
            if(this.isInsideBlock()) {
               mc.thePlayer.rotationYaw = this.yaw;
               mc.thePlayer.rotationPitch = this.pitch;
            } else {
               this.yaw = mc.thePlayer.rotationYaw;
               this.pitch = mc.thePlayer.rotationPitch;
            }
         }

         if(event.isPre()) {
            if(this.shouldSpeed || this.isInsideBlock()) {
               if(!mc.thePlayer.isSneaking()) {
                  mc.thePlayer.lastReportedPosY = 0.0D;
               }

               mc.thePlayer.lastReportedPitch = 999.0F;
               mc.thePlayer.onGround = false;
               mc.thePlayer.noClip = true;
               mc.thePlayer.motionX = 0.0D;
               mc.thePlayer.motionZ = 0.0D;
               if(mc.gameSettings.keyBindJump.isPressed() && mc.thePlayer.posY == (double)((int)mc.thePlayer.posY)) {
                  mc.thePlayer.jump();
               }

               mc.thePlayer.jumpMovementFactor = 0.0F;
            }

            ++this.rot1;
            if(this.rot1 < 3.0D) {
               if(this.rot1 == 1.0D) {
                  this.pitch += 15.0F;
               } else {
                  this.pitch -= 15.0F;
               }
            }

            if(mc.gameSettings.keyBindSneak.isPressed()) {
               mc.thePlayer.lastReportedPitch = 999.0F;
               sin = mc.thePlayer.posX;
               Y = mc.thePlayer.posY;
               double Z = mc.thePlayer.posZ;
               if(!mc.thePlayer.isMoving()) {
                  if(MoveUtils.isOnGround(0.001D) && !this.isInsideBlock()) {
                     mc.thePlayer.lastReportedPosY = -99.0D;
                     event.setY(Y - 1.0D);
                     mc.thePlayer.setPosition(sin, Y - 1.0D, Z);
                     this.timer.reset();
                     mc.thePlayer.motionY = 0.0D;
                  } else if(this.timer.delay(100.0F) && mc.thePlayer.posY == (double)((int)mc.thePlayer.posY)) {
                     mc.thePlayer.setPosition(sin, Y - 0.3D, Z);
                  }
               }
            }

            if(this.isInsideBlock() && this.rot1 >= 3.0D) {
               if(this.shouldSpeed) {
                  this.teleport(0.617D);
                  float var11 = (float)Math.sin(this.rot2) * 0.1F;
                  float cos = (float)Math.cos(this.rot2) * 0.1F;
                  mc.thePlayer.rotationYaw += var11;
                  mc.thePlayer.rotationPitch += cos;
                  ++this.rot2;
               } else {
                  this.teleport(0.031D);
               }
            }
         }
      }

   }

   public boolean isInsideBlock() {
      for(int x = MathHelper.floor(mc.thePlayer.boundingBox.minX); x < MathHelper.floor(mc.thePlayer.boundingBox.maxX) + 1; ++x) {
         for(int y = MathHelper.floor(mc.thePlayer.boundingBox.minY); y < MathHelper.floor(mc.thePlayer.boundingBox.maxY) + 1; ++y) {
            for(int z = MathHelper.floor(mc.thePlayer.boundingBox.minZ); z < MathHelper.floor(mc.thePlayer.boundingBox.maxZ) + 1; ++z) {
               Block block = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
               if(block != null && !(block instanceof BlockAir)) {
                  AxisAlignedBB boundingBox = block.getCollisionBoundingBox(mc.theWorld.getBlockState(new BlockPos(x, y, z)), mc.theWorld, new BlockPos(x, y, z));
                  if(block instanceof BlockHopper) {
                     boundingBox = new AxisAlignedBB((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
                  }

                  if(boundingBox != null && mc.thePlayer.boundingBox.intersectsWith(boundingBox)) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean isOnLiquid() {
      if(mc.thePlayer == null) {
         return false;
      } else {
         boolean onLiquid = false;
         int y = (int)mc.thePlayer.boundingBox.offset(0.0D, -0.0D, 0.0D).minY;

         for(int x = MathHelper.floor(mc.thePlayer.boundingBox.minX); x < MathHelper.floor(mc.thePlayer.boundingBox.maxX) + 1; ++x) {
            for(int z = MathHelper.floor(mc.thePlayer.boundingBox.minZ); z < MathHelper.floor(mc.thePlayer.boundingBox.maxZ) + 1; ++z) {
               Block block = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
               if(block != null && !(block instanceof BlockAir)) {
                  if(!(block instanceof BlockLiquid)) {
                     return false;
                  }

                  onLiquid = true;
               }
            }
         }

         return onLiquid;
      }
   }

   private void teleport(double dist) {
      double forward = (double)MovementInput.moveForward;
      double strafe = (double)MovementInput.moveStrafe;
      float yaw = mc.thePlayer.rotationYaw;
      if(forward != 0.0D) {
         if(strafe > 0.0D) {
            yaw += (float)(forward > 0.0D?-45:45);
         } else if(strafe < 0.0D) {
            yaw += (float)(forward > 0.0D?45:-45);
         }

         strafe = 0.0D;
         if(forward > 0.0D) {
            forward = 1.0D;
         } else if(forward < 0.0D) {
            forward = -1.0D;
         }
      }

      double x = mc.thePlayer.posX;
      double y = mc.thePlayer.posY;
      double z = mc.thePlayer.posZ;
      double xspeed = forward * dist * Math.cos(Math.toRadians((double)(yaw + 90.0F))) + strafe * dist * Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      double zspeed = forward * dist * Math.sin(Math.toRadians((double)(yaw + 90.0F))) - strafe * dist * Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      mc.thePlayer.setPosition(x + xspeed, y, z + zspeed);
   }
}
