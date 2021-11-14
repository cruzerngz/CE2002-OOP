package ui;

import util.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import objects.StaffRoster;

public class AdminUI implements BaseUI{
    
    //TODO fix implements
    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do {
            Colour.println(Colour.TEXT_BLUE, "ADMIN");
            Colour.println(Colour.TEXT_GREEN, "(1) Staff");
            Colour.println(Colour.TEXT_GREEN,"(2) Change date and time");
            Colour.println(Colour.TEXT_GREEN,"(0) Return"); 
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch(choice) {
                case 1:
                    staffUI();
                    break;
                case 2:
                    dateTimeUI();
                    break;
                case 0:
                    System.out.println("returning to main program...");
                    break;
                default:
                    break;
            }
        } while(choice != 0);
    }

    /**
     * UI for handling staff related stuff hello
     */
    public static void staffUI() {
        int choice;
        Scanner sc = new Scanner(System.in);
        // StaffStaffRoster StaffRoster = new StaffStaffRoster();
        

        do {
            Colour.println(Colour.TEXT_BLUE,"STAFF");
            Colour.println(Colour.TEXT_GREEN,"(1) Show number of staff");
            Colour.println(Colour.TEXT_GREEN,"(2) Show staff list");
            Colour.println(Colour.TEXT_GREEN,"(3) Hiring Staff");
            Colour.println(Colour.TEXT_GREEN,"(4) Firing Staff");
            Colour.println(Colour.TEXT_GREEN,"(5) Login");
            Colour.println(Colour.TEXT_GREEN,"(6) Logout");
            Colour.println(Colour.TEXT_GREEN,"(0) Return");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch(choice) {
                case 1:
                    StaffRoster.showNumStaff();
                    break;
                case 2:
                    StaffRoster.showHired();
                    break;
                case 3:
                    System.out.print("Please enter the ID given: ");
                    int ID = sc.nextInt();
                    System.out.println();
                    System.out.print("Please enter the Name: ");
                    String name = sc.next();
                    System.out.println();
                    System.out.print("Please enter the position: ");
                    String position = sc.next();
                    System.out.println();
                    System.out.print("Please enter the username: ");
                    String username = sc.next();
                    System.out.println();
                    System.out.print("Please enter the password: ");
                    String password = sc.next();
                    System.out.println();
                    StaffRoster.hiring(ID,name,position,username,password);
                    break;
                case 4:
                    System.out.print("Please enter the name of staff to be fired: ");
                    String namef = sc.next();
                    System.out.println();
                    StaffRoster.firing(namef);
                    break;
                case 5:
                    System.out.print("Please enter your username: ");
                    String Lusername = sc.next();
                    System.out.println();
                    System.out.print("please enter your password: ");
                    String Lpassword = sc.next();
                    System.out.println();
                    Boolean successful = StaffRoster.login(Lusername,Lpassword);
                    // System.out.println(Sname+" has log in!");
                    break;
                case 6: 
                    // System.out.println("Enter staff username")
                    StaffRoster.logout();
                    System.out.println("logged out");
                    break;
                default:
                    break;
            }
        } while(choice != 0);
    }

    /**
     * UI for handling date-time related stuff
     */
    public static void dateTimeUI() {
        int choice;
        Scanner sc = new Scanner(System.in);
        DateTime datetime = new DateTime();
        

        do {
            Colour.println(Colour.TEXT_BLUE,"TIME");
            Colour.println(Colour.TEXT_GREEN,"(1) Show current date and time");
            Colour.println(Colour.TEXT_GREEN,"(2) Modify hours");
            Colour.println(Colour.TEXT_GREEN,"(3) Modify days");
            Colour.println(Colour.TEXT_GREEN,"(4) Reset to current time");
            Colour.println(Colour.TEXT_GREEN,"(0) Return");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch(choice) {
                case 1:
                    Colour.println(Colour.TEXT_CYAN, datetime.getDateTime());
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Enter the number of hours to offset: ");
                    datetime.deltaHour(sc.nextInt());
                    System.out.println();
                    System.out.print("New date and time: ");
                    Colour.print(Colour.TEXT_CYAN, datetime.getDateTime());
                    System.out.println();
                    System.out.println();
                    choice = 1;
                    break;

                case 3:
                    System.out.print("Enter the number of days to offset: ");
                    datetime.deltaDay(sc.nextInt());
                    System.out.println();
                    System.out.print("New date and time: ");
                    Colour.print(Colour.TEXT_CYAN, datetime.getDateTime());
                    System.out.println();
                    System.out.println();
                    choice = 2;
                    break;
                
                case 4:
                    datetime.reset();
                    System.out.print("Reset! Date and time: ");
                    Colour.print(Colour.TEXT_CYAN, datetime.getDateTime());
                    System.out.println();
                    System.out.println();
                    break;

                case 0:
                    break;

                default:
                    break;
            }
        } while(choice != 0);
    }
}
