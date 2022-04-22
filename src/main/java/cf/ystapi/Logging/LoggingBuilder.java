package cf.ystapi.Logging;

import cf.ystapi.Logging.Web.Core;

import java.io.IOException;

public class LoggingBuilder {

    String format = "%MM%/%DD%/%YYYY% %HH%:%mm% | [%LL%] %MSG%";
    boolean isUsingWebLogger = false;
    int webLoggerPort = 8080;
    String webLoggerPath = "/log";

    public Logger build(String name) {
        Logger logger = new Logger();
        logger.format = this.format;
        logger.name = name;
        Data.loggers.put(name, logger);

        if(isUsingWebLogger && Data.web == null) {
            Data.web = new Core();
            try {
                Data.web.start(8080, webLoggerPath);
            } catch (IOException e) {
                Logger.getLoggerByName("System").error("ERROR TO START WEB LOGGER");
                e.printStackTrace();
            }
        }

        return logger;
    }

    /**
     *
     * Set Format <br/>
     * <br/>
     * Setting format<br/>
     * <br/>
     * %YY% = Year(2 Digits)<br/>
     * %YYYY% = Full Year<br/>
     * %MM% = Month <br/>
     * %DD% = Day<br/>
     * %HH% = Hour<br/>
     * %mm% = Min<br/>
     * %LL% = Level<br/>
     * %CC% = ClassName<br/>
     * %MSG% = Message(If you don't set this then, this will automatically add in format)<br/>
     * @return LoggingBuilder
     */
    public LoggingBuilder setFormat(String format){
        this.format = format;
        return this;
    }

    /**
     * Setting WebLogger
     * **/
    public LoggingBuilder useWebLogger(boolean isUsingWebLogger){
        this.isUsingWebLogger = isUsingWebLogger;
        return this;
    }

    /**
     * Setting WebPath(http://localhost:8080<strong>/log</strong>)
     * **/
    public LoggingBuilder setWebLoggerPath(String Path){
        this.webLoggerPath = Path;
        return this;
    }

    /**
     * Setting WebPort(http://localhost:<strong>8080</strong>/log)
     * **/
    public LoggingBuilder setWebLoggerPort(int Port){
        this.webLoggerPort = Port;
        return this;
    }
}
