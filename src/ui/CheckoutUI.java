package ui;
import java.util.Scanner;


import objects.Discount;
import objects.Membership;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import util.Colour;
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
                Checkout(orderID);
                break;
            case 2:
                PrintInvoice();
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
                if(orderRows[i].equals(orderID))
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
        
        //table number from Yu Ze or maybe store in order csv as well
        //server
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();
        tempArrayList = Data.readCSV("../data/Order.csv");
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        int i=0;
        String[] orderRows = tempMap.get("orderNO");
        while(i<orderRows.length) //no match and never reach end
            {
                if(orderRows[i].equals(orderID))
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

        //item list with price at side
        String[] itemRows = tempMap.get("items");
        String[] itemlist = itemRows[i].split("."); //each element is id
        
        ArrayList<String[]> menuArrayList = Data.readCSV("../data/menu.csv"); //read menu csv before enter loop
        tempMap = Data.parse(menuArrayList);

        String[] idRows = tempMap.get("id");
        String[] nameRows = tempMap.get("name");
        String[] priceRows = tempMap.get("price");

        int index;
        for(String item:itemlist) 
        {
            //look for id match
            for(index=0;index<idRows.length;++index)
            {
                if(item == idRows[index]) //string match
                    break;
            }
            
            System.out.printf("%s              $%s\n",nameRows[index],priceRows[index]);
        }
        
        //subtotal followed by discount then tax amt then total
        String subtotal = tempMap.get("Saleprice")[i]; //saleprice at row i
        String tax = tempMap.get("salesTax")[i];
        System.out.printf("Subtotal         $%s\n",subtotal); 
        System.out.printf("Tax          $%s\n",tax);
        float total = Float.parseFloat(subtotal) + Float.parseFloat(tax);
        System.out.printf("Total          %.2f",total);

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
