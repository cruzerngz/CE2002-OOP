import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Arrays;

import util.*;

public class debugging {
    
    public static void main(String[] args) throws FileNotFoundException {

        // testPrint();
        // testPrintColour();
        // testParseMap();
        // testParseArr();
        // testReadAndPrint();
        // testPutMap();
        testAllParse();

        // String[] testArr = new String[3];
        // String[] testArr2 = new String[2];
        // testArr2[0] = "-1";
        // testArr2[1] = "-2";
        // testArr = testArr2;
        // testArr = testArr2.clone();
        // System.out.println(Arrays.asList(testArr));
        // System.out.println(Arrays.asList(testArr2));
        
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

    public static void testParseMap() {
        LinkedHashMap<String, String[]> newMap = new LinkedHashMap<String, String[]>();
        newMap.put("col1", new String[]{"10","11"});
        newMap.put("col2", new String[]{"20","21"});

        Data.printArrayList(Data.parse(newMap));
        
    }

    public static void testParseArr() {
        ArrayList<String[]> newArr = new ArrayList<String[]>();
        newArr.add(new String[]{"col1","col2"});
        newArr.add(new String[]{"10","20"});
        newArr.add(new String[]{"11","21"});

        LinkedHashMap<String, String[]> newMap = new LinkedHashMap<String, String[]>();
        newMap = Data.parse(newArr);
        for(String key: newMap.keySet()) {
            System.out.println(Arrays.asList(newMap.get(key)));
        }

    }

    public static void testReadAndPrint() throws FileNotFoundException {
        ArrayList<String[]> testArr = Data.readCSV("../data/testdata.csv");

        Data.printArrayList(testArr);
    }

    public static void testPutMap() {
        LinkedHashMap<String, String[]> newMap = new LinkedHashMap<String, String[]>();
        String[] colArr = {"col1","col2","col3","col4"};
        String[] data1 = {"10","11"};
        String[] data2 = {"20","21"};
        String[] data3 = {"30","31"};
        String[] data4 = {"40","41"};
        ArrayList<String[]> holder = new ArrayList<String[]>();
        holder.add(data1);
        holder.add(data2);
        holder.add(data3);
        holder.add(data4);

        for(int i=0; i<colArr.length; i++) {
            newMap.put(colArr[i], holder.get(i));
        }
        // newMap.put(colArr[0], data1);
        // newMap.put(colArr[1], data2);
        // newMap.put(colArr[2], data3);
        // newMap.put(colArr[3], data4);

        for(String key: newMap.keySet()) {
            System.out.print(key + " ");
            System.out.print(Arrays.asList(newMap.get(key)));
            System.out.println();
        }
        // Data.printArrayList(Data.parse(newMap));
    }

    public static void testAllParse() throws FileNotFoundException {

        ArrayList<String[]> testArr = Data.readCSV("../data/newtest.csv");
        
        LinkedHashMap<String, String[]> testMap = new LinkedHashMap<String, String[]>();
        Data.printArrayList(testArr);
        testMap = Data.parse(testArr);
        for(String key: testMap.keySet()) {
            System.out.print(key + " ");
            System.out.print(Arrays.asList(testMap.get(key)));
            System.out.println();
        }
        testArr = Data.parse(testMap);
        Data.printArrayList(testArr);

        for(int i=0; i<testArr.size(); i++) {
            System.out.println(Arrays.asList(testArr.get(i)));
        }
    }   
}
