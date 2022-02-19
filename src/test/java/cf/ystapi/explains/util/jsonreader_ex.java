package cf.ystapi.explains.util;

import cf.ystapi.util.JsonReader;
import org.json.JSONObject;

import java.io.IOException;

public class jsonreader_ex {
    public static void main(String[] args) {
        try {
            JSONObject j = JsonReader.ReadFromUrl("URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
