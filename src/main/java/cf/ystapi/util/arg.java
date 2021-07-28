package cf.ystapi.util;

public class arg {
    public static String[] spconvert(String toconvert){
        /*
         * Space Convert
         *
         * Explain:
         *  String s = "hi my name is xxx"
         *  String[] st = arg.spconvert(s);
         *  st[0] will be "hi"
         * */
        return toconvert.split(" ");
    }
    public static String[] convert(String toconvert){
        /*
         * Convert
         *
         * Explain:
         *  String s = "hihiihhihihihihihihi"
         *  String[] st = arg.convert(s);
         *  st[0] will be "h"
         * */
        return toconvert.split("");
    }
}
