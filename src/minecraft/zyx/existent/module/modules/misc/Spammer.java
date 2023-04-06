package zyx.existent.module.modules.misc;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.RandomStringUtils;

import zyx.existent.event.EventTarget;
import zyx.existent.event.events.EventUpdate;
import zyx.existent.module.Category;
import zyx.existent.module.Module;
import zyx.existent.module.data.Options;
import zyx.existent.module.data.Setting;
import zyx.existent.utils.timer.Timer;

public class Spammer extends Module {
    public static String MESSAGE = "MESSAGE";
    private String MODE = "MODE";
    private String OTHER = "OTHER";
    private String DELAY = "DELAY";
    private String ANNI = "ANNI";

    private final Timer timer = new Timer();
    public int var1;

    public Spammer(String name, String desc, int keybind, Category category) {
        super(name, desc, keybind, category);

        settings.put(MODE, new Setting<>(MODE, new Options("Spam Mode", "Custom", new String[]{"Custom", "Other"}), "Spam method"));
        settings.put(OTHER, new Setting<>(OTHER, new Options("Other Mode", "Random", new String[]{"Client", "Random", "Obfsucator", "Nasty1", "Noriaki1", "832uBan"}), "Spam method"));
        settings.put(MESSAGE, new Setting<>(MESSAGE, "Test", "SpammerText"));
        settings.put(DELAY, new Setting<>(DELAY, 2.4, "SpamDelay.", 0.1, 0.1, 20.0));
        settings.put(ANNI, new Setting<>(ANNI, false, ""));
    }

    @Override
    public void onEnable() {
        var1 = 0;
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        String currentmode = ((Options) settings.get(MODE).getValue()).getSelected();
        String othermode = ((Options) settings.get(OTHER).getValue()).getSelected();
        double delay = ((Number) settings.get(DELAY).getValue()).doubleValue();
        String[] msg = null;

        switch (currentmode) {
            case "Custom":
                msg = new String[]{settings.get(MESSAGE).getValue() + " <" + RandomStringUtils.randomNumeric(5) + ">"};
                break;
            case "Other":
                switch (othermode) {
                    case "Client":
                        msg = new String[]{
                                "アトピーは毎日かゆみと戦っています",
                                "今すぐ貧しいアトピーに寄付: https://sellix.io/Existent"
                        };
                        break;
                    case "Random":
                        String str1 = RandomStringUtils.randomAlphabetic(20);
                        msg = new String[]{str1};
                        break;
                    case "Obfsucator":
                        try {
                            String[] c = {"あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ", "さ", "し", "す", "せ", "そ", "ま", "み", "む", "め", "も", "な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ", "わ", "を", "ん", "ら", "り", "る", "れ", "ろ"};
                            int cl = c.length;
                            String k = c[(int) Math.floor(Math.random() * cl)] + c[(int) Math.floor(Math.random() * cl)] + c[(int) Math.floor(Math.random() * cl)] + c[(int) Math.floor(Math.random() * cl)] + c[(int) Math.floor(Math.random() * cl)] + c[(int) Math.floor(Math.random() * cl)] + c[(int) Math.floor(Math.random() * cl)] + c[(int) Math.floor(Math.random() * cl)];
                            k = new String(k.getBytes("UTF-8"), "Shift-JIS");
                            msg = new String[]{k};
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Nasty1":
                        msg = new String[]{
                        		"お願い神様♡ムニャムニャムニャ", "あの人の夢が台無しになりますように", "お願い♡そしたら切ないムネに風が吹いて・・・", "ユメがユメで無くなる前に ムニャムニャムニャ・・・", "ところで", "ナンシーシンナー吸った", "ドロドロの頭の中", "ナンシー空を飛んだ", "両手広げて・・・", "ナンシーシンナー吸った", "本当は死ぬ気じゃなかった", "どぉ神様、世界中の人たちがアカペラでF-1のテーマ曲を唄うの", "ねぇ*そしたら街中がサーキットで、電柱はもうないの", "そういえば", "あの人は言った", "世界一のバカヤローナンシー", "小さな声で言った", "ずっと前から好きだった", "ナンシーシンナー吸った", "世界中が傾き始めて", "ナンシー空を飛んだ" ,"本当は死ぬ気じゃなかった", "アタシもシンナー吸った", "Let's go新世界見えた"};
                        break;
                    case "Noriaki1":
                        msg = new String[]{
                                "Yo! オレがSo! ノリアキ マジ手のつけられない悪ガキ",
                                "クラスのアイドルあの娘もオレのこのflowにメロメロ",
                                "マイク一本で今日上京 これでも最強のRapper志望",
                                "もう戻れないぜGo Go オレの叫びを聴かせよう",
                                "Yo! ニコタマへようこそ 今日のBBQ超ウマそう",
                                "オレがMCノリアキ お前等マジでオレに会えてlucky",
                                "YAMAGATAから来たbaby face エミネム・ジブラも全部FAKE",
                                "もう戻れないぜGo Go オレの叫びを聴かせよう",
                                "Say! you've gotta save me and my heart",
                                "オレのマシンが夜の地を這う",
                                "墾田永年私財法 まさに釈迦に説法 金の延べ棒",
                                "オレの採点は減点法 魔の言霊でアントンも死亡",
                                "もう届かないぜGo Go オレの走りを見てろよ",
                                "Hey! そこのHip Hop Guy! オレのアッスにキッスするかい？",
                                "グラビアアイドルあの娘もオレのこの刺青にレロレロ",
                                "フック船長の三重奏 もうギリギリのカウパーがこぼれそう",
                                "もう止まらないぜGo Go おれのマグナム噴かせよう",
                                "Yeah, Come on, Let's Go!",
                                "ガリガリノリアキガリガリノリアキ",
                                "You are fucker and さくらんぼfucker",
                                "Yeah, Come on, Let's Go!",
                                "ガリガリノリアキガリガリノリアキ",
                                "You are fucker and さくらんぼfucker",
                                "Yeah, Come on, Let's Go!",
                                "ガリガリノリアキガリガリノリアキ",
                                "You are fucker and さくらんぼfucker",
                                "Hey, 一生チェリーボーイ",
                                "めがねめがねめがねめがね",
                                "Yo! Fuck me Yo!",
                                "Yeah, Come on, Let's Go!",
                                "ガリガリノリアキガリガリノリアキ",
                                "You are fucker and さくらんぼfucker",
                                "Yeah, Come on, Let's Go!",
                                "ガリガリノリアキガリガリノリアキ",
                                "You are fucker and さくらんぼfucker",
                                "Hey, 一生チェリーボーイ",
                                "めがねめがねめがねめがね",
                                "Yo! Fuck me Yo!",
                        };
                        break;
                    case "832uBan":
                        msg = new String[]{
                        		"怎麼能哭呢 一切會好噠",
                        		"一切都去吧 你就得想著",
                        		"既然沒辦法 還恨他幹嘛",
                        		"還管它幹嘛 心裡要記得",
                        		"你是內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內內",
                        		"陽光彩虹小白馬 滴滴噠滴滴噠",
                        		"我是內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內內",
                        		"陽光彩虹小白馬 滴滴噠滴滴噠",
                        		"滴滴噠滴噠 滴滴噠滴噠 滴滴噠滴滴噠",
                        		"滴滴噠滴噠 滴滴噠滴噠 滴滴噠滴滴噠 嘿！",
                        		"你是 內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內個內",
                        		"內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內個內",
                        		"內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內個內",
                        		"內內個 內內 內個內個 內內",
                        		"陽光彩虹小白馬",
                        		"你是最強噠 最棒噠 最亮噠 最發光噠",
                        		"攔不住你發芽",
                        		"你是最好噠 最俏噠 最妙噠 最驕傲噠",
                        		"盡情的盛開吧",
                        		"你就是 最強噠 最棒噠 最亮噠 最發光噠",
                        		"心需要你哄它",
                        		"你是最好噠 最俏噠 最妙噠 最驕傲噠",
                        		"陽光彩虹小白馬",
                        		"生活是笑話 別哭著聽它",
                        		"別在意梗垮 不樂是你傻",
                        		"心要你哄它 一切會好噠",
                        		"一切都來吧 天地隨你耍",
                        		"你是內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內內",
                        		"陽光彩虹小白馬 滴滴噠滴滴噠",
                        		"我是內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內內",
                        		"陽光彩虹小白馬 滴滴噠滴滴噠",
                        		"滴滴噠滴噠 滴滴噠滴噠 滴滴噠滴滴噠",
                        		"滴滴噠滴噠 滴滴噠滴噠 滴滴噠滴滴噠 嘿！",
                        		"你是 內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內個內",
                        		"內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內個內",
                        		"內內個 內內 內個內個 內內",
                        		"內內個 內內 內個內個 內個內",
                        		"內內個 內內 內個內個 內內",
                        		"陽光彩虹小白馬",
                        		"你是最強噠 最棒噠 最亮噠 最發光噠",
                        		"攔不住你發芽",
                        		"你是最好噠 最俏噠 最妙噠 最驕傲噠",
                        		"盡情的盛開吧",
                        		"你就是 最強噠 最棒噠 最亮噠 最發光噠",
                        		"心需要你哄它",
                        		"你是最好噠 最俏噠 最妙噠 最驕傲噠",
                        		"陽光彩虹小白馬",
//                                "はちみっつーよ",
//                                "きょうはなTaihu☆様からな言葉だ",
//                                "わかるかはちみっつー",
//                                "お前はなbanされたんだよ",
//                                "お前は裏でgcばっかつかってranked使ってるからな",
//                                "banされたんだよ",
//                                "分かるかくそ",
//                                "なにちょうしのってダイヤモンドとかイってんだよ",
//                                "お前はただgcの端っこにしか過ぎねえんだよ",
//                                "しね二度と帰ってくんな死ねsine☆",
                        };
                        break;
                }
                break;
        }

        if (this.timer.delay((float) (delay * 1050.0D))) {
            if (this.var1 >= msg.length) {
                this.var1 = 0;
            }
            mc.thePlayer.sendChatMessage((Boolean) settings.get(ANNI).getValue() ? "!" + msg[this.var1] : msg[this.var1]);
            this.var1++;
            this.timer.reset();
        }
    }
}
