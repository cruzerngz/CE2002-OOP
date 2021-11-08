import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import util.*;

public class debugging {
    
    public static void main(String[] args) throws IOException {

        String path = "../data/testdata.csv";

        ArrayList<String[]> data = Data.readCSV(path);

        System.out.println(data.size());
        System.out.println(data.get(0).length);

        // for(int i=0; i<data.size(); i++) {
        //     System.out.println(Arrays.asList(data.get(i)));
        // }

        //Data.writeCSV(data, "bla.csv");

        HashMap<String, String[]> testMap;
        testMap = Data.parse(data);
        // data  = Data.parse(testMap);
        //System.out.println(Arrays.asList(testMap));


        //Data.printArrayList(data);


        
    }
}
