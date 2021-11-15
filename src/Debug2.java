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