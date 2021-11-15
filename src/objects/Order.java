package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import util.Colour;
import util.Data;
import util.DateTime;
import util.Path;

/**
 * Contains methods to act on individual orders.
 * Order object must have certain variables defined in order to function, minimally orderID
 * @author Domi
 */
public class Order {
    private static String filePath = Path.order;
    private String emp_name, emp_id; //staff info
    private String orderID; //id to pass around
    
    /**
     * Constructor stores employee name, id and order number tied to the order.
     * @param emp_name
     * @param emp_id
     * @param orderID
     */
    public Order(String emp_name, String emp_id, String orderID) {
        this.emp_name = emp_name;
        this.emp_id = emp_id;
        this.orderID = orderID;
    }
    /**
     * Constructor overload for situations where staff info not needed
     * @param orderID
     */
    public Order(String orderID){
        emp_name = "";
        emp_id = "";
        this.orderID = orderID;
    }
    
/**
 * 
 * @param emp_name Staff name as required by order details
 * @param emp_id Staff id as required by order details
 * @return orderid to caller
 */
    public String create(String emp_name, String emp_id) {
        //creates a new order
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();
        tempArrayList = Data.readCSV(filePath);
        String[] temp = new String[tempArrayList.get(0).length];
        Arrays.fill(temp, "");
        tempArrayList.add(temp); //add empty row
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);

        String[] orderRows = tempMap.get("orderNO"); //get this column values as one array
        int index = orderRows.length - 1;
        
        DateTime datetime = new DateTime();
        String id = String.format("%1$4s",datetime.getTime()).replace(" ", "0");
        orderRows[index] = id; //write to array
        tempMap.put("orderNO", orderRows); //WB to map

        String[] empRow = tempMap.get("emp_name"); //setting emp_name
        empRow[index] = emp_name; //write to array
        tempMap.put("emp_name", empRow);

        String[] empIdRow = tempMap.get("emp_id"); //setting emp_id
        empIdRow[index] = emp_id; //write to array
        tempMap.put("emp_id", empIdRow);

        String[] paidRow = tempMap.get("paid");
        paidRow[index] = "false";
        tempMap.put("paid", paidRow);

        String[] priceRow = tempMap.get("saleprice");
        priceRow[index] = "0";
        tempMap.put("saleprice", priceRow);

        String[] taxRow = tempMap.get("salesTax");
        taxRow[index] = "0";
        tempMap.put("salesTax", taxRow);


        

        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, filePath);
        this.orderID = id; //store in object variable to initialize
        return id;
    }

    /**
     * Retrieves order details and prints just the list of items, staff name and id
     * @param orderID
     */
    public void printOrder(String orderID) {
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();

        tempArrayList = Data.readCSV(filePath);
        //read into arraylist
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
            Colour.println(Colour.TEXT_RED, "No such order found");
            return;
        }
        // by here i is the row we want to print
        System.out.printf("orderID: %s ", orderID);
        System.out.printf("Staff name: %s ", tempMap.get("emp_name")[i] ); 
        System.out.printf("Staff ID: %s ", tempMap.get("emp_id")[i] ); 
        System.out.printf("Items: %s\n", tempMap.get("items")[i] ); 
    }

    /**
     * Adds item to order.csv
     * @param orderID string to find row and appropriate order to add to
     * @param itemID actual item number code to be input into field
     */
    public void addItem(String orderID, String itemID) {
        ArrayList<String[]> tempArrayList = Data.readCSV(filePath);
        ArrayList<String[]> menuArr = Data.readCSV(Path.menu);
        Float salePrice = 0f;

        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        int i=0,j=0;
        String[] orderRows = tempMap.get("orderNO");
        while(i<orderRows.length) //no match and never reach end
            {
                if(orderRows[i].equals(orderID))
                    break;
                else ++i;
            }
        if(i == orderRows.length)
        {
            Colour.println(Colour.TEXT_RED, "No such order found");
            return;
        }
        
        //add item to arraylist and WB
        String[] itemRows = tempMap.get("items");
        

        //increment the sale price
        
        for(j=0; j<menuArr.size(); j++) {
            if(j==0) {continue;} //skip col
            if(menuArr.get(j)[0].equals(itemID)) {
                itemRows[i] += ("."+itemID); 
                itemRows[i] = itemRows[i].replaceAll("^\\.|\\.$", "").replaceAll("\\.\\.",".");
                tempMap.put("items", itemRows);
                salePrice += Float.parseFloat(menuArr.get(j)[2]);
                break;
            }
        }

        if(j==menuArr.size())
        {
            Colour.println(Colour.TEXT_RED, "No such item");
            return;
        }
        //add to order
        String[] sales = tempMap.get("saleprice");
        salePrice += Float.parseFloat(sales[i]);
        sales[i] = Float.toString(salePrice);
        //write to map
        tempMap.put("saleprice", sales);
        
        tempArrayList = Data.parse(tempMap);
        Data.writeCSV(tempArrayList, filePath);

        Colour.println(Colour.TEXT_CYAN, "Item added");
    }


    /**
     * Removes item from order.csv
     * @param orderID string to find row and appropriate order to add to
     * @param itemID actual item number code to be input into field. returns error if mismatch
     */
    public void removeItem(String orderID, String itemID) {
        ArrayList<String[]> tempArrayList = Data.readCSV(filePath);
        ArrayList<String[]> menuArr = Data.readCSV(Path.menu);
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        Float salePrice = 0f;
        int i=0,j=0;
        String[] orderRows = tempMap.get("orderNO");

        while(i<orderRows.length) //no match and never reach end
            {
                if(orderRows[i].equals(orderID)) {
                    // System.out.println(orderRows[i]);
                    break;
                }
                else i++;
            }
        if(i == orderRows.length)
        {
            Colour.println(Colour.TEXT_RED, "No such order found");
            return;
        }

        String[] itemRows = tempMap.get("items");
        itemRows[i] = itemRows[i].replaceFirst(itemID, ""); //replace with blank

        //remove outlier dots
        itemRows[i] = itemRows[i].replaceAll("^\\.|\\.$", "").replaceAll("\\.\\.",".");
        tempMap.put("items", itemRows);
        //nothing will happen if nonexistent

        //get the sale price
        for(j=0; j<menuArr.size(); j++) {
            if(j==0) {continue;} //skip col
            if(menuArr.get(j)[0].equals(itemID)) {
                salePrice = salePrice - Float.parseFloat(menuArr.get(j)[2]);
                break;
            }
        }
        
        if(j==menuArr.size())
        {
            Colour.println(Colour.TEXT_RED, "No such item");
            return;
        }


        //subtract from order
        String[] sales = tempMap.get("saleprice");
        salePrice = Float.parseFloat(sales[i]) + salePrice;
        sales[i] = Float.toString(salePrice);
        //write to map
        tempMap.put("saleprice", sales);
        
            
        tempArrayList = Data.parse(tempMap);
        Data.writeCSV(tempArrayList, filePath);
        
        //WB to csv
        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, filePath);
        Colour.println(Colour.TEXT_CYAN, "Item Removed");
    }

}
