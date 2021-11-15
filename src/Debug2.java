import util.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import ui.*;
import objects.*;
import util.*;


public class Debug2 {
    public static void main(String[] args) {
        OrderUI orderui = new OrderUI();
        // orderui.printOptions();
        SaleStats stats = new SaleStats();
        DateTime dt = new DateTime();
        int ref = dt.getDaysSinceEpoch();
        ref -= 5;
        stats.addRevenue(ref++, "001.002.003.101.102.200.201.300.301");
        stats.addRevenue(ref++, "100.101.102.111.202.300");
        stats.addRevenue(ref++, "201.200.203.300.004.002");
        stats.addRevenue(ref++, "004.111.303.204.204.204.204.201.200.200.204");
        stats.addRevenue(ref++, "100.111.114.114.111");

        ArrayList<String[]> menu = Data.readCSV(Path.menu);
        LinkedHashMap<String, String[]> map = Data.parse(menu);

        String regex = "\\e\\[[0-9]+m";
        // Colour.println(Colour.TEXT_PURPLE, "test");
        System.out.println(Colour.Red("test").replaceAll(regex, ""));
        System.out.println("\u001b[7m testtesttestv \u001B[0m");

        //map.get("recommend")[0] = "asd";
        
    }

}

//for i in array:
//    do sth with i