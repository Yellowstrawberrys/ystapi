package cf.ystapi.Logging.Web;

import cf.ystapi.Logging.Data;
import cf.ystapi.Logging.Logger;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class SendLog implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", " text/plain");
        Object log = exchange.getRequestURI().getQuery().replace("log=", "");
        if(log == null)
            exchange.sendResponseHeaders(404, 0);
        else {
            StringBuilder logg = new StringBuilder();
            Logger logger = Data.loggers.get(log);
            for(List<String> logs : logger.WholeLog)
                logg.append("<text style=\"@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');font-family: 'Do Hyeon', sans-serif;color:")
                        .append(logs.get(1).equals("DEBUG") ? "gray" : (logs.get(1).equals("INFO")
                                ? "white" : (logs.get(1).equals("WARN") ? "yellow" : "red")))
                        .append("\">").append(logs.get(0)).append("</text>").append("<br/>");
            if(logg.isEmpty())
                exchange.sendResponseHeaders(404, 0);
            else
                exchange.sendResponseHeaders(200, logg.length());
            OutputStream os = exchange.getResponseBody();
            os.write(logg.toString().getBytes());
            os.close();
        }
    }
}
