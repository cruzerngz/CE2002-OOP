import util.*;

import java.util.ArrayList;
import java.util.Scanner;

import ui.*;
import objects.*;
import util.*;


public class Debug2 {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);

        String orderID = "sat 21/10/2021";
        //byte[] temp = orderID;


        float money = 100f;
        SaleStats stats = new SaleStats();
        DateTime dt = new DateTime();
        stats.addRevenue(dt.getDaysSinceEpoch(), money);

    }

}

//for i in array:
//    do sth with i