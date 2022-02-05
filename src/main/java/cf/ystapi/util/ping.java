package cf.ystapi.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class ping {

    /**
     * getPing(String ip, Port)
     *
     * @version Beta 0.0.1.2
     * @return long as MilliSecond
     * @since Beta 0.0.1.2
     * @throws IOException
     * **/
    public static long Ping(String ip, int port) throws IOException {
        return ping(ip, port);
    }

    /**
     * getWebsitePing(String ip)
     *
     * @version Beta 0.0.1.2
     * @return long as MilliSecond
     * @since Beta 0.0.1.2
     * @throws IOException
     * **/
    public static long WebsitePing(String website) throws IOException {
        return ping(website.replaceAll("https://", "").replaceAll("http://", ""), 80);
    }

    private static long ping(String location, int port) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(location, port);

        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(true);

        long respondedTime = 0;

        Date startedTime = new Date();
        if(sc.connect(socketAddress)){
            Date endedTime = new Date();
            respondedTime = (endedTime.getTime() - startedTime.getTime());
        }
        return respondedTime;
    }
}
