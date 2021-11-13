package ui;
import java.util.Scanner;
import objects.*;
import util.*;

/**
 * Interface / Menu for managing orders
 */
public class OrderUI{
    /**
     * Actual menu function to 'execute' class and access functions
     * It passes in dummy variables to Order objects when it is not needed
     * In the event it becomes needed due to change in Order methods, the variable can be passed from here
     */
    Restaurant res;

    /**
     * time is the current time and date
     */
    DateTime dt = new DateTime();
    private int time = dt.getTime();
    private String date = dt.getDate().toString();

    public OrderUI(Restaurant res){
        this.res = res;
    }

    public void printOptions(){
         int choice;
         String orderID, itemID;
         String staffID, staffName;
         Order tempOrder;
         
         Scanner sc = new Scanner(System.in);

        do {
            System.out.println();
            Colour.println(Colour.TEXT_BLUE, "Order Menu");
            Colour.println(Colour.TEXT_GREEN, "(1) Create order");
            Colour.println(Colour.TEXT_GREEN, "(2) View Order");
            Colour.println(Colour.TEXT_GREEN, "(3) Add items to Order");
            Colour.println(Colour.TEXT_GREEN, "(4) Remove items from Order");
            Colour.println(Colour.TEXT_GREEN, "(5) Checkout/Print Bill Invoice");
            Colour.println(Colour.TEXT_GREEN, "(0) Exit");
            System.out.println("");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch (choice) {
            
            case 1:
                //debug
                Colour.println(Colour.TEXT_YELLOW, "Enter Staff ID:"); //temporarily use this method first
                staffID = sc.next();
                Colour.println(Colour.TEXT_YELLOW, "Enter Staff name:"); //temporarily use this method first
                staffName = sc.next(); 
                orderID = null; //initialize to null as invalid id now, for compiler.
                tempOrder = new Order(staffName, staffID, orderID); //pass in dummy orderID
                orderID = tempOrder.create(staffID,staffName); //get actual orderID

                res.checkReservation(date, time); //prints reserved tables
                res.showEmptyTable(date); //prints empty tables (including reserved but empty tables)
                System.out.println("Assign a table:");
                int tableNo = sc.nextInt();
                res.assignTable(date, tableNo, Integer.parseInt(orderID));

                System.out.printf("Order created! ID = %s\n",orderID);
                break;
            case 2:
                System.out.println("Enter order ID");
                orderID = sc.next();
                tempOrder = new Order(orderID);
                tempOrder.printOrder(orderID);
                break;
            case 3:
                System.out.println("Enter order ID");
                orderID = sc.next();
                System.out.println("Enter item ID");
                itemID = sc.next();
                tempOrder = new Order(orderID); 
                tempOrder.addItem(orderID,itemID);
                break;
            case 4:
                System.out.println("Enter order ID");
                orderID = sc.next();
                System.out.println("Enter item ID");
                itemID = sc.next();
                tempOrder = new Order(orderID); 
                tempOrder.removeItem(orderID,itemID);
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