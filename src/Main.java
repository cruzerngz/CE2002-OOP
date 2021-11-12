import java.util.Scanner;

import ui.*;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        System.out.println();
        System.out.println("(1) Menu Management");          //F.R. 1
        System.out.println("(2) Promotions Management");    //F.R. 2
        System.out.println("(3) Order Management");         //F.R. 3,4,5
        System.out.println("(4) Reservation Management");   //F.R. 6,7,8
        System.out.println("(5) Order checkout");           //F.R. 9
        System.out.println("(6) Sales statistics");         //F.R. 10
        System.out.println("(7) Admin");                    //Staff and
        System.out.println("(0) Exit");                     //time manupulation
        
        do {
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
                    
                    break;
                case 5:
                    
                    break;
                case 6: 
                    SaleStatsUI.main(args);
                    break;
                case 7:
                    
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
