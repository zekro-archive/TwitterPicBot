import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * Created by zekro on 06.04.2017 / 23:09
 * TwitterPicBot/PACKAGE_NAME
 * © zekro 2017
 */

public class HTMLGetter {

    static String url = "";

    public static String title() {

        String out = "";

        try {
            Document doc = Jsoup.connect(url).get();
            out = doc.select(".title").text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;

    }

    public static String artist() {

        String out = "";

        try {
            Document doc = Jsoup.connect(url).get();
            out = doc.select("h1").select(".username-with-symbol.u").text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;

    }

    public static String imgUrl() {

        String out = "";

        try {
            Document doc = Jsoup.connect(url).get();
            out = doc.select(".dev-content-normal").attr("src");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;

    }

}
