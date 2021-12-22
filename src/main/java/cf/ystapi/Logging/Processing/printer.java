package cf.ystapi.Logging.Processing;

import java.io.PrintStream;

public class printer {
    boolean isLightMode = false;
    boolean isGUI = false;
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    public void Update(String message, int level){
        if(isGUI){

        }else{
            String color = "";
            if(level == 0)
                color = TEXT_WHITE;
            else if(level == 1)
                color = TEXT_RESET;
            else if(level == 2)
                color = TEXT_YELLOW;
            else if(level == 3)
                color = TEXT_RED;
            try{
                System.out.write((color+message+TEXT_RESET+"\n").getBytes("UTF-8"));
            }catch (Exception e){}
        }
    }
}
