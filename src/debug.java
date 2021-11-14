import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import ui.*;
import util.*;
import objects.*;


public class debug {
    // public static void main(String args[]) throws IOException {

    //     SaleStatsUI.main(args);
    //     // testTime();
    //     // testStats();
    //     // testColour();
    //     // DateTime date = new DateTime();
    //     // int epochDay = date.ymdToEpochDay(2021, 11, 12);
    //     // System.out.println(epochDay);
    //     // epochDay = date.getDaysSinceEpoch();
    //     // System.out.println(epochDay);

    // }
    // public static void testTime() throws IOException {
    //     DateTime datetime = new DateTime();
    //     System.out.println(datetime.getDateTime());
    //     datetime.deltaHour(1);
    //     datetime.deltaDay(-112);
    //     datetime.reset();
    // }

    // public static void testStats() {
    //     SaleStats stats = new SaleStats();
    //     stats.reset(); //clear all data
    //     System.out.println("Adding 50 days of data");
    //     for(int i=18938; i>18888; i--) {
    //         stats.addRevenue(i,10f);
    //     }
    //     System.out.print("removing one day of data: ");
    //     System.out.println(stats.delRevenue(18900));

    //     System.out.print("subtracting revenue from one day: ");
    //     System.out.println(stats.subRevenue(18901, 5f));

    //     Data.printArrayList(stats.getPrintMatrix());

    //     System.out.print("Showing month data: ");
    //     Float x = stats.monthRevenue(18936);
    //     System.out.println(x);

    //     System.out.print("Showing day data if any: ");
    //     x = stats.dayRevenue(18950);
    //     System.out.println(x);
    // }

    // public static void testColour() {
    //     Colour.print(Colour.TEXT_BLUE, "testing... printing single line. ");
    //     Colour.println(Colour.TEXT_YELLOW, "new colour haha");
    // }
}
