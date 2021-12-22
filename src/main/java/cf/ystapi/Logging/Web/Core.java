package cf.ystapi.Logging.Web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Objects;

public class Core {

    public HttpServer server = null;

    public void start(int port, String path) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(path, new Handler());
        server.createContext("/cf.ystapi.Logging.getLog.asRaw", new SendLog());
        server.createContext("/favicon.ico", new Favicon());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class Favicon implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File file = null;
            try {
                file = new File(Objects.requireNonNull(Core.class.getResource("ystapilogo.ico")).toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            if(file == null)
                System.out.println("File is NULL");

            exchange.getResponseHeaders().set("Content-Type", "image/x-icon");
            exchange.sendResponseHeaders(200, file.length());
            OutputStream os = exchange.getResponseBody();
            Files.copy(file.toPath(), os);
            os.close();
        }
    }
}
