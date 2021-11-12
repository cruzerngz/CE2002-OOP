package ui;

import util.*;
import java.util.Scanner;

import staff.StaffRoster;

public class AdminUI {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        
        do {
            System.out.println(StrColour.Blue("ADMIN"));
            System.out.println(StrColour.Green("(1) Staff"));
            System.out.println(StrColour.Green("(2) Change date and time"));
            System.out.println(StrColour.Green("(0) Return")); 
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
        

        do {
            System.out.println(StrColour.Blue("STAFF"));
            System.out.println(StrColour.Green("(1) Show number of staff"));
            System.out.println(StrColour.Green("(2) Show staff list"));
            System.out.println(StrColour.Green("(3) Hiring staff"));
            System.out.println(StrColour.Green("(4) Fire staff"));
            System.out.println(StrColour.Green("(5) Login"));
            System.out.println(StrColour.Green("(6) Logout"));
            System.out.println(StrColour.Green("(0) Return")); 
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch(choice) {
                case 1:
                    StaffRoster.ShownumStaff();
                    
                case 2:
                    StaffRoster.Showhired();
                    
                case 3:
                    System.out.println("Please enter the ID given: ");
                    int ID = sc.nextInt();
                    System.out.println("Please enter the Name: ");
                    String name = sc.next();
                    System.out.println("Please enter the position: ");
                    String position = sc.next();
                    System.out.println("Please enter the username: ");
                    String username = sc.next();
                    System.out.println("Please enter the password: ");
                    String password = sc.next();
                    StaffRoster.hiring(ID,name,position,username,password);
                    
                case 4:
                    System.out.println("Please enter the name of staff to be fired: ");
                    String namef = sc.next();
                    StaffRoster.firing(namef);
                
                case 5:
                    System.out.println("Please enter your username: ");
                    String Lusername = sc.next();
                    System.out.println("please enter your password: ");
                    String Lpassword = sc.next();
                    String Sname =StaffRoster.login(Lusername,Lpassword);
                    System.out.println(Sname+" has log in!");
                case 6: 
                    StaffRoster.logout();
                    System.out.println("logged out");
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
        DateTime datetime = new DateTime("../data");
        

        do {
            System.out.println(StrColour.Blue("TIME"));
            System.out.println(StrColour.Green("(1) Show current date and time"));
            System.out.println(StrColour.Green("(2) Modify hours"));
            System.out.println(StrColour.Green("(3) Modify days"));
            System.out.println(StrColour.Green("(4) Reset to current time"));
            System.out.println(StrColour.Green("(0) Return"));
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch(choice) {
                case 1:
                    System.out.println(StrColour.Cyan(datetime.getDateTime()));
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Enter the number of hours to offset: ");
                    datetime.deltaHour(sc.nextInt());
                    System.out.println();
                    System.out.print("New date and time: ");
                    System.out.print(StrColour.Cyan(datetime.getDateTime()));
                    System.out.println();
                    System.out.println();
                    choice = 1;
                    break;

                case 3:
                    System.out.print("Enter the number of days to offset: ");
                    datetime.deltaDay(sc.nextInt());
                    System.out.println();
                    System.out.print("New date and time: ");
                    System.out.print(StrColour.Cyan(datetime.getDateTime()));
                    System.out.println();
                    System.out.println();
                    choice = 2;
                    break;
                
                case 4:
                    datetime.reset();
                    System.out.print("Reset! Date and time: ");
                    System.out.print(StrColour.Cyan(datetime.getDateTime()));
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
