package objects;

import util.*;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WalkIn {
    DateTime dt = new DateTime();
    String path = "../data/reservations.csv";
    ArrayList<String[]> reserve = Data.readCSV(path);

    public WalkIn() {
        // creates the an instance of Seating for today.
        String date = dt.getDate().toString();
        Seating seating = new Seating(date);

        // this for loops checks if there is any reservation for this date in the csv
        // file, then assigns if there are.
        for (int j = 1; j < reserve.size(); ++j) {
            String temp = reserve.get(j)[1];
            if (temp.equals(date)) {
                int time = Integer.parseInt(reserve.get(j)[2]);
                int tableNo = Integer.parseInt(reserve.get(j)[3]);
                String cust_name = reserve.get(j)[4];
                int phoneNo = Integer.parseInt(reserve.get(j)[5]);

                seating.reserve_csv(tableNo, time, cust_name, phoneNo);
            }
        }
    }
}
