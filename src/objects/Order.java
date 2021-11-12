package objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import util.Data;
import util.DateTime;

public class Order {
    //construct staff object
    //private Staff staff; //create staff class later
    //private int OrderID; //id to pass around
    
    public Order() {
        //staffid could be part of the constructor
    }
/**
 * 
 * @param emp_name
 * @param emp_id
 * @return orderid to caller
 */
    public static String create(String emp_name, String emp_id) {
        //creates a new order
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();
        tempArrayList = Data.readCSV("../data/Order.csv");
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);
        int i=0;
        String[] orderRows = tempMap.get("orderNO"); //get this column values as one array
        
        while( i<orderRows.length ) //for every row
        {
            
            if(orderRows[i] == null ) //index 1 is orderid field and is null
                break; //get out of loop with i pointing to which row we want to edit
            else ++i;
        }
        DateTime datetime = new DateTime();
        String id = datetime.getDateTime();
        orderRows[i] = id; //write to array
        tempMap.put("orderNO", orderRows); //WB to map

        String[] empRow = tempMap.get("emp_name"); //setting emp_name
        empRow[i] = emp_name; //write to array
        tempMap.put("emp_name", empRow);

        empRow = tempMap.get("emp_id"); //setting emp_id
        empRow[i] = emp_id; //write to array
        tempMap.put("emp_id", empRow);

        //TODO assign table

        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, "../data/Order.csv");
        return id;
    }

    public static void printOrder(String orderID) {
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();

        tempArrayList = Data.readCSV("../data/Order.csv");
        //read into arraylist
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
        // by here i is the row we want to print
        System.out.printf("%s: ", orderID);
        System.out.printf("%s ", tempMap.get("emp_name")[i] ); 
        System.out.printf("%s ", tempMap.get("emp_id")[i] ); 
        System.out.printf("%s ", tempMap.get("items")[i] ); 
    }

    public static void addItem(String orderID, String itemID) {
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
        
        //add item to arraylist and WB
        String[] itemRows = tempMap.get("items");
        itemRows[i] += itemID; 
        tempMap.put("items", itemRows);

        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, "../data/Order.csv");
    }

    public static void removeItem(String orderID, String itemID) {
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
        
        String[] itemRows = tempMap.get("items");
        itemRows[i].replace(itemID, ""); //replace with blank
        itemRows[i].replaceAll("..",".");
        tempMap.put("items", itemRows);
        
        //WB to csv
        tempArrayList = Data.parse(tempMap);

        Data.writeCSV(tempArrayList, "../data/Order.csv");

    }

}
