package cf.ystapi.explains.util;

import cf.ystapi.util.arg;

public class arg_ex {
    public static void main(String[] args) {
        String[] ar = arg.spconvert("Hi! Hello");

        //Output = Hi!
        System.out.println(ar[0]);

        //Output = Hello
        System.out.println(ar[1]);
    }
}
