package cf.ystapi.Logging.Web;

import cf.ystapi.Logging.Data;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        if(exchange.getRequestURI().getQuery().replace("logger=", "") != null && Data.loggers.size() <= 1){
            response =
                    """
                            <html>
                                <head>
                                    <title>LOGGER | YST API</title>
                                </head>
                                
                                <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
                                <script>
                                    var auto_refresh = setInterval(
                                    function ()
                                    {
                                        $('#log').load('cf.ystapi.Logging.getLog.asRaw?log=%s').fadeIn("slow");
                                        // $("#log").scrollTop($("#log")[0].scrollHeight);
                                    }, 1000);
                                </script>
                                <style>
                                    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
                                    
                                    .big{
                                        font-size: 50px;
                                        font-family: 'Do Hyeon', sans-serif;
                                    }
                                </style>
                                <body style="background-color: black; color: white;">
                                    <text class="big">%s Logger's Log</text>
                                    <hr/>
                                    <br/>
                                    <div id="log" style="overflow-y:scroll; width: 99vw; height: 78.5vh;">Loading Logs</div>
                                </body>
                            </html>
                            """.formatted(exchange.getRequestURI().getQuery().replace("logger=", ""), exchange.getRequestURI().getQuery().replace("logger=", ""));
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
