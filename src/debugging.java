import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import util.*;

public class debugging {
    public static void main(String[] args) throws IOException {
        String path = "./util/testdata.csv";

        ArrayList<String[]> data = Data.readCSV(path);

        // for(int i=0; i<data.size(); i++) {
        //     System.out.println(Arrays.toString(data.get(i)));
        // }

        //Data.writeCSV(data, "bla.csv");

        Data.printArrayList(data);
        //System.out.println(Arrays.asList(result));

        //System.out.println(key + Arrays.toString(result.get(key)));

        // HashMap<String,String> thisthing = new HashMap<String, String>();
        // thisthing.put("1","10");
        // thisthing.put("2","20");
        // thisthing.put("3","30");

        // for(String thing: thisthing.keySet()) {
        //     key = thing.toString();
        //     System.out.print(key);
        //     System.out.println(thisthing.get(key));
        // }
        
    }
}
