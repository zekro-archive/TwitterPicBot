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

        SECRETS.CONSUMER_KEY = SECRETS.getElement("CONSUMER_KEY");
        SECRETS.CONSUMER_SECRET = SECRETS.getElement("CONSUMER_SECRET");
        SECRETS.ACCESS_TOKEN = SECRETS.getElement("ACCESS_TOKEN");
        SECRETS.ACCESS_SECRET = SECRETS.getElement("ACCESS_SECRET");


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
        }, 0, 8*60*60*1000);

        //System.out.println("Enter Deviantart-URL: ");
        //send (new Scanner(System.in).next());

    }

    public static void send(String url) {

        HTMLGetter.url = url;
        try {
            TwitterManager.sendTweet();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

    }

    public static void sendFromList() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("piclist.txt"));
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("[dd/MM/yyyy - HH:mm:ss]");

        String nextLine;
        List<String> allLines = new ArrayList<String>();

        while ((nextLine = br.readLine()) != null) {
            allLines.add(nextLine);
        }

        if (allLines.size() > 0) {

            send(allLines.get(0));
            System.out.println(df.format(date) + " Sendet " + allLines.get(0));
            BufferedWriter bw = new BufferedWriter(new FileWriter("piclist.txt"));
            allLines = allLines.subList(1, allLines.size());
            for (String s : allLines) {
                bw.write(s + "\n");
            }
            bw.close();

        }
        System.out.println(df.format(date) + " List empty -> Sendet nothing...");
    }
}
