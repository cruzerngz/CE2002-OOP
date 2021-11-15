package objects;
import util.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {

    /**
     * Restaurant is made up of 7 instances of Seating. Every Seating represents one day of restaurant operation and
     * customers can book for a table up to 1 week in advance.
     * 
     */
    private HashMap<String, Seating> seating = new HashMap<String, Seating>();
    DateTime dt = new DateTime();   
    String path = Path.reservations;
    ArrayList<String[]> reserve = Data.readCSV(path);
    

    public Restaurant() {
        String date = new String();
        // loop creates a series of "Seatings" one week into the future
        for (int i = 0; i < 7; ++i) {
            date = dt.getDate().toString();
            seating.put(date, new Seating(date));

            //this for loops checks if there is any reservation for this date in the csv file, then assigns if there are.
            for (int j = 1; j < reserve.size(); ++j) {
                String temp = reserve.get(j)[1];
                if (temp.equals(date)){
                    int time = Integer.parseInt(reserve.get(j)[2]);
                    int tableNo = Integer.parseInt(reserve.get(j)[3]);
                    String cust_name = reserve.get(j)[4];
                    int phoneNo = Integer.parseInt(reserve.get(j)[5]);

                    seating.get(date).reserve_csv(tableNo, time, cust_name, phoneNo);
                }
            }
            dt.deltaDay(1);
        }
        dt.deltaDay(-7);
    }

    /**
     * Prints out the number of empty tables currently.
     * @param date
     */
    public void showEmptyTable(String date) {
        System.out.println("Unoccupied Tables:");
        seating.get(date).showEmptyTable();
    }

    public void showNumEmptyTable(String date) {
        seating.get(date).showNumEmptyTable();
    }

    /**
     * Prints the assigned tables and the order number that is associated with that table.
     * @param date
     */
    public void showAssignedTable(String date) {
        seating.get(date).showAssignedTable();
    }
    /**
     * Assigns a table
     * @param date
     * @param tableNo
     * @param orderID
     */
    public void assignTable(String date, int tableNo, int orderID, int pax) {
        boolean x;
        x = seating.get(date).assignTable(tableNo,orderID, pax);
        while (x){
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose another table:");
            tableNo = sc.nextInt();
            x = seating.get(date).assignTable(tableNo,orderID, pax);
        }
    }

    /**
     * Unassigns a table
     * @param date
     * @param tableNo
     */
    public void unassignTable(String date, int tableNo) {
        seating.get(date).unassignTable(tableNo);
    }

    /**
     * Reserves a table. Tables can be reserved up to 1 week in advance.
     * @param date
     * @param tableNo
     * @param time
     * @param cust_name
     * @param phoneNo
     */
    public void reserve(String date, int tableNo, int time, String cust_name, int phoneNo, int pax) {
        //this whole chunk simply checks whether the date given falls within the next 7 days
        String today = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr2 = dt.getDate().toString();
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
            seating.get(date).reserveTable(tableNo, time, cust_name, phoneNo, pax);
            String[] newReservation ={"0", date, String.valueOf(time), String.valueOf(tableNo), cust_name, String.valueOf(phoneNo)};
            reserve.add(newReservation);
        }
        dt.deltaDay(-7);
    }

    

    /**
     * Unreserves a table
     * @param date
     * @param tableNo
     * @param time
     */
    public void unreserveTable(String date, int tableNo, int time, boolean print) {
        seating.get(date).unreserveTable(tableNo, time, print);
        for (int j = 1; j < reserve.size(); ++j) {
            if (date == reserve.get(j)[1] && String.valueOf(time) == reserve.get(j)[2] && String.valueOf(tableNo)== reserve.get(j)[3]){
                reserve.remove(j);                
            }
        }
    }

    /**
     * Shows reservations for one day
     * @param date
     */
    public void showReservationStatus_Day(String date) {
        seating.get(date).showReservationStatus();
    }

    /**
     * shows all reservations for next 7 days
     */
    public void showReservationStatus_All() {
        for (int j = 0; j < 7; ++j) {
            // dt.reset();
            String date = dt.getDate().toString();
            
            System.out.println("On " + date + ":");
            seating.get(date).showReservationStatus();
            System.out.println("-------------------");
            dt.deltaDay(1);
        }
        dt.deltaDay(-7);
    }

    /**
     * Check what tabels are reserves at a given date and time
     * @param date
     * @param time
     */
    public void checkReservation(String date, int time){ 
        if(dt.getTime()-time>=10 ){
            System.out.println();
            for(int i=1 ; i<21 ; i++){
                seating.get(date).unreserveTable(i,dt.getTime(),false);
            }
            System.out.println("table unreserved due to time expire");
            System.out.println();
            
        }
        else{
            System.out.println("At " + date + " " + time + "hrs:");
            seating.get(date).checkReservation(time);
            System.out.println();
        }
        
    }
    
    public boolean checkFullReservation(String date, int time){
        String today = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr2 = dt.getDate().toString();
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
            return false;
        }
        else{
            return (seating.get(date).checkFullReservation(time));
        }
        
    }

    /**
     * Writes the new reservations into the CSV file to store the data.
     */
    public void writeToCSV(){ 
        for (int j = 0; j < reserve.size(); ++j) {
            reserve.get(j)[0] = String.valueOf(j);
        }
        Data.writeCSV(reserve, path);
    }
}
