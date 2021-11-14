package ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import util.*;

public class MenuSettingUI implements BaseUI{
    private static Scanner x;
    
    

    public MenuSettingUI() {
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println();
            Colour.println(Colour.TEXT_BLUE,"Menu Settings");
            Colour.println(Colour.TEXT_GREEN,"(1) Print existing menu");
            Colour.println(Colour.TEXT_GREEN,"(2) Create a new menu item");
            Colour.println(Colour.TEXT_GREEN,"(3) Edit an existing menu item's details");
            Colour.println(Colour.TEXT_GREEN,"(4) Delete a menu item");
            Colour.println(Colour.TEXT_GREEN,"(0) Back");

            System.out.println("");
            Colour.print(Colour.TEXT_GREEN, "Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
            
            switch (choice) {
            //each of these cases call another method within this class
            case 1:
                menuOption1();
                break;
            case 2:
                System.out.print("Please enter what type the item is(main,dessert or drink): ");
                String type = sc.next();
                System.out.println();
                System.out.print("Please enter item Id: ");
                String Id = sc.next();
                System.out.println();
                System.out.print("Please enter item name: ");
                String name= sc.next();
                System.out.println();
                System.out.print("Please enter item price: ");
                String price= sc.next();
                System.out.println();
                System.out.print("Are there any allergen in dish (TRUE/FALSE): ");
                Boolean allergen= Boolean.parseBoolean(sc.next());
                System.out.println();
                System.out.print("Does the chef reccomend the dish (TRUE/FALSE): ");
                Boolean chefr= Boolean.parseBoolean(sc.next());
                System.out.println();
                this.addItem(type, Id , name,  price,Boolean.toString(allergen),Boolean.toString(chefr));
                Colour.println(Colour.TEXT_CYAN, "Item added");
                break;

            case 3:
                System.out.print("Please enter item Id of item to be edited: ");
                String Idedit = sc.next();
                System.out.println();
                System.out.print("Please enter item new Id: ");
                String newId = sc.next();
                System.out.println();
                System.out.print("Please enter item new name: ");
                String newname= sc.next();
                System.out.println();
                System.out.print("Please enter item new price: ");
                String newprice= sc.next();
                System.out.println();
                System.out.print("Update type(main/dessert/drinks): ");
                String newType= sc.next();
                System.out.println();
                System.out.print("Update allergen(TRUE/FALSE): ");
                Boolean newAllergen= Boolean.parseBoolean(sc.next());
                System.out.println();
                System.out.print("Update chef reccomendation(TRUE/FALSE): ");
                Boolean newChefr= Boolean.parseBoolean(sc.next());
                System.out.println();
                this.editMenu(Idedit,newId,  newname,  newprice, newType,Boolean.toString(newAllergen),Boolean.toString(newChefr));
                Colour.println(Colour.TEXT_CYAN, "Item edited");
                break;

            case 4:
                System.out.print("Please enter item Id of item to be deleted: ");
                String Iddelete = sc.next();
                System.out.println();
                this.deleteItem(Iddelete);
                Colour.println(Colour.TEXT_CYAN, "Item deleted");
                break;
            case 0:
                System.out.println("Going back ….");
                break;
            default:
                System.out.println("Invalid choice");
                break;
            }
        } while (choice != 0);
    }

    /**
     * Formats the menu to a printable state
     * @param menuList Menu to be formatted
     * @return
     */
    public static ArrayList<String[]> formatMenu(ArrayList<String[]> menuList) {

        LinkedHashMap<String, String[]> tempMap = Data.parse(menuList);


        for(int i=0; i<tempMap.get("price").length; i++) {
            float temp = Float.parseFloat(tempMap.get("price")[i]);
            tempMap.get("price")[i] = String.format("$%.2f", temp);
        }
        for(int i=0; i<tempMap.get("allergen").length; i++) {
            if(tempMap.get("allergen")[i].equals("true")) {
                tempMap.get("allergen")[i] = Colour.Red(tempMap.get("allergen")[i]);
            }
        }
        for(int i=0; i<tempMap.get("recommend").length; i++) {
            if(tempMap.get("recommend")[i].equals("true")) {
                tempMap.get("recommend")[i] = Colour.Green(tempMap.get("recommend")[i]);
            }
        }

        return Data.parse(tempMap);
    }

    public static void menuOption1() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String[]> tempArr = new  ArrayList<String[]>();
        ArrayList<String[]> printArr = new ArrayList<String[]>();

        Colour.println(Colour.TEXT_GREEN,"(1) Main");
        Colour.println(Colour.TEXT_GREEN,"(2) Drinks");
        Colour.println(Colour.TEXT_GREEN,"(3) Dessert");
        Colour.println(Colour.TEXT_GREEN,"(4) All");
        Colour.print(Colour.TEXT_BLUE,"Please select what you would like to print out: ");
        int choice = sc.nextInt();
        System.out.println();

        switch(choice) {
            case 1:
                tempArr.clear();
                printArr.clear();
                tempArr = Data.readCSV(Path.menu);
                printArr = new ArrayList<String[]>();
                printArr.add(tempArr.get(0));
                tempArr.remove(0);
                
                for(String[] row: tempArr) {
                    if(Integer.parseInt(row[0]) >= 100 &&
                    Integer.parseInt(row[0]) < 200) {
                        printArr.add(row);
                    }
                }
                printArr = Data.sortArrayList(printArr);
                Data.printArrayList(formatMenu(printArr));
                break;

            case 2:
                tempArr.clear();
                printArr.clear();
                tempArr = Data.readCSV(Path.menu);
                printArr = new ArrayList<String[]>();
                printArr.add(tempArr.get(0));
                tempArr.remove(0);

                for(String[] row: tempArr) {
                    if(Integer.parseInt(row[0]) >= 200 &&
                    Integer.parseInt(row[0]) <300) {
                        printArr.add(row);
                    }
                }
                printArr = Data.sortArrayList(printArr);
                Data.printArrayList(formatMenu(printArr));
                break;

            case 3:
                tempArr.clear();
                printArr.clear();
                tempArr = Data.readCSV(Path.menu);
                printArr = new ArrayList<String[]>();
                printArr.add(tempArr.get(0));
                tempArr.remove(0);

                for(String[] row: tempArr) {
                    if(Integer.parseInt(row[0]) >= 300 &&
                    Integer.parseInt(row[0]) <400) {
                        printArr.add(row);
                    }
                }
                printArr = Data.sortArrayList(printArr);
                Data.printArrayList(formatMenu(printArr));
                break;

            case 4:
                tempArr.clear();
                printArr.clear();
                tempArr = Data.readCSV(Path.menu);
                printArr = new ArrayList<String[]>();
                printArr.add(tempArr.get(0));
                tempArr.remove(0);

                for(String[] row: tempArr) {
                    if(Integer.parseInt(row[0]) >= 100 &&
                    Integer.parseInt(row[0]) <400) {
                        printArr.add(row);
                    }
                }
                printArr = Data.sortArrayList(printArr);
                Data.printArrayList(formatMenu(printArr));
                break;

            default:
                Colour.println(Colour.TEXT_RED, "Error, please select a valid option.");
                break;
        }
    }

    //above works

    //Case 2 method
    public void addItem(String type,String Id ,String name, String price,String allergen,String chefr){
        String[] writeStr = new String[6];
        writeStr[0] = Id;
        writeStr[1] = name;
        writeStr[2] = price;
        writeStr[3] = type;
        writeStr[4] = allergen;
        writeStr[5] = chefr;

        ArrayList<String[]> tempArr = Data.readCSV(Path.menu);
        tempArr.add(writeStr);
        Data.writeCSV(Data.sortArrayList(tempArr), Path.menu);
    }

    //Case 3 method
    public void editMenu(String Idedit, String newId, String newname, String newprice,String newType,String newAllergen,String newChefr){
        String[] writeStr = new String[6];
        writeStr[0] = newId;
        writeStr[1] = newname;
        writeStr[2] = newprice;
        writeStr[3] = newType;
        writeStr[4] = newAllergen;
        writeStr[5] = newChefr;

        ArrayList<String[]> tempArr = Data.readCSV(Path.menu);
        for(int i=0; i<tempArr.size(); i++) {
            if(i==0) {continue;} //skip col headers
            if(Integer.parseInt(tempArr.get(i)[0]) == Integer.parseInt(newId)) {
                tempArr.remove(i);
                tempArr.add(writeStr);
                tempArr = Data.sortArrayList(tempArr);
                Data.writeCSV(tempArr, Path.menu);
            }
        }
        
    } 
    //Case 4 method
    public void deleteItem(String Iddelete){
        ArrayList<String[]> tempArr = Data.readCSV(Path.menu);

        for(int i=0; i<tempArr.size(); i++) {
            if(i==0) {continue;} //skip col headers
            if(Integer.parseInt(tempArr.get(i)[0]) == Integer.parseInt(Iddelete)) {
                tempArr.remove(i);
                Data.writeCSV(tempArr, Path.menu);
            }
        }   
    }
}
