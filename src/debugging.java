import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import util.*;

public class debugging {
    public static void main(String[] args) throws IOException {
        String path = "./util/2022.csv";

        ArrayList<String[]> data = Data.readCSV(path);
        for(int i=0; i<data.size(); i++) {
            for(int j=0; j<data.get(i).length; j++) {
                System.out.print(data.get(i)[j] + "-");
            }
            System.out.println();
        }

        Data.writeCSV(data, "bla2022.csv");
    }
}
