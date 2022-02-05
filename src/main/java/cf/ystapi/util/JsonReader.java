package cf.ystapi.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonReader {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    /**
     * ReadFromUrl
     * @throws IOException
     * @throws JSONException
     * **/
    public static JSONObject ReadFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    /**
     * ReadFrom String
     * **/
    public static JSONObject ReadFromString(String toconvert){
        return new JSONObject(toconvert);
    }

    /**
     * ReadFrom Char
     * **/
    public static JSONObject ReadFromChar(char toconvert){
        return new JSONObject(String.valueOf(toconvert));
    }
}
