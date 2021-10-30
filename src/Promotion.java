
import java.util.Scanner;

public class Promotion {
    public Promotion() {
        
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.println("(1) Print existing promotions");
        System.out.println("(2) Create a new promotion set");
        System.out.println("(3) Update an existing promotion");
        System.out.println("(4) Delete a promotions");
        System.out.println("(5) Back");
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

                break;
            case 4:

                break;
            case 5:
                System.out.println("Going back ….");
            }
        } while (choice < 5);
    }

    //Case 1 method

    //Case 2 method

    //Case 3 method

    //Case 4 method
}
