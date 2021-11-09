package ui;
import java.util.Scanner;

import objects.Discount;
import objects.Membership;

public class CheckoutUI {
    private String orderID;
    
    public CheckoutUI(String orderID) {
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
                Checkout();
                break;
            case 2:
                PrintInvoice();
                break;
            case 0:
                System.out.println("Going back ….");
                break;
            
            default:
                System.out.println("Invalid choice");
                break;
            }
        } while (choice > 0);
        sc.close();
    }

    //Case 1 method
    private void Checkout(){
        //need choose payment method? no assume cash
        Scanner sc = new Scanner(System.in);
        System.out.println("Is customer member? Y/N");
        if(sc.next().charAt(0) == 'Y')
        {
            Membership member = new Membership(true); //create simple obj to pass into overridable fn
            double discount = Discount.getDiscount(member); //callee fn returns discount in ratio 0-1. 0.4 discount means pay 0.6.
            // get subtotal or total then discount?

        }
        sc.close();
    }
    //Case 2 method
    private void PrintInvoice(){

    }
    //Case 3 method
}
