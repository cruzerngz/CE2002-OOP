package ui;
import java.util.Scanner;
import objects.Order;
import util.Colour;

public class OrderUI{

    public static void printOptions(){
         int choice;
         String orderID, itemID;
         String staffID, staffName;
         Scanner sc = new Scanner(System.in);

         System.out.println("");
         Colour.println(Colour.TEXT_BLUE, "Order Menu");
         Colour.println(Colour.TEXT_GREEN, "(1) Create order");
         Colour.println(Colour.TEXT_GREEN, "(2) View Order");
         Colour.println(Colour.TEXT_GREEN, "(3) Add items to Order");
         Colour.println(Colour.TEXT_GREEN, "(4) Remove items from Order");
         Colour.println(Colour.TEXT_GREEN, "(5) Checkout/Print Bill Invoice");
         Colour.println(Colour.TEXT_GREEN, "(0) Exit");

        do {
            System.out.println("");
            Colour.println(Colour.TEXT_GREEN, "Enter your choice: ");
            
            choice = sc.nextInt();
            switch (choice) {
            
            case 1:
                //debug
                Colour.println(Colour.TEXT_YELLOW, "Enter Staff ID:"); //temporarily use this method first
                staffID = sc.next();
                Colour.println(Colour.TEXT_YELLOW, "Enter Staff name:"); //temporarily use this method first
                staffName = sc.next();

                orderID = Order.create(staffID,staffName);
                System.out.printf("Order created! ID = %s",orderID);
                break;
            case 2:
                System.out.println("Enter order ID");
                orderID = sc.next();
                Order.printOrder(orderID);
                break;
            case 3:
                System.out.println("Enter order ID");
                orderID = sc.next();
                System.out.println("Enter item ID");
                itemID = sc.next();
                Order.addItem(orderID,itemID);
                break;
            case 4:
                System.out.println("Enter order ID");
                orderID = sc.next();
                System.out.println("Enter item ID");
                itemID = sc.next();
                Order.removeItem(orderID,itemID);
                break;
            case 5:
                System.out.println("Enter order ID");
                orderID = sc.next();
                CheckoutUI checkout = new CheckoutUI(orderID); //may change to static method later
                checkout.printOptions(); //pass to checkout ui
                break;
            case 0:
                System.out.println("Going back ….");
                break;
            default:
                System.out.println("Invalid choice");
                break;
            }
        } while (choice>0);
    }
}