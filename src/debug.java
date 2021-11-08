import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import ui.*;
import util.*;

public class debug {
    public static void main(String args[]) throws IOException {
        // DateTime datetime = new DateTime();

        // System.out.println(datetime.getDaysSinceEpoch());
        // System.out.println(datetime.daysToDate(18900));
        // System.out.println(datetime.getTime());
        // System.out.println(datetime.getTimeString());

        // String z = "\u001B[0m";
        // String x = "this is coloured";

        // x = StrColour.Green(x);
        // System.out.println(x);

        // //my precious regex
        // //filters out the unicode special characters
        // String regex = "\\e\\[[0-9]+m";
        // x = x.replaceAll(regex, "");
        // System.out.println(x);
        // System.out.println(Password.hash("inpuu"));
        // testTime();
        char esc = 27;
        char thing = 34;
        //System.out.println(esc + "[" + thing + "asdasd");
        //System.out.println("\033[31;1;4m asd \033[0m");
        AdminUI.main(args);
    }
    public static void testTime() throws IOException {
        DateTime datetime = new DateTime("../data");
        System.out.println(datetime.getDateTime());
        datetime.deltaHour(1);
        datetime.deltaDay(-112);
        datetime.reset();
    }
}
