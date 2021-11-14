import java.io.FileNotFoundException;
import java.util.Scanner;
import objects.*;

import ui.*;
import util.Colour;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        Restaurant res = new Restaurant();

        Colour.println(Colour.TEXT_YELLOW, "Enter Staff name:");
        String staffName = sc.next(); 
        Staff activeStaff = new Staff(staffName);
        activeStaff.makeActive();

        do {
            System.out.println("\n" + System.getProperty("user.dir"));
            System.out.println();
            Colour.println(Colour.TEXT_BLUE, "MAIN MENU");
            Colour.println(Colour.TEXT_GREEN, "(1) Menu Management");          //F.R. 1
            Colour.println(Colour.TEXT_GREEN, "(2) Promotions Management");    //F.R. 2
            Colour.println(Colour.TEXT_GREEN, "(3) Order Management");         //F.R. 3,4,5
            Colour.println(Colour.TEXT_GREEN, "(4) Reservation Management");   //F.R. 6,7,8
            // Colour.println(Colour.TEXT_GREEN, "(5) Order checkout");           //F.R. 9
            Colour.println(Colour.TEXT_GREEN, "(5) Sales statistics");         //F.R. 10
            Colour.println(Colour.TEXT_GREEN, "(6) Admin");                    //Staff and
            Colour.println(Colour.TEXT_GREEN, "(0) Exit");                     //time manupulation
            System.out.println();
            System.out.printf("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    MenuSetting menusetting = new MenuSetting();
                    menusetting.printOptions();
                    break;
                case 2:
                    PmenuUI pmenuui = new PmenuUI();
                    pmenuui.printOptions();
                    break;
                case 3: 
                    OrderUI orderUI = new OrderUI(res, staffName, staffID);
                    orderUI.printOptions(); //pass to orderui
                    break;
                case 4: 
                    Reservation reservation = new Reservation(res);
                    reservation.printOptions();
                    break;
                // case 5:
                //     // CheckoutUI checkoutui = new CheckoutUI(orderID);

                //     break;
                case 5: 
                    SaleStatsUI saleStatsUI = new SaleStatsUI();
                    saleStatsUI.printOptions();
                    break;
                case 6:
                    AdminUI adminUI = new AdminUI();
                    adminUI.printOptions();
                    break;
                case 0:
                    System.out.println("Program terminating ...");
                    break;
                default:
                    break;
            }
        } while (choice != 0);
        sc.close();
    }
}
