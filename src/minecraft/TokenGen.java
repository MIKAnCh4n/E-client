public class TokenGen {

    public static void main(String[] args) {
        System.out.println(stringToNumber(""));

//        try {
//            TheAltening altening = new TheAltening("api-a7dx-wpwo-ub5f");
//
//            try {
//                GuiAltManager.altService.switchService(AltService.EnumAltService.THEALTENING);
//
//                System.out.println(altening.getAccountData().getToken());
//                System.out.println(altening.getAccountData().getUsername());
//                System.out.println(altening.getAccountData().getInfo().getHypixelLevel());
//                System.out.println(altening.getAccountData().getInfo().getHypixelRank());
//                System.out.println(altening.getAccountData().getInfo().hasFiveZigCape());
//                System.out.println(altening.getAccountData().getInfo().hasLabyModCape());
//            } catch (Throwable throwable) {
//                System.out.println("Error2 :(");
//            }
//        } catch (Exception e) {
//            System.out.println("Error1 :(");
//        }
    }

    public static long stringToNumber(String s) {
        long result = 0;

        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            result += (int) ch;
        }

        return result;
    }
}
