package objects;

import util.*;
import java.util.Scanner;

public class Seating {
    /**
     * Restaurant is made up of many tables
     * 
     * @author Koh Yu Ze
     * 
     * 
     */

    /**
     * There are 20 tables in this restaurant
     */
    private String date = new String();
    private Table[] table = new Table[20];
    private int numEmptyTable = 20;

    DateTime dt = new DateTime();
    private int time = dt.getTime();

    /**
     * Numbers the tables from 1 to 20, inclusive
     * 
     * @param date
     */
    public Seating(String date) {
        for (int i = 0; i < 20; ++i) {
            table[i] = new Table(i + 1, date);
        }
        this.date = date; // assigns a date to this Seating
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
     * Prints the assigned tables
     */
    public void showAssignedTable() {
        for (int i = 0; i < table.length; ++i) {
            if (table[i].isOccupied()) {
                System.out.println(
                        "Table " + table[i].getTableNo() + " occupied by order " + table[i].getOrderID() + ".");
            }
        }
    }

    /**
     * Method to assign table. Provide table number and order number.
     */
    public void assignTable(int tableNo, int orderID) {
        if (table[tableNo - 1].isOccupied()) { // the array position is 1 less than actual seatId
            System.out.println("Table already assigned to a customer.");
        } 
        //if the person who reserved is here, assign to customer.
        else if (table[tableNo - 1].isReserved(time)) { //
            Scanner sc = new Scanner(System.in);
            boolean repeat = true;
            do {
                System.out.println("Table reserved to a customer at this time. Assign? (Y/N)");
                String assign = sc.nextLine();
                switch(assign){
                    case "Y":
                    case "y":
                        table[tableNo - 1].assign(orderID);
                        numEmptyTable -= 1;
                        System.out.println("Table Assigned!");
                        repeat = false;
                        break;
                    case "N":
                    case "n":
                        System.out.println("Going back ...");
                        repeat = false;
                        break;
                    default:
                }
            } while (repeat);
        } else {
            table[tableNo - 1].assign(orderID);
            numEmptyTable -= 1;
            System.out.println("Table Assigned!");
        }

    }

    /**
     * Method to unassign table
     */
    public void unassignTable(int tableNo) {
        table[tableNo - 1].unassign();
        numEmptyTable += 1;
    }

    /**
     * Method to reserve table. Provide table number, phone and time
     */
    public void reserveTable(int tableNo, int time, String cust_name, int phoneNo) {
        table[tableNo - 1].reserve(phoneNo, time, cust_name);
    }

    public void reserve_csv(int tableNo, int time, String cust_name, int phoneNo) {
        table[tableNo - 1].reserve_csv(phoneNo, time, cust_name);
    }

    /**
     * Method to unassign table
     */
    public void unreserveTable(int tableNo, int time) {
        table[tableNo - 1].unreserve(time);
    }

    /**
     * Prints the reservation status
     */
    public void showReservationStatus() {
        for (int i = 0; i < table.length; ++i) {
            table[i].reservedStatus();
        }
    }

    public void checkReservation(int time) {
        for (int i = 0; i < 20; ++i) {
            table[i].checkReservation(time);
        }
    }
}