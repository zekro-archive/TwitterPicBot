import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by zekro on 06.04.2017 / 22:59
 * TwitterPicBot/PACKAGE_NAME
 * © zekro 2017
 */

public class TwitterManager {

    public static void sendTweet() throws TwitterException {

        // TWITTER INITIALIZATION

        ConfigurationBuilder confBuilder = new ConfigurationBuilder();

        confBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(SECRETS.CONSUMER_KEY)
                .setOAuthConsumerSecret(SECRETS.CONSUMER_SECRET)
                .setOAuthAccessToken(SECRETS.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(SECRETS.ACCESS_SECRET);

        TwitterFactory twitterFactory = new TwitterFactory(confBuilder.build());
        Twitter twitter = twitterFactory.getInstance();

        dlPic(HTMLGetter.imgUrl());

        String assambledMessage =   "\"" + HTMLGetter.title() + "\" by " + HTMLGetter.artist() + "\n" +
                                    "(" + HTMLGetter.url + ")";

        File f = new File("pic.jpg");
        StatusUpdate status = new StatusUpdate(assambledMessage);
        status.setMedia(f);
        twitter.updateStatus(status);
    }

    private static void dlPic(String url) {

        try {

            URL source = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(source.openStream());
            FileOutputStream fos = new FileOutputStream("pic.jpg");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendRaw(String msg) {

        ConfigurationBuilder confBuilder = new ConfigurationBuilder();

        confBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(SECRETS.CONSUMER_KEY)
                .setOAuthConsumerSecret(SECRETS.CONSUMER_SECRET)
                .setOAuthAccessToken(SECRETS.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(SECRETS.ACCESS_SECRET);

        TwitterFactory twitterFactory = new TwitterFactory(confBuilder.build());
        Twitter twitter = twitterFactory.getInstance();

        try {
            twitter.updateStatus(msg);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }

}
