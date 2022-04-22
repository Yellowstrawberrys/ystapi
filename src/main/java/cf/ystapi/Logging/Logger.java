package cf.ystapi.Logging;

import cf.ystapi.Logging.Processing.printer;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>Logger</p>
 * - YST LOGGER
 * **/
public class Logger {
    public String format = "%MM%/%DD%/%YYYY% %HH%:%mm% | [%LL%] %MSG%";
    public String name = "";
    List<List<String>> debug = new ArrayList<>();
    List<List<String>> info = new ArrayList<>();
    List<List<String>> warn = new ArrayList<>();
    List<List<String>> error = new ArrayList<>();
    public List<List<String>> WholeLog = new ArrayList<>();

    public Logger(){
        System.setOut(new PrintStream(System.out) {
            public void println(String s) {
                WholeLog.add(Arrays.asList(formatter(s, format, "STD"), "INFO"));
                super.println(formatter(s, format, "STD"));
            }
            public void println(Object s) {
                WholeLog.add(Arrays.asList(formatter(s.toString(), format, "STD"), "INFO"));
                super.println(formatter(s.toString(), format, "STD"));
            }
            public void println(int s) {
                WholeLog.add(Arrays.asList(formatter(String.valueOf(s), format, "STD"), "INFO"));
                super.println(formatter(String.valueOf(s), format, "STD"));
            }
            public void println(double s) {
                WholeLog.add(Arrays.asList(formatter(String.valueOf(s), format, "STD"), "INFO"));
                super.println(formatter(String.valueOf(s), format, "STD"));
            }
            public void println(char s) {
                WholeLog.add(Arrays.asList(formatter(String.valueOf(s), format, "STD"), "INFO"));
                super.println(formatter(String.valueOf(s), format, "STD"));
            }
            public void println(char[] s) {
                WholeLog.add(Arrays.asList(formatter(String.valueOf(s), format, "STD"), "INFO"));
                super.println(formatter(String.valueOf(s), format, "STD"));
            }
            public void println(float s) {
                WholeLog.add(Arrays.asList(formatter(String.valueOf(s), format, "STD"), "INFO"));
                super.println(formatter(String.valueOf(s), format, "STD"));
            }
            public void println(boolean s) {
                WholeLog.add(Arrays.asList(formatter(String.valueOf(s), format, "STD"), "INFO"));
                super.println(formatter(String.valueOf(s), format, "STD"));
            }
            public void println(long s) {
                WholeLog.add(Arrays.asList(formatter(String.valueOf(s), format, "STD"), "INFO"));
                super.println(formatter(String.valueOf(s), format, "STD"));
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Data.web.server.stop(0);
        }));
    }

    /**
     * Send debug log
     * **/
    public void debug(String message){
        for(String line : message.lines().toList()) {
            line = formatter(line, format, "DEBUG");
            debug.add(Arrays.asList(line, "DEBUG"));
            WholeLog.add(Arrays.asList(line, "DEBUG"));
            new printer().Update(line, 0);
        }
    }

    /**
     * Send info log
     * **/
    public void info(String message){
        for(String line : message.lines().toList()) {
            line = formatter(line, format, "INFO");
            info.add(Arrays.asList(line, "INFO"));
            WholeLog.add(Arrays.asList(line, "INFO"));
            new printer().Update(line, 1);
        }
    }

    /**
     * Send warn log
     * **/
    public void warn(String message){
        for(String line : message.lines().toList()) {
            line = formatter(line, format, "WARN");
            warn.add(Arrays.asList(line, "WARN"));
            WholeLog.add(Arrays.asList(line, "WARN"));
            new printer().Update(line, 2);
        }
    }

    /**
     * Send error log
     * **/
    public void error(String message){
        for(String line : message.lines().toList()) {
            line = formatter(line, format, "ERROR");
            error.add(Arrays.asList(line, "ERROR"));
            WholeLog.add(Arrays.asList(line, "ERROR"));
            new printer().Update(line, 3);
        }
    }

    /**
     * Send error log
     * **/
    public void err(String message){
        error(message);
    }



    /**
     * Send log
     * **/
    public void log(String message, int level){
        for(String line : message.lines().toList()) {
            String levelName = "";
            switch (level) {
                case 0:
                    levelName = "DEBUG";
                    debug.add(Arrays.asList(line, levelName));
                    break;
                case 1:
                    levelName = "INFO";
                    info.add(Arrays.asList(line, levelName));
                    break;
                case 2:
                    levelName = "WARN";
                    warn.add(Arrays.asList(line, levelName));
                    break;
                case 3:
                    levelName = "ERROR";
                    error.add(Arrays.asList(line, levelName));
                    break;
            }
            line = formatter(line, format, levelName);
            WholeLog.add(Arrays.asList(line, levelName));
            new printer().Update(line, level);
        }
    }

    /**
     * Get logger by name of logger.
     *
     * @return Logger
     * **/
    public static Logger getLoggerByName(String name){
        return Data.loggers.get(name);
    }

    private String formatter(String message, String format, String level){
        String[] dates = new SimpleDateFormat("yyyy-yy-MM-dd-HH-mm-ss").format(new Date()).split("-");
        return format.replaceAll("%MSG%", message).replaceAll("%LL%", level).replaceAll("%CC%", format.contains("%CC%") ? new Exception().getStackTrace()[2].getClassName() : "")
                .replaceAll("%YYYY%", dates[0]).replaceAll("%YY%", dates[1]).replaceAll("%MM%", dates[2])
                .replaceAll("%DD%", dates[3]).replaceAll("%HH%", dates[4]).replaceAll("%mm%", dates[5])
                .replaceAll("%SS%", dates[6]);
    }
}