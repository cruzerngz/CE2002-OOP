package ui;

import java.util.Scanner;
import objects.SaleStats;
import util.DateTime;
import util.Data;

public class SaleStatsUI {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println();
            System.out.println("(1) Sales statistics today");
            System.out.println("(2) Sales statistics by day");
            System.out.println("(3) Sales statistics by month");
            System.out.println("(4) View all statistics");
            System.out.println("(0) Return");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch(choice) {
                case 1: 
                    statsNow();
                    break;
                case 2:
                    statsDay();
                    break;
                case 3:
                    statsMth();
                    break;
                case 4:
                    statsAll();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while(choice != 0);
    }

    public static void statsNow() {
        float amount;
        DateTime dt = new DateTime("../data");
        SaleStats stats = new SaleStats();

        amount = stats.dayRevenue(dt.getDaysSinceEpoch());
        System.out.printf("Sales for %s is $%.2f\n", dt.getDayDate(), amount);
    }

    public static void statsDay() {
        int y, m, d;
        String date;
        float amount;
        DateTime dt = new DateTime("../data");
        SaleStats stats = new SaleStats();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter year:  ");
        y = sc.nextInt();
        System.out.print("\nEnter month: ");
        m = sc.nextInt();
        System.out.print("\nEnter day: ");
        d = sc.nextInt();
        System.out.println();

        date = dt.daysToDayDate(dt.ymdToEpochDay(y, m, d));
        //get the day
        amount = stats.dayRevenue(
            dt.ymdToEpochDay(y, m, d)
        );
        System.out.printf("Sales for %s is $%.2f\n", date, amount);
    }

    public static void statsMth() {
        int y, m, mStart, mEnd;
        String date;
        float amount;
        DateTime dt = new DateTime("../data");
        SaleStats stats = new SaleStats();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter year:  ");
        y = sc.nextInt();
        System.out.print("\nEnter month: ");
        m = sc.nextInt();
        System.out.println();
        
        mStart = dt.ymdToEpochDay(y, m, 1);
        if(m == 12) { //if end of year
            m = 0; y++;
        }
        mEnd = dt.ymdToEpochDay(y, m+1, 1) - 1;
        amount = stats.rangeRevenue(mStart, mEnd);
        date = dt.daysToMonthYear(mEnd);

        System.out.printf("Sales for %s is $%.2f\n", date, amount);

    }

    public static void statsAll() {
        SaleStats stats = new SaleStats();
        Data.printArrayList(stats.getPrintMatrix());
    }


}
