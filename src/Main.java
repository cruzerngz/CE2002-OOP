import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        

        //please rewrite all the options
        System.out.println("");
        System.out.println("(1) Menu Items Management");
        System.out.println("(2) Promotion Sets Management");
        System.out.println("(3) Managing Orders");
        System.out.println("(4) Managing Reservations");
        System.out.println("(5) Checkout/Print Bill Invoice");
        System.out.println("(6) Printing sales revenue report per day and for a range period");
        System.out.println("(7) Managing staff for the program");
        System.out.println("(8) Exit");
        
        do {
            System.out.println("");
            System.out.printf("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3: 
                    
                    break;
                case 4: 
                    
                    break;
                case 5:
                    
                    break;
                case 6: 
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    System.out.println("Program terminating ….");
            }
        } while (choice < 8);
    }
}
