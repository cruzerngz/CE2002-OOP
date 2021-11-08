package ui;

import java.util.Scanner;

/**
 * Template for creating other UI classes
 */
public class templateUI {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("(1) Option1");
        System.out.println("(2) Option2");
        System.out.println("(0) Return"); 

        do {
            System.out.println();
            System.out.print("Enter you choice: ");
            choice = sc.nextInt();
            
            switch(choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while(choice != 0);
        sc.close();
    }
}
