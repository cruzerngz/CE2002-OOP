import util.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import ui.*;
import objects.*;
import util.*;


public class Debug2 {
    public static void main(String[] args) {
        MenuSetting temp = new MenuSetting();

        // temp.printOptions();

        ArrayList<String[]> menu = Data.readCSV(Path.menu);
        LinkedHashMap<String, String[]> map = Data.parse(menu);

        map.get("recommend")[0] = "asd";
        
    }

}

//for i in array:
//    do sth with i