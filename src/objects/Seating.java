package objects;

import util.*;
import java.util.Scanner;

public class Seating {
    /**
     * Seating is made up of many tables
     * 
     * @author Koh Yu Ze
     * 
     * 
     */

    /**
     * There are 10 TableFor2, 5 TableFor6, 5 TableFor10 in this restaurant
     */
    private String date = new String();
    private Table[] table = new Table[20];
    private int numEmptyTable = 20;

    /**
     * time is the current time
     */
    DateTime dt = new DateTime();
    
    private int time = dt.getTime();
    

    /**
     * Numbers the tables from 1 to 20, inclusive
     * 
     * @param date
     */
    public Seating(String date) {
        for (int i = 0; i < 10; ++i) {
            table[i] = new Table(i+1, date, 2);
        }
        for (int i = 10; i < 15; ++i) {
            table[i] = new Table(i+1, date, 6);
        }
        for (int i = 15; i < 20; ++i) {
            table[i] = new Table(i+1, date, 10);
        }
        this.date = date; // assigns a date to this Seating

        // dt.deltaHour(16); 

        time = time/100 * 100; //rounds the time down to last hour.
    }

    /**
     * 
     * @return the date attribute of this seating
     */
    public String getDate() {
        return (date);
    }

    /**
     * Prints number of empty tables
     */
    public void showNumEmptyTable() {
        System.out.println("There are " + numEmptyTable + " empty tables");
    }

    /**
     * Prints empty tables
     */
    public void showEmptyTable() {
        for (int i = 0; i < table.length; ++i) {
            if (!((Table) table[i]).isOccupied()) {
                System.out.println("Table " +  table[i].getTableNo());
            }
        }
    }

    /**
     * Prints the assigned tables
     */
    public void showAssignedTable() {
        for (int i = 0; i < table.length; ++i) {
            if (((Table) table[i]).isOccupied()) {
                System.out.println(
                        "Table " +  table[i].getTableNo() + " occupied by order " +  table[i].getOrderID() + ".");
            }
        }
    }

    /**
     * Checks whether table is already occupied, or reserved for someone at this time.
     * Then it assigns to the customer.
     * @param tableNo
     * @param orderID
     */
    public boolean assignTable(int tableNo, int orderID, int pax) {
        if ( table[tableNo - 1].isOccupied()) { // the array position is 1 less than actual seatId
            System.out.println("Table already assigned to a customer.");
            return (true);
        } 
        //if the person who reserved is here, assign to customer.
        else if ( table[tableNo - 1].isReserved(time)) { //
            Scanner sc = new Scanner(System.in);
            boolean repeat = true;
            do {
                table[tableNo - 1].checkReservation(time);
                System.out.println("Assign? (Y/N)");
                String assign = sc.nextLine();
                switch(assign){
                    case "Y":
                    case "y":
                        table[tableNo - 1].assign(orderID, pax);
                        numEmptyTable -= 1;
                        
                        repeat = false;
                        return (false);
                    case "N":
                    case "n":
                        System.out.println("Going back ...");
                        repeat = false;
                        return (true);
                    default:
                }
            } while (repeat);
        } 
        else if (table[tableNo - 1].checkMaxPax() < pax){
            System.out.printf("This table can only seat %d people. \n", table[tableNo - 1].checkMaxPax());
            return (true);
        }
        else {
            table[tableNo - 1].assign(orderID, pax);
            numEmptyTable -= 1;
            
            return (false);
        }
        return (false);
    }

    /**
     * Method to unassign table
     * @param tableNo
     */
    public void unassignTable(int tableNo) {
        table[tableNo - 1].unassign();
        numEmptyTable += 1;
    }


    /**
     * Reserves the table
     * @param tableNo
     * @param time
     * @param cust_name
     * @param phoneNo
     */
    public void reserveTable(int tableNo, int time, String cust_name, int phoneNo, int pax) {
        table[tableNo - 1].reserve(phoneNo, time, cust_name, pax);
    }
    
    /**
     * This separate reserve method is used for initializing, when the data from the CSV is read.
     * @param tableNo
     * @param time
     * @param cust_name
     * @param phoneNo
     */
    public void reserve_csv(int tableNo, int time, String cust_name, int phoneNo) {
        table[tableNo - 1].reserve_csv(phoneNo, time, cust_name);
    }

    /**
     * Method to unassign table
     * @param tableNo
     * @param time
     */
    public void unreserveTable(int tableNo, int time, boolean print) {
        table[tableNo - 1].unreserve(time, print);
    }

    /**
     * Prints the reservation status
     */
    public void showReservationStatus() {
        for (int i = 0; i < table.length; ++i) {
            table[i].reservedStatus();
        }
    }

    /**
     * Prints out all the tables that are reserved at the given time. So the staff can assign an unreserved table for a walk-in customer.
     * @param time
     */
    public void checkReservation(int time) {
        int numReserve = 0;
        for (int i = 0; i < 20; ++i) {
            if (table[i].isReserved(time)){
                numReserve+=1;
                table[i].checkReservation(time);
            }
        }
        if (numReserve == 0){
            System.out.println("There are no reservations.");
        }
    }

    public void checkFullReservation(int time) {
        int numReserve = 0;
        for (int i = 0; i < 20; ++i) {
            if (table[i].isReserved(time)){
                numReserve+=1;
            }
        }
        if (numReserve == 20){
            System.out.println("Reservations are full for this timing.");
        }
    }
}