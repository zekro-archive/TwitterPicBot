import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import twitter4j.TwitterException;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zekro on 06.04.2017 / 22:53
 * TwitterPicBot/PACKAGE_NAME
 * © zekro 2017
 */

public class Main {

    public static void main(String[] args) throws ParseException {

        // FOR TESTING PURPOSES
        //HTMLGetter.url = "http://www.pixiv.net/member_illust.php?mode=medium&illust_id=61997631";
        //System.out.println("\"" + HTMLGetter.title() + "\"" + " by " + HTMLGetter.artist() + " | " + HTMLGetter.imgUrl());
        //System.exit(0);

        SECRETS.CONSUMER_KEY = SECRETS.getElement("CONSUMER_KEY");
        SECRETS.CONSUMER_SECRET = SECRETS.getElement("CONSUMER_SECRET");
        SECRETS.ACCESS_TOKEN = SECRETS.getElement("ACCESS_TOKEN");
        SECRETS.ACCESS_SECRET = SECRETS.getElement("ACCESS_SECRET");

        try {
            timerVariables.get(args);
            System.out.println(
                    "[ TWITTER PIC BOT STARTED ]\n" +
                            "Started: " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date()) + "\n" +
                            "Start: " + timerVariables.start.split("_")[1].replaceAll("-", "/") + " - " + timerVariables.start.split("_")[0] + "\n" +
                            "Period: " + (timerVariables.period / 3600000) + " h\n" +
                            "Daily Jokes: " + timerVariables.jokes + "\n\n"
            );
        } catch (Exception e) {
            System.out.println( "Please chose interval (in h) and start date in arguments!\n" +
                                "Example: 'java -jar TwitterPicBot.jar -interval 4 -start 16:00:00_09-12-2017'\n" +
                                "(In this case, the bot will post a picture every 4 hours after 16:00 in 9th December 2017.)");
            System.exit(0);
        }


        DateFormat df = new SimpleDateFormat("HH:mm:ss_dd-MM-yyyy");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    sendFromList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, df.parse(timerVariables.start), timerVariables.period);

        if (timerVariables.jokes) {

            String dateToday = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            Timer jokeTimer = new Timer();
            jokeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        sendJoke();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").parse("18:00:00 " + dateToday), 24*60*60*1000);
        }

    }

    private static void send(String url) {

        HTMLGetter.url = url;
        try {
            TwitterManager.sendTweet();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }

    private static void sendFromList() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("piclist.txt"));
        DateFormat df = new SimpleDateFormat("[dd/MM/yyyy - HH:mm:ss]");
        Date date = new Date();

        String nextLine;
        List<String> allLines = new ArrayList<>();

        while ((nextLine = br.readLine()) != null) {
            allLines.add(nextLine);
        }

        if (allLines.size() > 0) {

            try {
                send(allLines.get(0));
                System.out.println(df.format(date) + "[Queued: " + allLines.size() + "] " + " Sendet " + allLines.get(0));
            } catch (Exception e) {
                System.out.println(df.format(date) + "[ERROR] " + e.getMessage());
                e.printStackTrace();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("piclist.txt"));
            allLines = allLines.subList(1, allLines.size());
            for (String s : allLines) {
                bw.write(s + "\n");
            }
            bw.close();

        } else
            System.out.println(df.format(date) + " List empty -> Sendet nothing...");
    }

    public static class timerVariables {

        static int period = 1000;
        static String start = "";
        static boolean jokes = false;

        static void get(String[] args) {
            List<String> argsList = new ArrayList<>();
            argsList.addAll(Arrays.asList(args));
            period = Integer.parseInt(argsList.get(argsList.indexOf("-interval") + 1)) * 60 * 60 * 1000;
            start = argsList.get(argsList.indexOf("-start") + 1);
            jokes = argsList.contains("-jokes");

        }

    }

    private static void sendJoke() throws IOException {

        File f = new File("jokecount.tml");
        FileWriter w;
        Toml t;
        long count;

        if (!f.exists()) {
            w = new FileWriter("jokecount.tml");
            w.write("count = 0");
            w.close();
        }
        t = new Toml().read(f);
        count = t.getLong("count");

        count++;

        TwitterManager.sendRaw(
                "Daily joke #" + count + "\n" +
                "\"" + RandomJokes.get() + "\""
        );

        TomlWriter tw = new TomlWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        tw.write(map, f);

    }
}
