package zyx.existent.module.modules.movement;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import org.apache.commons.lang3.RandomStringUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.math.BlockPos;
import zyx.existent.event.EventTarget;
import zyx.existent.event.events.EventAttack;
import zyx.existent.event.events.EventMove;
import zyx.existent.event.events.EventPacket;
import zyx.existent.event.events.EventUpdate;
import zyx.existent.module.Category;
import zyx.existent.module.Module;
import zyx.existent.utils.ChatUtils;
import zyx.existent.utils.timer.Timer;

public class AntiVoid extends Module {
	public static int int0;
    public static int int1;
    public static int int2;
    public static int int3;
    public static int int4;
    public static int int5;
    public static int int6;
    public static int int7;
    public static int int8;
    public static int int9;
    public static int int10;
    public static int int11;
    public static int int12;
    public static int int13;
    public static int int14;
    public static int int15;
    public static int int16;
    public static int int17;
    public static int int18;
    public static int int19;
    public static float float0;
    public static float float1;
    public static float float2;
    public static float float3;
    public static float float4;
    public static float float5;
    public static float float6;
    public static float float7;
    public static float float8;
    public static float float9;
    public static float float10;
    public static float float11;
    public static float float12;
    public static float float13;
    public static float float14;
    public static float float15;
    public static float float16;
    public static float float17;
    public static float float18;
    public static float float19;
    public static double double0;
    public static double double1;
    public static double double2;
    public static double double3;
    public static double double4;
    public static double double5;
    public static double double6;
    public static double double7;
    public static double double8;
    public static double double9;
    public static double double10;
    public static double double11;
    public static double double12;
    public static double double13;
    public static double double14;
    public static double double15;
    public static double double16;
    public static double double17;
    public static double double18;
    public static double double19;
    public static boolean boolean0;
    public static boolean boolean1;
    public static boolean boolean2;
    public static boolean boolean3;
    public static boolean boolean4;
    public static boolean boolean5;
    public static boolean boolean6;
    public static BlockPos BlockPos0;
    public static BlockPos BlockPos1;
    public static BlockPos BlockPos2;
    public static BlockPos BlockPos3;
    public static BlockPos BlockPos4;
    public static BlockPos BlockPos5;
    public static Timer timer0 = new Timer();
    public static Timer timer1 = new Timer();
    public static Timer timer2 = new Timer();
    public static Timer timer3 = new Timer();
    public static Timer timer4 = new Timer();
    public static Timer timer5 = new Timer();
    public static Timer timer6 = new Timer();
    public static Timer timer7 = new Timer();
    public static Timer timer8 = new Timer();
    public static Timer timer9 = new Timer();
    public static Timer timer10 = new Timer();
    public static Timer timer11 = new Timer();
    public static Timer timer12 = new Timer();
    public static Timer timer13 = new Timer();
    public static Timer timer14 = new Timer();
    public static Timer timer15 = new Timer();
    public static Timer timer16 = new Timer();
    public static Timer timer17 = new Timer();
    public static Timer timer18 = new Timer();
    public static Timer timer19 = new Timer();
    private final ArrayList<String> admins = new ArrayList<String>();
    private final ArrayList<String> admins2 = new ArrayList<String>();
    private final ArrayList<String> admins3 = new ArrayList<String>();
    private final ArrayList<String> admins4 = new ArrayList<String>();
    private final ArrayList<String> admins5 = new ArrayList<String>();
    private final ArrayList<String> admins6 = new ArrayList<String>();
    private final ArrayList<String> admins7 = new ArrayList<String>();

    Queue<String> queue = new ArrayDeque<>();
    Queue<String> queue2 = new ArrayDeque<>();
    Queue<String> queue3 = new ArrayDeque<>();
    Queue<String> queue4 = new ArrayDeque<>();
    Queue<String> queue5 = new ArrayDeque<>();
    Queue<String> queue6 = new ArrayDeque<>();

    private final ArrayList<RankData> rankdata = new ArrayList<RankData>();
    public AntiVoid(String name, String desc, int keybind, Category category) {
        super(name, desc, keybind, category);
    }
    @Override
    public void onEnable() {
        if(mc.theWorld == null) {
            return;
        }
        timer1.reset();
        super.onEnable();
    }
    @Override
    public void onDisable() {
        mc.timer.timerSpeed = 1;
        super.onDisable();
    }

    @EventTarget
    public void onUpdate2(EventMove event) {

    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        if(timer1.delay(3000)&&queue.size() > 0) {
        	String username = queue.poll();
        	//ChatUtils.printChat("niggers:" + username);
        	ChatUtils.sendChat("/rank "+ username);
        	timer1.reset();
        }
    }
    RankData tmprank = new RankData();
    @EventTarget
    public void onPacket(EventPacket event) {
    	if (event.getPacket() instanceof SPacketTabComplete) {
            SPacketTabComplete packet = (SPacketTabComplete) event.getPacket();
            queue = new ArrayDeque<>();
            for (int length = (packet.getMatches()).length, i = 0; i < length; ++i) {
            	String username = packet.getMatches()[i];
            	queue.add(username);
            	//ChatUtils.printChat("niggers:" + username);
            	//ChatUtils.sendChat("/rank "+ username);;
            }
        }

    	if (event.getPacket() instanceof SPacketChat) {
    		SPacketChat packet = (SPacketChat) event.getPacket();
    		//String msg = packet.getChatComponent().getFormattedText();
    		String uncolormsg = packet.getChatComponent().getUnformattedText();
    		if(uncolormsg.contains("Rank Info for")) {
    			//|----------- Rank Info for: NIGGERS -----------| §a|----------- §r§eRank Info for: NIGGER§r§a -----------|§r
    		   //System.out.println(packet.getChatComponent().getFormattedText());
    			tmprank.user = uncolormsg.split("Rank Info for: ")[1].split(" ")[0];
    			event.setCancelled(true);
    			//ChatUtils.printChat(packet.getChatComponent().getFormattedText());
    			/*
    			String currentrank = msg.split("Current rank: ")[1].split("\n")[0];
    			String currentpoints = msg.split("Current points: ")[1].split("\n")[0];
    			String nextrank = msg.split("Next rank: ")[1].split("\n")[0];
    			String pointsrequired = msg.split("Points required: ")[1].split("\n")[0];
    			String nextrankunlocks = msg.split("Next rank unlocks: ")[1].split("\n")[0];
    			*/
    		//	ChatUtils.printChat(currentrank);
    		}
    		String msg = packet.getChatComponent().getFormattedText();
    		if(msg.contains("Current rank: ")) {
    			tmprank.currentrank = msg.split("Current rank: ")[1];
    			if(tmprank.currentrank.contains("Unranked")) {

    			}
    			ChatUtils.sendChat("!おい？ " + tmprank.user + " " + uncolormsg.split("Current rank: ")[1] +"が調子のんなよｗ嫉妬乙！ｗ" );
    			event.setCancelled(true);
    		}
    		if(msg.contains("Current points: ")) {
    			tmprank.currentpoints = msg.split("Current points: ")[1];
    			event.setCancelled(true);
    		}
    		if(msg.contains("Next rank: ")) {
    			tmprank.nextrank = msg.split("Next rank: ")[1];
    			event.setCancelled(true);
    		}
    		if(msg.contains("Points required: ")) {
    			tmprank.pointsrequired = msg.split("Points required: ")[1];
    			event.setCancelled(true);
    		}
    		if(msg.contains("Next rank unlocks: ")) {
    			tmprank.nextrankunlocks = msg.split("Next rank unlocks: ")[1];
    			event.setCancelled(true);
    			ChatUtils.printChat("Rank Info for: " + tmprank.user + " Current rank: " + tmprank.currentrank);
    			rankdata.add(tmprank);
    			tmprank = new RankData();
    		}
    	}
    	/*
        if (event.getPacket() instanceof SPacketEntityVelocity) {
            ChatUtils.printChat("\2479PacketDetect\2477: \247fVelocity");
            if (event.isCancelled()) {
                ChatUtils.printChat("\2479Canceled Packet");
            }
        } else if (event.getPacket() instanceof SPacketExplosion) {
            ChatUtils.printChat("\2479PacketDetect\2477: \247fExplosion");
            if (event.isCancelled()) {
                ChatUtils.printChat("\2479Canceled Packet");
            }
        }
        */
    	 if (event.getPacket() instanceof SPacketTitle) {
    		 SPacketTitle p1 = (SPacketTitle) event.getPacket();
    		//ChatUtils.printChat( p1.getMessage().getUnformattedText().toString());
    		//§6Shotbow
    		//§aThe original server network!
    		 if( p1.getMessage().getUnformattedText().toString().contains("§aThe original server network!")) {
    			String rndf = RandomStringUtils.randomAlphanumeric(10);

  				Minecraft.getMinecraft().thePlayer.sendChatMessage("/al");
  				Minecraft.getMinecraft().thePlayer.sendChatMessage("/register "+rndf+"@gmail.com");
    		 }
    	 }
    }
    @EventTarget
    public void onAttack(EventAttack event) {
    	/*
        if (mc.thePlayer.getCooledAttackStrength(0) >= 1 && !mc.thePlayer.onGround) {
            ChatUtils.printChat("\2479CriticalAttack\2477: " + event.getEntity().getName());
        } else {
            ChatUtils.printChat("\2479Attack\2477: " + event.getEntity().getName());
        }
        */
    }
    public static class RankData {
		public String currentrank;
		public String currentpoints;
		public String nextrank;
		public String pointsrequired;
		public String nextrankunlocks;
		public String user;
		public RankData() {
			this.currentrank = "";
			this.currentpoints = "";
			this.nextrank = "";
			this.pointsrequired = "";
			this.nextrankunlocks = "";
			this.user = "";
		}
	}
}
