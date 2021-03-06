package ui;

import java.util.ArrayList;
import java.util.Scanner;
import objects.SaleStats;
import util.DateTime;
import util.Path;
import util.Colour;
import util.Data;

/**
 * UI handling sale statistics. 
 * Collection of static methods
 * @author cruzerngz
 */
public class SaleStatsUI implements BaseUI{

    /**
     * Main method when entering UI
     */
    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println();
            Colour.println(Colour.TEXT_BLUE, "SALE STATISTICS");
            Colour.println(Colour.TEXT_GREEN, "(1) Sales statistics today");
            Colour.println(Colour.TEXT_GREEN, "(2) Sales statistics by day");
            Colour.println(Colour.TEXT_GREEN, "(3) Sales statistics by month");
            Colour.println(Colour.TEXT_GREEN, "(4) View all records");
            Colour.println(Colour.TEXT_GREEN, "(0) Return");
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

    /**
     * Show stats on the current day
     */
    public static void statsNow() {
        ArrayList<String[]> table;
        DateTime dt = new DateTime();
        SaleStats stats = new SaleStats();

        table = stats.dayRevenue(dt.getDaysSinceEpoch());
        if(table == null) {
            System.out.printf(Colour.Red("No sales data found for %s\n"), dt.getDayDate());
        } else {
            System.out.printf("Sales for %s:\n", dt.getDayDate());
            Data.printArrayList(table);
        }
    }

    /**
     * Show stats for a specific day
     */
    public static void statsDay() {
        int y, m, d;
        String date;
        ArrayList<String[]> table;
        DateTime dt = new DateTime();
        SaleStats stats = new SaleStats();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter year: ");
        y = sc.nextInt();
        System.out.print("\nEnter month: ");
        m = sc.nextInt();
        System.out.print("\nEnter day: ");
        d = sc.nextInt();
        System.out.println();

        date = dt.daysToDayDate(dt.ymdToEpochDay(y, m, d));
        //get the day
        table = stats.dayRevenue(
            dt.ymdToEpochDay(y, m, d)
        );

        if(table == null) {
            System.out.printf(Colour.Red("No sales data found for %s\n"), date);
        } else {
            System.out.printf("Sales for %s:\n", date);
            Data.printArrayList(table);
        }

    }

    /**
     * Show stats for a specific month
     */
    public static void statsMth() {
        int y, m, mStart, mEnd;
        String date;
        ArrayList<String[]> table;
        DateTime dt = new DateTime();
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
        table = stats.rangeRevenue(mStart, mEnd);
        date = dt.daysToMonthYear(mEnd);

        if(table == null) {
            System.out.printf(Colour.Red("No sales data found for %s\n"), date);
        } else {
            System.out.printf("Sales for %s:\n", date);
            Data.printArrayList(table);
        }

    }

    /**
     * Show all stats stored in file
     */
    public static void statsAll() {
        SaleStats stats = new SaleStats();
        ArrayList<String[]> table;
        ArrayList<String[]> revMatrix = Data.readCSV(Path.revMatrix);
        int max = revMatrix.size() - 1;
        if(max == 0) { //if no data in revenue matrix file
            Colour.println(Colour.TEXT_RED, "No sales data found");
            return;
        }
        int start = Integer.parseInt(revMatrix.get(1)[0]);
        int end = Integer.parseInt(revMatrix.get(max)[0]);
        table = stats.rangeRevenue(start, end);

        if(table == null) {
            Colour.println(Colour.TEXT_RED, "No sales data found");
        } else {
            System.out.println("Showing all records:");
            Data.printArrayList(table);
        }
    }
}
