//this file is just here for testing purposes, can delete later on.
//import Menu.menuSetting;

import java.time.LocalDate; // import the LocalDate class

public class ZTest {
    public static void main(String[] args) {
        LocalDate myObj = LocalDate.now(); // Create a date object
        String todayDate = new String(myObj.toString());

        Seating seat = new Seating("369");  
        // seat.showNumEmptyTable();
        // seat.assignTable(5, 10001);
        // seat.assignTable(7, 10002);
        // seat.assignTable(9, 10003);
        // seat.assignTable(10, 10004);
        // seat.showAssignedTable();

        // seat.reserveTable(2, 91278908, 1500, "Yu Ze");
        // seat.reserveTable(2, 91274562, 1800, "John");
        // seat.showReservationStatus();
    
        // seat.reserveTable(2, 95687953, 1500, "Joan");

        // seat.unreserveTable(2, 1500);
        // seat.reserveTable(2, 95687953, 1500, "Joan");


        System.out.println(myObj); // Display the current date
        System.out.println("today is "+todayDate); // Display the current date
        System.out.println("today is "+todayDate.substring(8,10));
        System.out.println(seat.getDate());
    }
}