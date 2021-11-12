package ui;
import java.util.Scanner;

import objects.Discount;
import objects.Membership;
import java.util.ArrayList;
import java.util.LinkedHashMap;


import util.Data;

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
                Checkout(orderID);
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
        
    }

    //Case 1 method
    private void Checkout(String orderID){
        //need choose payment method? no assume cash
        Scanner sc = new Scanner(System.in);
        
        //read raw price before discount
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();

        tempArrayList = Data.readCSV("./Order.csv");
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        int i=0;
        String[] orderRows = tempMap.get("orderNO");
        while(i<orderRows.length) //no match and never reach end
            {
                if(orderRows[i] == orderID)
                    break;
                else ++i;
            }
        if(i == orderRows.length)
        {
            System.out.println("No such order found");
            return;
        }
        
        int tempsaleprice = Integer.parseInt(tempMap.get("saleprice")[i]);

        System.out.println("Is customer member? Y/N");
        if(sc.next().charAt(0) == 'Y')
        {
            Membership member = new Membership(true); //create simple obj to pass into overridable fn
            double discount = Discount.getDiscount(member); //callee fn returns discount in ratio 0-1. 0.4 discount means pay 0.6.
            discount = 1-discount; //get actual multiplier
            // get subtotal or total then discount?
            tempsaleprice = (int) ((float)tempsaleprice * discount); //round of to nearest int cents

        }
        //else no discount applied
        //tempsaleprice is final now
        System.out.printf("Total payment to receive: $%.2f", (float)tempsaleprice/100);
        //temporary test
        
        //sc.close();
    }
    //Case 2 method
    public void PrintInvoice(){ //receipt format
        //TODO printinvoice
        //table number from Yu Ze or maybe store in order csv as well
        //server
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();
        tempArrayList = Data.readCSV("../data/Order.csv");
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        int i=0;
        String[] orderRows = tempMap.get("orderNO");
        while(i<orderRows.length) //no match and never reach end
            {
                if(orderRows[i] == orderID)
                    break;
                else ++i;
            }
        if(i == orderRows.length)
            {
                System.out.println("No such order found");
                return;
            } 
        //timestamp is orderid
        //convert or just print?
        System.out.println(orderID);

        //item list with price at side
        String[] itemRows = tempMap.get("items");
        int j;
        for(j=0;j) //how to stop at commas? item1,item2
        {

        }
        
        //subtotal followed by discount then tax amt then total
        String subtotal = 
        System.out.println("Subtotal         ",); 
    }
    
}
