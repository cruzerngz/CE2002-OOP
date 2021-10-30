import java.util.Scanner;

public class Menu {

    public Menu() {
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.println("(1) Print existing menu");
        System.out.println("(2) Create a new menu item");
        System.out.println("(3) Edit an existing menu item’s details");
        System.out.println("(4) Delete a menu item");
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
