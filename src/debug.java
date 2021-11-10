import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import ui.*;
import util.*;
import objects.*;


public class debug {
    public static void main(String args[]) throws IOException {

        // testTime();
        testStats();

    }
    public static void testTime() throws IOException {
        DateTime datetime = new DateTime("../data");
        System.out.println(datetime.getDateTime());
        datetime.deltaHour(1);
        datetime.deltaDay(-112);
        datetime.reset();
    }
    public static void testStats() {
        saleStats stats = new saleStats();
        stats.reset();
        System.out.println("Adding 50 days of data");
        for(int i=18938; i>18888; i--) {
            stats.addRevenue(i,10f);
        }
        Data.printArrayList(stats.getMatrix());
        System.out.print("Showing month data: ");
        Float x = stats.monthRevenue(18936);
        System.out.println(x);

        System.out.print("Showing day data if any: ");
        x = stats.dayRevenue(18937);
        System.out.println(x);
    }
}
