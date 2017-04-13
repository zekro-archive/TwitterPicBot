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

            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36").get();

            if (url.contains("deviantart"))
                out = doc.select(".title").text();
            else if (url.contains("artstation"))
                out = doc.select("title").text().split("-")[1].replaceFirst(" ", "").split(",")[0];
            else if (url.contains("pixiv"))
                out = doc.select(".userdata").select(".title").text();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;

    }

    public static String artist() {

        String out = "";

        try {

            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36").get();

            if (url.contains("deviantart"))
                out = doc.select("h1").select(".username-with-symbol.u").text();
            else if (url.contains("artstation"))
                out = doc.select("title").text().split("-")[1].split(",")[1].replaceFirst(" ", "");
            else if (url.contains("pixiv"))
                out = doc.select(".userdata").select(".name").select("a").text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;

    }

    public static String imgUrl() {

        String out = "";

        try {

            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36").get();

            if (url.contains("deviantart"))
                out = doc.select(".dev-content-normal").attr("src");
            else if (url.contains("artstation"))
                out = doc.select("[property=\"og:image\"]").attr("content");
            else if (url.contains("pixiv"))
                out = doc.select(".require-register.medium-image._work").select("img").attr("src");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;

    }

}
