
import java.util.Scanner;

public class RevenueReport {
    public RevenueReport() {
        
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.println("(1) Generate for a day");
        System.out.println("(2) Generate for a period");
        System.out.println("(3) Back");
        do {
            System.out.println("");
            System.out.printf("Enter your choice: ");
            
            choice = sc.nextInt();
            switch (choice) {
            //each of these cases call another method within this class
            case 1:

                break;
            case 2:

                break;
            case 3:
                System.out.println("Going back ….");
            }
        } while (choice < 3);
    }

    //Case 1 method

    //Case 2 method

    //Case 3 method

    //Case 4 method
}
