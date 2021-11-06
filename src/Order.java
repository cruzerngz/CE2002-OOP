import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    //construct staff object
    //private Staff staff; //create staff class later
    //private int OrderID; //id to pass around
    
    public Order() {
        //staffid could be part of the constructor?
    }

    public static void create() {
        //creates a new order
        //how to generate new id?
        //can edit, can delete
        //leads to checkout
    }

    public static void printOrder(int orderID) {
        ArrayList<String[]> array = new ArrayList<String[]>(); 
        array = d.readCSV("Order.csv") //read csv
        
    }

    public static void addItem(int orderID) {
        //write to csv
        //append to list new item
    }

    public static void removeItem(int orderID) {
        //find item
        //if found remove item else return error
        //write to csv
    }

}
