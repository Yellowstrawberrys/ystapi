package cf.ystapi.explains.Logging;

import cf.ystapi.Logging.*;

import java.io.IOException;
import java.util.Random;

public class Main {
    static Logger logger;
    public static void main(String[] args) throws IOException {
        LoggingBuilder loggingBuilder = new LoggingBuilder();
        loggingBuilder.setFormat("[%YY/%MM/%DD %HH:%mm:%SS | %LL] %MSG");
        loggingBuilder.useWebLogger(true);
        logger = loggingBuilder.build("wow");
        logger.error("wow");
        logger.warn("woow");
        logger.info("wooow");
        logger.debug("woooow");
        System.out.println(1);
        System.out.println();
        new Thread(() -> {
            while (true){
                logger.log(String.valueOf(new Random().nextInt(2147892)), new Random().nextInt(4));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
