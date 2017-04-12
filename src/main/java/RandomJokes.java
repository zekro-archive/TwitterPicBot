import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by zekro on 12.04.2017 / 09:49
 * TwitterPicBot/PACKAGE_NAME
 * © zekro 2017
 */
public class RandomJokes {

    public static String get() {

        String out = "";

        try {
            out = readJsonFromUrl("http://tambal.azurewebsites.net/joke/random").getString("joke");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return out;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = rd.readLine();
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
