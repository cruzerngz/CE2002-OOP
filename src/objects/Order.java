package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import util.Data;
import util.DateTime;

/**
 * Contains methods to act on individual orders.
 * Order object must have certain variables defined in order to function, minimally orderID
 * @author Domi
 */
public class Order {
    
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
        tempArrayList = Data.readCSV("../data/Order.csv");
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

        empRow = tempMap.get("emp_id"); //setting emp_id
        empRow[index] = emp_id; //write to array
        tempMap.put("emp_id", empRow);

        //TODO assign table
        

        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, "../data/Order.csv");
        this.orderID = id; //store in object variable to initialize
        return id;
    }

    /**
     * Retrieves order details and prints just the list of items, staff name and id
     * @param orderID
     */
    public void printOrder(String orderID) {
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();

        tempArrayList = Data.readCSV("../data/Order.csv");
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
            System.out.println("No such order found");
            return;
        }
        // by here i is the row we want to print
        System.out.printf("%s: ", orderID);
        System.out.printf("%s ", tempMap.get("emp_name")[i] ); 
        System.out.printf("%s ", tempMap.get("emp_id")[i] ); 
        System.out.printf("%s \n", tempMap.get("items")[i] ); 
    }

    /**
     * Adds item to order.csv
     * @param orderID string to find row and appropriate order to add to
     * @param itemID actual item number code to be input into field
     */
    public void addItem(String orderID, String itemID) {
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
        
        //add item to arraylist and WB
        String[] itemRows = tempMap.get("items");
        itemRows[i] += (itemID + '.'); 
        tempMap.put("items", itemRows);

        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, "../data/Order.csv");
    }


    /**
     * Removes item from order.csv
     * @param orderID string to find row and appropriate order to add to
     * @param itemID actual item number code to be input into field. returns error if mismatch
     */
    public void removeItem(String orderID, String itemID) {
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
        
        String[] itemRows = tempMap.get("items");
        itemRows[i].replace(itemID, ""); //replace with blank

        //remove outlier dots
        itemRows[i] = itemRows[i].replaceAll("^\\.|\\.$", "").replaceAll("\\.\\.",".");;         
        tempMap.put("items", itemRows);
        
        //WB to csv
        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, "../data/Order.csv");

    }

}
