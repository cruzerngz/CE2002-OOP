package ui;

import java.io.FileNotFoundException;
import java.util.Scanner;
import objects.Restaurant;

public class Reservation {

    public static void printOptions() throws FileNotFoundException {
        int choice;
        Scanner sc = new Scanner(System.in);
        Restaurant res = new Restaurant();

        do {
            System.out.println("");
            System.out.println("(1) Create a new reservation booking");
            System.out.println("(2) Check for reservation booking");
            System.out.println("(3) Remove reservation booking");
            System.out.println("(4) List existing reservations");
            System.out.println("(0) Back");

            System.out.println("");
            System.out.printf("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); //after nextAnything add a nextLine
            switch (choice) {
            // each of these cases call another method within this class
            case 1:
                System.out.println("_____Create a new reservation booking_____");
                System.out.println("Enter date:");
                String date = sc.nextLine();
                System.out.println("Time:");
                int time = sc.nextInt();
                System.out.println("Table Number:");
                int tableNo = sc.nextInt();
                System.out.println("Customer's name:");
                String custName = sc.nextLine();
                System.out.println("Phone Number:");
                int phoneNo = sc.nextInt();
                res.reserve(date, tableNo, time, custName, phoneNo);
                break;
            case 2:
                System.out.println("_____Check for reservation booking_____");
                System.out.println("Enter date:");
                // while(!sc.hasNextLine()){}
                String date2 = sc.nextLine();
                System.out.println("Time:");
                int time2 = sc.nextInt();
                res.checkReservation(date2, time2);
                break;
            case 3:
                System.out.println("_____Remove reservation booking_____");
                System.out.println("Enter date:");
                String date3 = sc.nextLine();
                System.out.println("Time:");
                int time3 = sc.nextInt();
                System.out.println("Table Number:");
                int tableNo3 = sc.nextInt();
                res.unreserveTable(date3, tableNo3, time3);
                break;
            case 4:
                System.out.println("_____List existing reservations_____");
                System.out.println("(1) List specific day");
                System.out.println("(2) List all");
                System.out.println("(0) Back");
                do {
                    System.out.println("");
                    System.out.printf("Enter your choice: ");
                    choice = sc.nextInt();
                    switch (choice) {
                    case 1:
                        System.out.println("Enter date:");
                        String date4 = sc.nextLine();
                        res.showReservationStatus_Day(date4);
                        break;
                    case 2:
                        res.showReservationStatus_All();
                        break;
                    case 0:
                        System.out.println("Going back ….");
                    }
                } while (choice < 3 && choice > 0);
                break;
            case 0:
                System.out.println("Going back ….");
            }
        } while (choice < 5 && choice >= 0);
        sc.close();
    }

    // Case 1 method

    // Case 2 method

    // Case 3 method

    // Case 4 method
}
