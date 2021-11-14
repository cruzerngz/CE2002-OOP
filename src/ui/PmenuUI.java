package ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import ui.BaseUI;
import util.Data;

public class PmenuUI implements BaseUI{
    private static Scanner x;

    public PmenuUI() {}

    public void printOptions() {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Menu Settings");
        System.out.println("(1) Print existing menu");
        System.out.println("(2) Create a new menu item");
        System.out.println("(3) Edit an existing menu item’s details");
        System.out.println("(4) Delete a menu item");
        System.out.println("(5) Back");
        do {
            System.out.println("");
            System.out.printf("Enter your choice: ");
            
            choice = sc.nextInt();
            switch (choice) {
            //each of these cases call another method within this class
            case 1:
                System.out.println("The following is the promotional menu: ");
                this.printMenu();
                break;
            case 2:
                System.out.println("Please enter item Id: ");
                String Id = sc.next();
                System.out.println("Please enter item name: ");
                String name= sc.nextLine();
                System.out.println("Please enter item price: ");
                String price= sc.next();
                System.out.println("Are there any allergen(TRUE/False): ");
                String  allergen = sc.nextLine();
                System.out.println("Does the chef recommend it(TRUE/FALSE): ");
                String chefr= sc.nextLine();

                this.addItem(Id , name,  price,allergen,chefr);
                break;
            case 3:
                System.out.println("Please enter what type the item is(main,side,dessert or drink): ");
                System.out.println("Please enter item Id of item to be edited: ");
                String Idedit = sc.next();
                System.out.println("Please enter item new Id: ");
                String newId = sc.next();
                System.out.println("Please enter item new name: ");
                String newname= sc.nextLine();
                System.out.println("Please enter item new price: ");
                String newprice= sc.next();
                System.out.println("Update allergen(TRUE/FALSE): ");
                String newAllergen= sc.nextLine();
                System.out.println("Update chef reccomendation(TRUE/FALSE): ");
                String newChefr= sc.nextLine();
                this.editMenu(Idedit,newId,  newname,  newprice,newAllergen,newChefr);
                break;
            case 4:
                System.out.println("Please enter what type the item is(main,side,dessert or drink): ");
                System.out.println("Please enter item Id of item to be deleted: ");
                String Iddelete = sc.next();
                this.deleteItem(Iddelete);
                break;
            case 5:
                System.out.println("Going back ….");
            }
        } while (choice < 5);
    }

    private void printMenu() {

        Data.printArrayList(Data.readCSV("../data/promomenu.csv"));
    }

    //Case 2 method
    public void addItem(String Id ,String name, String price,String allergen,String chefr){
        try{
            FileWriter fw = new FileWriter ("../data/promomenu.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println (Id+","+name+","+price+","+allergen+","+chefr);
            pw.flush();
            pw.close();
            System.out.println("done!");
        }
        catch(Exception e){
            System.out.println("Try again!");
        }
    }

    //Case 3 method
    public void editMenu(String Idedit, String newId, String newname, String newprice,String newAllergen,String newChefr){
        String tempfile = "tempfile.txt";
        File oldfile = new File("../data/promomenu.csv");
        File newFile = new File(tempfile);
        String Id3 = ""; String name3 = "";String price3 = ""; String allergen3 =""; String chefr3 ="";

        try {
            FileWriter fw = new FileWriter(tempfile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File("../data/promomenu.csv"));
            x.useDelimiter("[,\n]");

            while (x.hasNext()){
                Id3 = x.next();
                name3 = x.next();
                price3 = x.next();
                if(Id3 == Idedit){
                    pw.println(newId+","+newname+","+newprice+","+newAllergen+","+newChefr);
                }
                else{
                    pw.println(Id3+","+name3+","+price3+","+allergen3+","+chefr3);
                }
                x.close();
                pw.flush();
                pw.close();
                oldfile.delete();
                File dump  = new File ("../data/promomenu.csv");
                newFile.renameTo(dump);
            }
        } catch (Exception e) {
            System.out.println("Please try again!");
        }
    } 
    //Case 4 method
    public void deleteItem(String Iddelete){
        String tempFile = "temp.txt";
        File oldFile = new File("../data/promomenu.csv");
        File newFile = new File(tempFile);
        String Id = ""; String name = ""; String price = "";String allergen =""; String chefr ="";
        try{
            FileWriter fw = new FileWriter(tempFile, true );
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File("../data/promomenu.csv"));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                Id = x.next();
                name = x.next();
                price = x.next();
                if (Id != Iddelete){
                    pw.println(Id+","+name+","+price+","+allergen+","+chefr);
                }
            }
           x.close();
           pw.flush();
           pw.close();
           oldFile.delete();
           File dump = new File("../data/promomenu.csv");
           newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("please try again");
        }
    }
   

}

