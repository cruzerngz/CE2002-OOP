package ui;

//import java.io.FileNotFoundException;
import java.util.Scanner;
import objects.Restaurant;
import util.*;

public class ReservationUI implements BaseUI{

    Restaurant res;

    public ReservationUI(Restaurant res){
        this.res = res;
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);
        //Restaurant res = new Restaurant();

        do {
            System.out.println("");
            Colour.println(Colour.TEXT_BLUE, "Reservation");
            Colour.println(Colour.TEXT_GREEN,"(1) Create a new reservation booking");
            Colour.println(Colour.TEXT_GREEN,"(2) Check for reservation booking");
            Colour.println(Colour.TEXT_GREEN,"(3) Remove reservation booking");
            Colour.println(Colour.TEXT_GREEN,"(4) List existing reservations");
            Colour.println(Colour.TEXT_GREEN,"(0) Back");

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
                sc.nextLine();
                if (res.checkFullReservation(date, time)){
                    break;
                }
                System.out.println("Table Number:");
                String tableNo = sc.nextLine();
                System.out.println("Customer's name:");
                String custName = sc.nextLine();
                System.out.println("Phone Number:");
                String phoneNo = sc.nextLine();
                System.out.println("Pax:");
                int pax = sc.nextInt();
                sc.nextLine();
                res.reserve(date, Integer.parseInt(tableNo), time, custName, Integer.parseInt(phoneNo), pax);
                break;
            case 2:
                System.out.println("_____Check for reservation booking_____");
                System.out.println("Enter date:");
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
                res.unreserveTable(date3, tableNo3, time3, true);
                break;

            case 4:
                do {
                    Colour.println(Colour.TEXT_BLUE,"_____List existing reservations_____");
                    Colour.println(Colour.TEXT_GREEN,"(1) List specific day");
                    Colour.println(Colour.TEXT_GREEN,"(2) List all");
                    Colour.println(Colour.TEXT_GREEN,"(0) Back");
                    System.out.println("");
                    System.out.printf("Enter your choice: ");
                    choice = sc.nextInt();
                    switch (choice) {
                    case 1:
                        System.out.println("Enter date:");
                        sc.nextLine();
                        String date4 = sc.nextLine();
                        res.showReservationStatus_Day(date4);
                        break;
                    case 2:
                        res.showReservationStatus_All();
                        break;
                    case 0:
                        System.out.println("Going back ...");
                    }
                } while (choice < 3 && choice > 0);
                choice = 4;
                break;

            case 0:
                System.out.println("Going back ...");
                break;
            default:
                break;

            }
        } while (choice != 0);
    }
}
