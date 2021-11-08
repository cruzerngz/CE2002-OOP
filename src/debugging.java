import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Arrays;

import util.*;

public class debugging {
    
    public static void main(String[] args) throws IOException {

        String path = "../data/testdata.csv";

        // ArrayList<String[]> data = Data.readCSV(path);

        // // for(int i=0; i<data.size(); i++) {
        // //     System.out.println(Arrays.asList(data.get(i)));
        // // }

        // //Data.writeCSV(data, "bla.csv");

        // LinkedHashMap<String, String[]> testMap;
        // testMap = Data.parse(data);
        // for(String key: testMap.keySet()) {
        //     System.out.println(key);
        //     System.out.println(Arrays.asList(testMap.get(key)));
        // }
        // data  = Data.parse(testMap);
        
        // for(int i=0; i<data.size(); i++) {
        //     System.out.println(Arrays.toString(data.get(i)));
        // }
        // //System.out.println(Arrays.asList(testMap));

        // LinkedHashMap<String, String[]> newMap = new LinkedHashMap<String, String[]>();
        // newMap.put("col1", new String[]{"10","11"});
        // newMap.put("col2", new String[]{"20","21"});
        // Data.printArrayList(Data.parse(newMap));

        // for(String key: newMap.keySet()) {
        //     System.out.println(Arrays.toString(newMap.get(key)));
        // }


        // testPrint();
        testPrintColour();
        // testParse();
        testReadAndPrint();
        
    }
    public static void testPrint() {
        ArrayList<String[]> testArr = new ArrayList<String[]>();
        testArr.add(new String[]{"col1","col2"});
        testArr.add(new String[]{"10","20"});
        testArr.add(new String[]{"11","21"});

        Data.printArrayList(testArr);
    }
    public static void testPrintColour() {
        ArrayList<String[]> testArr = new ArrayList<String[]>();
        testArr.add(new String[]{StrColour.Green("col1"),StrColour.Cyan("col2")});
        testArr.add(new String[]{"10","20"});
        testArr.add(new String[]{"11","21"});

        Data.printArrayList(testArr);
    }
    public static void testParse() {
        LinkedHashMap<String, String[]> newMap = new LinkedHashMap<String, String[]>();
        newMap.put("col1", new String[]{"10","11"});
        newMap.put("col2", new String[]{"20","21"});


    }
    public static void testReadAndPrint() throws FileNotFoundException {
        ArrayList<String[]> testArr = Data.readCSV("../data/testdata.csv");

        Data.printArrayList(testArr);
    }
}
