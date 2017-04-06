import twitter4j.TwitterException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by zekro on 06.04.2017 / 22:53
 * TwitterPicBot/PACKAGE_NAME
 * © zekro 2017
 */

public class Main {

    public static void main(String[] args) throws ParseException {

        SECRETS.CONSUMER_KEY = SECRETS.getElement("CONSUMER_KEY");
        SECRETS.CONSUMER_SECRET = SECRETS.getElement("CONSUMER_SECRET");
        SECRETS.ACCESS_TOKEN = SECRETS.getElement("ACCESS_TOKEN");
        SECRETS.ACCESS_SECRET = SECRETS.getElement("ACCESS_SECRET");

        System.out.println("Enter Deviantart-URL: ");
        send (new Scanner(System.in).next());

    }

    public static void send(String url) {

        HTMLGetter.url = url;
        try {
            TwitterManager.sendTweet();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }

}
