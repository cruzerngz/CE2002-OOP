package objects;
import util.*;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Restaurant {

    // private Seating[] seating = new Seating[7];
    private HashMap<String, Seating> seating = new HashMap<String, Seating>();
    DateTime dt = new DateTime();
    String path = "../data/reservations.csv";
    ArrayList<String[]> reserve = Data.readCSV(path);
    

    public Restaurant() throws FileNotFoundException {
        String date = new String();
        // loop creates a series of "Seatings" one week into the future
        for (int i = 0; i < 7; ++i) {
            dt.reset();
            dt.deltaDay(i);
            date = dt.getDate().toString();
            seating.put(date, new Seating(date));

            //this for loops checks if there is any reservation for this date in the csv file, then assigns if there are.
            for (int j = 1; j < reserve.size(); ++j) {
                String temp = reserve.get(j)[1];
                //System.out.println("j=" + j + ", temp = " + temp +", date = " + date);
                if (temp.equals(date)){
                    int time = Integer.parseInt(reserve.get(j)[2]);
                    int tableNo = Integer.parseInt(reserve.get(j)[3]);
                    String cust_name = reserve.get(j)[4];
                    int phoneNo = Integer.parseInt(reserve.get(j)[5]);

                    // this.reserve(date, tableNo, time, cust_name, phoneNo);
                    seating.get(date).reserve_csv(tableNo, time, cust_name, phoneNo);
                }
            }
        }
        dt.reset();//resets the datetime cos in the loop we changed it
    }


    public void showNumEmptyTable(String date) {
        seating.get(date).showNumEmptyTable();
    }

    public void showAssignedTable(String date) {
        seating.get(date).showAssignedTable();
    }

    public void assignTable(String date, int tableNo, int orderID) {
        seating.get(date).assignTable(tableNo,orderID);
    }

    public void unassignTable(String date, int tableNo) {
        seating.get(date).unassignTable(tableNo);
    }

    public void reserve(String date, int tableNo, int time, String cust_name, int phoneNo) {
        //this whole chunk simply checks whether the date given falls within the next 7 days
        // System.out.print(date);
        dt.reset();
        String today = dt.getDate().toString();
        //System.out.print(today);
        dt.deltaDay(1);
        String tmr = dt.getDate().toString();
        //System.out.print(tmr);
        dt.deltaDay(1);
        String tmr2 = dt.getDate().toString();
        //System.out.print(tmr2);
        dt.deltaDay(1);
        String tmr3 = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr4 = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr5 = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr6 = dt.getDate().toString();
        dt.deltaDay(1);
        if (!(date.equals(today) || date.equals(tmr) || date.equals(tmr2) || date.equals(tmr3) || date.equals(tmr4) || date.equals(tmr5) || date.equals(tmr6))){
            System.out.println("Reservation can only be made 1 week in advance!");
        }
        else if(date.equals(today) && dt.getTime()>time){
            System.out.println("The time has passed!");
        }
        else{
            seating.get(date).reserveTable(tableNo, time, cust_name, phoneNo);
            String[] newReservation ={"0", date, String.valueOf(time), String.valueOf(tableNo), cust_name, String.valueOf(phoneNo)};
            reserve.add(newReservation);
        }
    }

    public void unreserveTable(String date, int tableNo, int time) {
        seating.get(date).unreserveTable(tableNo, time);
        for (int j = 1; j < reserve.size(); ++j) {
            if (date == reserve.get(j)[1] && String.valueOf(time) == reserve.get(j)[2] && String.valueOf(tableNo)== reserve.get(j)[3]){
                reserve.remove(j);                
            }
        }
    }

    public void showReservationStatus_Day(String date) {
        seating.get(date).showReservationStatus();
    }

    public void showReservationStatus_All() {
        for (int j = 0; j < 7; ++j) {
            dt.reset();
            dt.deltaDay(j);
            String date = dt.getDate().toString();
            
            System.out.println("On " + date + ":");
            seating.get(date).showReservationStatus();
            System.out.println("-------------------");
        }
    }

    public void checkReservation(String date, int time){ 
        System.out.println("At " + date + " " + time + "hrs:");
        seating.get(date).checkReservation(time);
    }
    
    public void writeToCSV(){ 
        for (int j = 0; j < reserve.size(); ++j) {
            reserve.get(j)[0] = String.valueOf(j);
        }
        Data.writeCSV(reserve, path);
    }
}
