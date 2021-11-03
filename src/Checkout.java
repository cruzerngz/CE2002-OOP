import java.util.Scanner;

public class Checkout {
    private int orderID;
    
    public Checkout(int orderID) {
        this.orderID = orderID; //assigned from outside for use later       
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.println("(1) Checkout");
        System.out.println("(2) Reprint Invoice");
        System.out.println("(0) Back");
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
        sc.close();
    }

    //Case 1 method

    //Case 2 method

    //Case 3 method
}
