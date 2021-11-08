//this file is just here for testing purposes, can delete later on.
//import Menu.menuSetting;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import objects.Restaurant;

import util.*;

public class ZTest {
    public static void main(String[] args) throws FileNotFoundException {

        Restaurant res = new Restaurant();

        // res.showReservationStatus_All();
        res.checkReservation("14/11/2021", 1800);
        // // "(1) Create a new reservation booking");
        // res.reserve("05/11/2021", 3, 1200, "Koh", 92130987);
        // res.reserve("30/11/2021", 3, 1200, "Koh", 92130987);
        // res.reserve("06/11/2021", 3, 1200, "Koh", 92130987);

        // "(2) Check for reservation booking");
        // "(3) Remove reservation booking");
        // "(4) List existing reservation booking");
        //Data data = new Data();


        // ArrayList<String[]> reserve = Data.readCSV("C:/Users/kohyu/Documents/GitHub/CE2002-OOP/data/reservations.csv");

        // System.out.println(Arrays.toString(reserve.get(1)) );
        // Data.printArrayList(reserve);


        //array = d.readCSV("reservations.csv");
        

    }
}