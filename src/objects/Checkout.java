package objects;

import objects.Discount;
import objects.Membership;
import objects.SaleStats;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import util.Data;
import util.DateTime;
import util.Path;

/**
 * Contains static methods to act on orderID.
 * Checkout contains 2 functions 
 */
public class Checkout {
    private static String orderPath = Path.order;
    private static String menuPath = Path.menu;
       
        /**
         * Calculate sum total to be paid by customer, assumes staff will definitely collect money.
         * Immediately sets paid status to true upon executing
         * @param orderID which is used in order.csv to identify a customer
         */
       public static void checkout(String orderID){
        //Assume cash
        Scanner sc = new Scanner(System.in);
        
        //read raw price before discount
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();

        tempArrayList = Data.readCSV(orderPath);
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        int i=0;
        String[] orderRows = tempMap.get("orderNO");
        //get the index for order in Order.csv
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
        if(Paid.isPaid(i)) 
        {
            System.out.println("Already paid!");
            return;
        }
        
        String[] salepriceRows = tempMap.get("saleprice");
        float tempsaleprice = Float.parseFloat(salepriceRows[i]); //calculate saleprice


        System.out.println("Is customer member? Y/N");
        if(sc.next().charAt(0) == 'Y')
        {
            Membership member = new Membership(true); 
            MemberDiscount memberDiscount = new MemberDiscount(); //instantiate object just to call method
            float discount = memberDiscount.getDiscount(member); //callee fn returns discount in ratio 0-1. 0.4 discount means pay 0.6.
            discount = 1-discount; //get actual multiplier
            
            // get subtotal or total then discount?
            tempsaleprice = tempsaleprice * discount; 
            tempMap.put("saleprice", salepriceRows); //tempsaleprice is final now, WB to order.csv
            
            //discount tax separately for ease
            salepriceRows[i] = String.valueOf(tempsaleprice);
            String[] salesTaxRows = tempMap.get("salesTax");
            float tempsalesTax = tempsaleprice * 0.17f;
            tempsalesTax = tempsalesTax * discount;
            
            //WB new tax value
            salesTaxRows[i] = String.valueOf(tempsalesTax);
            tempMap.put("salesTax", salesTaxRows);
            
            tempArrayList = Data.parse(tempMap);
            Data.writeCSV(tempArrayList, orderPath);
        }
        //else no discount applied, no need edit csv
        //set paid
        //add to revenue stats
        SaleStats stats = new SaleStats();
        DateTime dt = new DateTime();
        stats.addRevenue(dt.getDaysSinceEpoch(), tempsaleprice);
       
        System.out.printf("Total payment to receive: $%.2f", tempsaleprice);
        //temporary test
        
        Paid.setPaid(i);
    }
    

    /**
     * Method to print invoice/receipt for customer. 
     * Will check if order has been successfully checked out before proceeding.
     * @param orderID String to search order csv
     */
    public static void printInvoice(String orderID){ //receipt format
        
        
        //server
        ArrayList<String[]> tempArrayList = Data.readCSV(orderPath);
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
        if(!Paid.isPaid(i))
        {
            System.out.println("Please pay before invoice");
            return;
        }
        //timestamp is orderid
        //convert or just print?
        //System.out.println(orderID);

        //item list with price at side
        String[] itemRows = tempMap.get("items");
        String[] itemlist = itemRows[i].split("\\."); //each element is id
        

        ArrayList<String[]> menuArrayList = Data.readCSV(menuPath); //read menu csv before enter loop
        LinkedHashMap<String, String[]> tempMap2 = Data.parse(menuArrayList);

        String[] idRows = tempMap2.get("id");
        String[] nameRows = tempMap2.get("name");
        String[] priceRows = tempMap2.get("price");

        int index;
        for(int j=0;j<itemlist.length;++j) 
        {
            //look for id match
            for(index=0;index<idRows.length;++index)
            {
                if(idRows[index].equals(idRows[j])) //string match
                    break;
            }
            
            System.out.printf("%s              $%s\n",nameRows[index],priceRows[index]);
        }
        
        //subtotal followed by discount then tax amt then total
        String subtotal = tempMap.get("saleprice")[i]; //saleprice at row i error here
        String tax = tempMap.get("salesTax")[i];
        System.out.printf("Subtotal         $%s\n",subtotal); 
        System.out.printf("Tax          $%s\n",tax);
        float total = Float.parseFloat(subtotal) + Float.parseFloat(tax);
        System.out.printf("Total          %.2f",total);

    }


}
