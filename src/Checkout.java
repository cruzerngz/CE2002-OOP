

import java.util.Scanner;

public class Checkout {
    public Checkout() {
        
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.println("(1) Checkout");
        System.out.println("(2) Reprint Invoice");
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
}
