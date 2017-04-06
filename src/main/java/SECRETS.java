import com.moandjiezana.toml.Toml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by zekro on 06.04.2017 / 22:50
 * TwitterPicBot/PACKAGE_NAME
 * © zekro 2017
 */

public class SECRETS {

    public static String CONSUMER_KEY = "";
    public static String CONSUMER_SECRET = "";
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_SECRET = "";

    public static String getElement(String elementTitle) {

        File settingsFile = new File("tokens.txt");
        String out = "";

        if (!settingsFile.exists()) {
            try {

                PrintWriter pw = new PrintWriter("tokens.txt");
                pw.println( "# ENTER HERE YOUR TWITTER APP CREDENTIALS YOU WILL GET FROM HERE: \n" +
                        "# https://apps.twitter.com\n\n" +
                        "CONSUMER_KEY = \"\"\n" +
                        "CONSUMER_SECRET = \"\"\n" +
                        "ACCESS_TOKEN = \"\"\n" +
                        "ACCESS_SECRET = \"\"\n");
                pw.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            Toml toml = new Toml().read(settingsFile);

            try {
                out = toml.getString(elementTitle);
                if (out.equals("")) {
                    System.out.println("PLEASE ENTER YOUR TWITTER APP CREDENTIALS IN 'tokens.txt'!");
                    new Scanner(System.in).next();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("PLEASE ENTER YOUR TWITTER APP CREDENTIALS IN 'tokens.txt'!");
                new Scanner(System.in).next();
                System.exit(0);
            }

        }

        return out;

    }

}