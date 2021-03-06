package ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import util.*;

public class PmenuUI implements BaseUI{
    private static Scanner x;

    public PmenuUI() {}

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);
        Scanner x = new Scanner(System.in);

        do {
            System.out.println();
            Colour.println(Colour.TEXT_BLUE,"Promo Menu Settings");
            Colour.println(Colour.TEXT_GREEN,"(1) Print existing packages");
            Colour.println(Colour.TEXT_GREEN,"(2) Create a new package item");
            Colour.println(Colour.TEXT_GREEN,"(3) Edit an existing package item's details");
            Colour.println(Colour.TEXT_GREEN,"(4) Delete a package from menu");
            Colour.println(Colour.TEXT_GREEN,"(0) Back");

            System.out.println("");
            Colour.print(Colour.TEXT_GREEN, "Enter your choice: ");
            
            choice = sc.nextInt();
            System.out.println();
            switch (choice) {
            //each of these cases call another method within this class
            case 1:
                PmenuPrint();
                break;
            case 2:
                System.out.print("Please enter item Id: ");
                String Id = sc.next();
                System.out.println();
                System.out.print("Please enter item name: ");
                String name= sc.next();
                System.out.println();
                System.out.print("Please enter item price: ");
                String price= sc.next();
                System.out.println();
                System.out.print("Please enter item description: ");
                String desc= x.nextLine();
                System.out.println();
                System.out.print("Are there any allergen in dish (TRUE/FALSE): ");
                Boolean allergen= Boolean.parseBoolean(sc.next());
                System.out.println();
                System.out.print("Does the chef reccomend the dish (TRUE/FALSE): ");
                Boolean chefr= Boolean.parseBoolean(sc.next());
                System.out.println();
                this.addItem( Id , name,  price, desc,Boolean.toString(allergen),Boolean.toString(chefr));
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
                System.out.print("Please enter item description: ");
                String newdesc= x.nextLine();
                System.out.println();
                System.out.print("Update allergen(TRUE/FALSE): ");
                Boolean newAllergen= Boolean.parseBoolean(sc.next());
                System.out.println();
                System.out.print("Update chef reccomendation(TRUE/FALSE): ");
                Boolean newChefr= Boolean.parseBoolean(sc.next());
                System.out.println();
                this.editMenu(Idedit,newId,  newname,  newprice, newdesc,Boolean.toString(newAllergen),Boolean.toString(newChefr));
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
                System.out.println("Going back ???.");
                break;

            default:
                System.out.println("Invalid choice!");
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

    private static void PmenuPrint() {
        ArrayList<String[]> tempArr = new  ArrayList<String[]>();
        ArrayList<String[]> printArr = new ArrayList<String[]>();
        LinkedHashMap<String, String[]> printMap = new LinkedHashMap<String, String[]>();

        tempArr.clear();
        printArr.clear();
        tempArr = Data.readCSV(Path.menu);
        printArr = new ArrayList<String[]>();
        printArr.add(tempArr.get(0));
        tempArr.remove(0);
        
        for(String[] row: tempArr) {
            if(Integer.parseInt(row[0]) < 100) {
                printArr.add(row);
            }
        }
        printArr = Data.sortArrayList(printArr);
        printMap = Data.parse(printArr);
        printMap.remove("type");
        printArr = Data.parse(printMap);
        Data.printArrayList(formatMenu(printArr));
        
        
    }

    //Case 2 method
    public void addItem(String Id ,String name, String price,String description,String allergen,String chefr){
        String[] writeStr = new String[7];
        writeStr[0] = Id;
        writeStr[1] = name;
        writeStr[2] = price;
        writeStr[3] = "";
        writeStr[4] = description;
        writeStr[5] = allergen;
        writeStr[6] = chefr;

        ArrayList<String[]> tempArr = Data.readCSV(Path.menu);
        tempArr.add(writeStr);
        Data.writeCSV(Data.sortArrayList(tempArr), Path.menu);
    }

    //Case 3 method
    public void editMenu(String Idedit, String newId, String newname, String newprice,String newdescription,String newAllergen,String newChefr){
        String[] writeStr = new String[7];
        writeStr[0] = newId;
        writeStr[1] = newname;
        writeStr[2] = newprice;
        writeStr[3] = "";
        writeStr[4] = newdescription;
        writeStr[5] = newAllergen;
        writeStr[6] = newChefr;

        ArrayList<String[]> tempArr = Data.readCSV(Path.menu);
        for(int i=0; i<tempArr.size(); i++) {
            if(i==0) {continue;} //skip col headers
            if(Integer.parseInt(tempArr.get(i)[0]) == Integer.parseInt(Idedit)) {
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
   



