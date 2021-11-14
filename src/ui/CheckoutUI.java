package ui;
import java.util.Scanner;

import util.Colour;
import objects.Checkout;
/**
 * Interface or Menu for checkout functions, checkout and print invoice
 * @author Domi
 */
public class CheckoutUI implements BaseUI{
    private String orderID;
    
    /**
     * Constructor: Simple object created so it can store orderID until the end of use, passing it to other linked classes.
     * @param orderID Order to checkout
     */
    public CheckoutUI(String orderID) {
        this.orderID = orderID; //assigned from outside for use later
    }

    /**
     * Actual menu function that is invoked to 'execute' the class
     */
    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("");
            Colour.println(Colour.TEXT_BLUE, "Checkout Menu");
            Colour.println(Colour.TEXT_GREEN, "(1) Checkout");
            Colour.println(Colour.TEXT_GREEN, "(2) Reprint Invoice");
            Colour.println(Colour.TEXT_GREEN, "(0) Back");
            System.out.println("");
            Colour.println(Colour.TEXT_GREEN, "Enter your choice: ");
            
            choice = sc.nextInt();
            switch (choice) {
            //each of these cases call another method within this class
            case 1:
                Checkout.checkout(orderID);
                break;
            case 2:
                Checkout.printInvoice(orderID);
                break;
            case 0:
                Colour.println(Colour.TEXT_GREEN, "Going back ….");
                break;
            
            default:
                Colour.println(Colour.TEXT_GREEN, "Invalid choice");
                break;
            }
        } while (choice > 0);
        
    }

 
    
}
