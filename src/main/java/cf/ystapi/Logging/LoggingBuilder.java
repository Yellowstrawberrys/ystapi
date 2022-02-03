package cf.ystapi.Logging;

import cf.ystapi.Logging.Web.Core;

import java.io.IOException;

public class LoggingBuilder {

    String format = "%MM/%DD/%YYYY %HH:%mm | [%LL] %MSG";
    boolean isUsingWebLogger = false;
    int webLoggerPort = 8080;
    String webLoggerPath = "/log";

    public Logger build(String name) throws IOException {
        Logger logger = new Logger();
        logger.format = this.format;
        logger.name = name;
        Data.loggers.add(logger);

        if(isUsingWebLogger && Data.web == null) {
            Data.web = new Core();
            Data.web.start(8080, webLoggerPath);
        }

        return logger;
    }

    /**
     *
     * Set Format <br/>
     * <br/>
     * Setting format<br/>
     * <br/>
     * %YY = Year(2 Digits)<br/>
     * %YYYY = Full Year<br/>
     * %MM = Month <br/>
     * %DD = Day<br/>
     * %HH = Hour<br/>
     * %mm = Min<br/>
     * %LL = Level<br/>
     * %CC = ClassName<br/>
     * %MSG = Message(If you don't set this then, this will automatically add in format)<br/>
     * @return LoggingBuilder
     */
    public LoggingBuilder setFormat(String format){
        this.format = format;
        return this;
    }

    public LoggingBuilder useWebLogger(boolean isUsingWebLogger){
        this.isUsingWebLogger = isUsingWebLogger;
        return this;
    }

    public LoggingBuilder setWebLoggerPath(String Path){
        this.webLoggerPath = Path;
        return this;
    }

    public LoggingBuilder setWebLoggerPort(int Port){
        this.webLoggerPort = Port;
        return this;
    }
}
