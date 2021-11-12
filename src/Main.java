import java.io.FileNotFoundException;
import java.util.Scanner;

import ui.*;
import util.Colour;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println();
            Colour.println(Colour.TEXT_BLUE, "MAIN MENU");
            Colour.println(Colour.TEXT_GREEN, "(1) Menu Management");          //F.R. 1
            Colour.println(Colour.TEXT_GREEN, "(2) Promotions Management");    //F.R. 2
            Colour.println(Colour.TEXT_GREEN, "(3) Order Management");         //F.R. 3,4,5
            Colour.println(Colour.TEXT_GREEN, "(4) Reservation Management");   //F.R. 6,7,8
            Colour.println(Colour.TEXT_GREEN, "(5) Order checkout");           //F.R. 9
            Colour.println(Colour.TEXT_GREEN, "(6) Sales statistics");         //F.R. 10
            Colour.println(Colour.TEXT_GREEN, "(7) Admin");                    //Staff and
            Colour.println(Colour.TEXT_GREEN, "(0) Exit");                     //time manupulation
            System.out.println();
            System.out.printf("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3: 
                    OrderUI.printOptions(); //pass to orderui
                    break;
                case 4: 
                    Reservation.printOptions();
                    break;
                case 5:
                    
                    break;
                case 6: 
                    SaleStatsUI.main(args);
                    break;
                case 7:
                    // AdminUI.main(args);
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
