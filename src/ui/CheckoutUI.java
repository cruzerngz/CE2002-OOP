package ui;
import java.util.Scanner;

import javax.management.remote.SubjectDelegationPermission;

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

        do {
            System.out.println("");
            System.out.println("(1) Checkout");
            System.out.println("(2) Reprint Invoice");
            System.out.println("(0) Back");
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
        //Assume cash
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
        
        //sanity check, is already paid?
        if(isPaid(i)) 
        {
            System.out.println("Already paid!");
            return;
        }

        String[] salepriceRows = tempMap.get("saleprice");
        float tempsaleprice = Float.parseFloat(salepriceRows[i]);

        System.out.println("Is customer member? Y/N");
        if(sc.next().charAt(0) == 'Y')
        {
            Membership member = new Membership(true); //create simple obj to pass into overridable fn
            float discount = Discount.getDiscount(member); //callee fn returns discount in ratio 0-1. 0.4 discount means pay 0.6.
            discount = 1-discount; //get actual multiplier
            // get subtotal or total then discount?
            tempsaleprice = tempsaleprice * discount; //round off to nearest int cents
            tempMap.put("saleprice", salepriceRows); //tempsaleprice is final now, WB to order.csv
            //discount tax separately for ease
            salepriceRows[i] = String.valueOf(tempsaleprice);
            String[] salesTaxRows = tempMap.get("salesTax");
            float tempsalesTax = Float.parseFloat(salesTaxRows[i]);
            tempsalesTax = tempsalesTax * discount;
            //WB new tax value
            salesTaxRows[i] = String.valueOf(tempsalesTax);
            tempMap.put("salesTax", salesTaxRows);
            
            tempArrayList = Data.parse(tempMap);
            Data.writeCSV(tempArrayList, "../data/Order.csv");
        }
        //else no discount applied, no need edit csv

       
        System.out.printf("Total payment to receive: $%.2f", tempsaleprice);
        //temporary test
        
        
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
        
        //sanity check
        if(!isPaid(i))
        {
            System.out.println("Please pay before invoice");
        }
        //timestamp is orderid
        //convert or just print?
        System.out.println(orderID);

        //TODO item list with price at side
        String[] itemRows = tempMap.get("items");
        int j;
        for(j=0;j<;) //how to stop at commas? item1,item2
        {

        }
        
        //subtotal followed by discount then tax amt then total
        String subtotal = tempMap.get("Saleprice")[i]; //saleprice at row i
        System.out.printf("Subtotal         $%s",subtotal); 
        System.out.printf("Tax        $%s",tax);
    }

    private boolean isPaid(int index){
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();

        tempArrayList = Data.readCSV("./Order.csv");
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        String[] paid = tempMap.get("paid");
        if(paid[index] == "TRUE")
            return true;
        else
            return false;
    }
    
}
