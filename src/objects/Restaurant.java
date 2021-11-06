package objects;
import util.DateTime;
import java.util.HashMap;


public class Restaurant {

    // private Seating[] seating = new Seating[7];
    private HashMap<String, Seating> seating = new HashMap<String, Seating>();
    DateTime dt = new DateTime();

    public Restaurant() {
        String date = new String();
        // loop creates a series of "Seatings" one week into the future
        for (int i = 0; i < 7; ++i) {
            date = dt.getDate().toString();
            seating.put(date, new Seating(date));
            // seating[i] = new Seating(date);
            dt.deltaDay(1);
        }

        
    }



    public void printShits() {
        System.out.println(dt.getTime());
    }

    public void reserve(String date, int tableNo, int time, String cust_name, int phoneNo) {
        //this whole chunk simply checks whether the date given falls within the next 7 days
        // System.out.print(date);
        dt.reset();
        String today = dt.getDate().toString();
        //System.out.print(today);
        dt.deltaDay(1);
        String tmr = dt.getDate().toString();
        //System.out.print(tmr);
        dt.deltaDay(1);
        String tmr2 = dt.getDate().toString();
        //System.out.print(tmr2);
        dt.deltaDay(1);
        String tmr3 = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr4 = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr5 = dt.getDate().toString();
        dt.deltaDay(1);
        String tmr6 = dt.getDate().toString();
        dt.deltaDay(1);
        if (!(date.equals(today) || date.equals(tmr) || date.equals(tmr2) || date.equals(tmr3) || date.equals(tmr4) || date.equals(tmr5) || date.equals(tmr6))){
            System.out.println("Reservation can only be made 1 week in advance!");
        }
        else if(date.equals(today) && dt.getTime()>time){
            System.out.println("The time has passed!");
        }
        else{
            seating.get(date).reserveTable(tableNo, time, cust_name, phoneNo);
        }
    }


}
