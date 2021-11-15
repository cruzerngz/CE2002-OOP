import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import ui.*;
import util.*;
import objects.*;


public class debug {
    public static void main(String[] args) {
        SaleStatsUI ui = new SaleStatsUI();
        ui.printOptions();
        SaleStats stats = new SaleStats();
        DateTime time = new DateTime();

        // stats.reset();
        // stats.addRevenue(time.getDaysSinceEpoch(), "100");
        // stats.addRevenue(time.getDaysSinceEpoch(), "200");
        // stats.addRevenue(time.getDaysSinceEpoch(), "100.300.100.300.200");

        ArrayList<String[]> x  = stats.dayRevenue(18946);
        Data.printArrayList(x);
        ArrayList<String[]> y = stats.rangeRevenue(18946, 18949);
        Data.printArrayList(y);
        // ArrayList<String[]> x = SaleStats.parseStringToArrayList("2*100.1*200.5*300");
        // Data.printArrayList(x);
        // x = Data.sortRevFullArrayList(x);
        // Data.printArrayList(x);
        // ArrayList<String[]> x = SaleStats.parseStringToArrayList("100.111.3*300.4*001");
        // // Data.printArrayList(x);
        // // "100.100.100"

        // // String[] y = "1*100".split("\\*");
        // // System.out.println(String.join("*", y));
        // // System.out.println(Arrays.asList(new String[]{"1","2"}));
        // // System.out.println(Arrays.asList(y));

        // String a = "1*100.2*200.3*300.400.500";
        // String b = "100.100.200.300.600.700";
        // String c = SaleStats.addTo(a, b);
        // System.out.println(c);

        // ArrayList<String[]> z = new ArrayList<String[]>();
        // ArrayList<String[]> q = new ArrayList<String[]>();
        // z.add(new String[]{"1","2"});
        // q.add(new String[]{"1","3"});

        // if(z.get(0)[0].equals(z.get(0)[0])) {
        //     System.out.println("match");
        // }

    }
    
}
