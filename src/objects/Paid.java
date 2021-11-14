package objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import util.Data;
import util.Path;
/**
 * Utility class to check if an order has been paid for and also set the variable to true
 * @author Domi
 */
public class Paid {
    public static String filePath = Path.order;
    /**
     * Checks whether order has been checked out before
     * @param index taken from function like checkout, index must refer to row in hashmap
     * Must be called by function which has read csv file and knows which location to check
     * @return boolean value as named
     */
    public static boolean isPaid(int index){
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();
        tempArrayList = Data.readCSV(filePath);
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);

        String[] paid = tempMap.get("paid");
        if(paid[index].equals("TRUE"))
            return true;
        else
            return false;
    }
    
    
    /**
     * Used to checkout an order in the csv file by setting to TRUE
     * @param index taken from function like checkout, index must refer to row in hashmap
     */
    public static void setPaid(int index){
        ArrayList<String[]> tempArrayList = new ArrayList<String[]>();
        tempArrayList = Data.readCSV(filePath);
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArrayList);

        String[] paid = tempMap.get("paid");
        paid[index] = "TRUE";
        tempMap.put("paid", paid);
        Data.writeCSV(Data.parse(tempMap), Path.order);
    }
}
