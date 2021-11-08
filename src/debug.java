import java.security.NoSuchAlgorithmException;

import util.*;

public class debug {
    public static void main(String args[]) throws NoSuchAlgorithmException {
        DateTime datetime = new DateTime();

        System.out.println(datetime.getTime());
        System.out.println(StrColour.Red(datetime.getDate()));
        System.out.println(datetime.getDateTime());

        datetime.deltaDay(-100);
        datetime.deltaHour(-1203);
        System.out.println(datetime.getTime());
        System.out.println(datetime.getDate());
        System.out.println(datetime.getDateTime());

        datetime.reset();
        System.out.println(datetime.getTime());
        System.out.println(datetime.getDate());
        System.out.println(datetime.getDateTime());

        String z = "\u001B[0m";
        String x = "this is coloured";

        x = StrColour.Green(x);
        System.out.println(x);

        //my precious regex
        //filters out the unicode special characters
        String regex = "\\e\\[[0-9]+m";
        x = x.replaceAll(regex, "");
        System.out.println(x);
        System.out.println(Password.hash("inpuu"));
    }
}
