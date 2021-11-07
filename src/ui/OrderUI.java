package ui;
import java.util.Scanner;

public class OrderUI{

    public void printOptions(){
         int choice,orderID;
         Scanner sc = new Scanner(System.in);

         System.out.println("");
         System.out.println("(1) Create order");
         System.out.println("(2) View Order");
         System.out.println("(3) Add items to Order");
         System.out.println("(4) Remove items from Order");
         System.out.println("(5) Checkout/Print Bill Invoice");
         System.out.println("(0) Exit");

        do {
            System.out.println("");
            System.out.printf("Enter your choice: ");
            
            choice = sc.nextInt();
            switch (choice) {
            
            case 1:
                orderID = Order.create();
                System.out.printf("Order created! ID = %d",orderID);
                break;
            case 2:
                System.out.println("Enter order ID");
                orderID = sc.nextInt();
                Order.printOrder(orderID);
                break;
            case 3:
                System.out.println("Enter order ID");
                orderID = sc.nextInt();
                Order.addItem(orderID); //how to add goes to order class
                break;
            case 4:
                System.out.println("Enter order ID");
                orderID = sc.nextInt();
                Order.removeItem(orderID);
                break;
            case 5:
                System.out.println("Enter order ID");
                orderID = sc.nextInt();
                Checkout(orderID); //how to use constructor ah?
            case 0:
                System.out.println("Going back ….");
            default:
                System.out.println("Invalid choice");
                break;
            }
        } while (choice);
    }
}