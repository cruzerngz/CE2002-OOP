package objects;
import java.util.ArrayList;
import java.util.Scanner;

import util.Data;
import util.DateTime;

public class Order {
    //construct staff object
    //private Staff staff; //create staff class later
    //private int OrderID; //id to pass around
    
    public Order() {
        //staffid could be part of the constructor
    }

    public static int create() {
        //creates a new order
        ArrayList<String[]> temp = Data.readCSV("./Order.txt"); //how to put filepath?
        int i=0;
        while( i<temp.size() )
        {
            if(temp[i][1] == 0) //index 1 is orderid field and is null
                break; //get out of loop with i pointing to which row we want to edit
            else ++i;
        }
        String id = DateTime.getDateTime();
        temp[i][1] = id; //write to array
        Data.writeCSV(temp, Order.csv);
        return id;
    }

    public static void printOrder(Integer orderID) {
        ArrayList<String[]> temp = Data.readCSV("./Order.csv"); //read into arraylist
        int i=0;
        while(temp[i][1] != orderID.toString() && i<temp.size() ) //no match and never reach end
            ++i;
        if( i==temp.size() )
        {
            System.out.println("No such order found");
            return;
        }
        // by here i is the row we want to print
        System.out.println(temp[i]); //print string array

    }

    public static void addItem(int orderID) {
        ArrayList<String[]> temp = Data.readCSV(./Order.csv);
        int i=0;
        while(temp[i][1] != orderID.toString() && i<temp.size() ) //no match and never reach end
            ++i;
        if( i==temp.size() )
        {
            System.out.println("No such order found");
            return;
        }
        System.out.println("What item are you adding?");
        Scanner sc = new Scanner(System.in);
        String itemID = sc.next();
        sc.close();
        //add item to arraylist and WB
        temp[i][4] ;//items column how to append?

        Data.writeCSV(temp, "Order.csv");
    }

    public static void removeItem(int orderID) {
        ArrayList<String[]> temp = Data.readCSV(./Order.csv);
        int i=0;
        while(temp[i][1] != orderID.toString() && i<temp.size() ) //no match and never reach end
            ++i;
        if( i==temp.size() )
        {
            System.out.println("No such order found");
            return;
        }
        System.out.println("What item are you removing?");
        Scanner sc = new Scanner(System.in);
        String itemID = sc.nextLine();
        //look for item in that index and remove
        //how to access the particular string and search?

        //WB to csv
        Data.writeCSV(temp, "./Order.csv");
    }

}
