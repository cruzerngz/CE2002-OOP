import util.*;

import java.util.ArrayList;

import ui.*;


public class Debug2 {
    public static void main(String[] args) {

        // String target;
        // String[] targetArr;
        // targetArr = target.split(".");
        // for(String i: targetArr) {
        //     //do stuff
        //     int temp = Integer.parseInt(i);
        //     //["1","2","3"....]
        // }
        // for(int i=0; i>targetArr.length; i++) {
            
        // }
        ArrayList<String[]> arr = Data.readCSV("../data/menu.csv");
        arr = Data.sortArrayList(arr);
        Data.printArrayList(arr);
        
    }
}

//for i in array:
//    do sth with i