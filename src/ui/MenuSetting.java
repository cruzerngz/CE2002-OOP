package ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import util.*;

public class MenuSetting implements BaseUI{
    private static Scanner x;
    
    

    public MenuSetting() {
    }

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            Colour.println(Colour.TEXT_BLUE,"Menu Settings");
            Colour.println(Colour.TEXT_GREEN,"(1) Print existing menu");
            Colour.println(Colour.TEXT_GREEN,"(2) Create a new menu item");
            Colour.println(Colour.TEXT_GREEN,"(3) Edit an existing menu item’s details");
            Colour.println(Colour.TEXT_GREEN,"(4) Delete a menu item");
            Colour.println(Colour.TEXT_GREEN,"(0) Back");

            System.out.println("");
            Colour.print(Colour.TEXT_GREEN, "Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            String filepath = Path.menu;
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
    
                this.addItem(type, Id , name,  price,Boolean.toString(allergen),Boolean.toString(chefr),  filepath);
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
                String newAllergen= sc.next();
                System.out.println();
                System.out.println("Update chef reccomendation(TRUE/FALSE): ");
                String newChefr= sc.next();
                System.out.println();
                
                this.editMenu(Idedit,newId,  newname,  newprice, newType,newAllergen,newChefr,  filepath);
                break;
            case 4:
                System.out.println("Please enter item Id of item to be deleted: ");
                String Iddelete = sc.nextLine();
                
                this.deleteItem(filepath, Iddelete);
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
                Data.printArrayList(Data.sortArrayList(printArr));
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
                Data.printArrayList(Data.sortArrayList(printArr));
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
                Data.printArrayList(Data.sortArrayList(printArr));
                break;

            case 4:
                tempArr.clear();
                tempArr = Data.readCSV(Path.menu);
                Data.printArrayList(Data.sortArrayList(tempArr));
                break;

            default:
                Colour.println(Colour.TEXT_RED, "Error, please select a valid option.");
                break;
        }
    }

    //above works

    //Case 2 method
    public void addItem(String type,String Id ,String name, String price,String allergen,String chefr, String filepath){
        try{
            FileWriter fw = new FileWriter (filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println (Id+","+name+","+price+","+type+","+allergen+","+chefr);
            pw.flush();
            pw.close();
            System.out.println("done!");
        }
        catch(Exception e){
            System.out.println("Try again!");
        }
    }

    //Case 3 method
    public void editMenu(String Idedit, String newId, String newname, String newprice,String newType,String newAllergen,String newChefr, String filepath){
        String tempfile = "tempfile.csv";
        File oldfile = new File(filepath);
        File newFile = new File(tempfile);
        String Id3 = ""; String name3 = "";String price3 = "";String type3 ="";String allergen3 =""; String chefr3 ="";

        try {
            FileWriter fw = new FileWriter(tempfile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()){
                Id3 = x.next();
                name3 = x.next();
                price3 = x.next();
                type3 = x.next();
                allergen3 = x.next();
                chefr3 = x.next();
                if(Id3 == Idedit){
                    pw.println(newId+","+newname+","+newprice+","+newType+","+newAllergen+","+newChefr);
                }
                else{
                    pw.println(Id3+","+name3+","+price3+","+type3+","+allergen3+","+chefr3);
                }
                x.close();
                pw.flush();
                pw.close();
                oldfile.delete();
                File dump  = new File (filepath);
                newFile.renameTo(dump);
            }
        } catch (Exception e) {
            System.out.println("Please try again!");
        }
    } 
    //Case 4 method
    public void deleteItem(String filepath, String Iddelete){
        String tempFile = "temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String Id = ""; String name = ""; String price = "";String allergen =""; String chefr =""; String type = "";
        try{
            FileWriter fw = new FileWriter(tempFile, true );
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                Id = x.next();
                name = x.next();
                price = x.next();
                type= x.next();
                allergen = x.next();
                chefr= x.next();
                if (Id != Iddelete){
                    pw.println(Id+","+name+","+price+","+type+","+allergen+","+chefr);
                }
            }
           x.close();
           pw.flush();
           pw.close();
           oldFile.delete();
           File dump = new File(filepath);
           newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("please try again");
        }
    }

}