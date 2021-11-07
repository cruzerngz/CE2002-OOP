package ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class menuSetting {
    private static Scanner x;
    public menuSetting() {
    }

    public void printOptions() throws FileNotFoundException {
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
                System.out.println("Please select what you would like to print out(1 - main, 2 - sides, 3 - dessert, 4 - drinks, 5 - All): ");
                int selection = 0;
                selection = sc.nextInt();
                this.printmenu(selection);
                break;
            case 2:
                System.out.println("Please enter what type the item is(main,side,dessert or drink): ");
                String type = sc.nextLine();
                System.out.println("Please enter item Id: ");
                int Id = sc.nextInt();
                System.out.println("Please enter item name: ");
                String name= sc.nextLine();
                System.out.println("Please enter item price: ");
                int price= sc.nextInt();
                String filepath = type+".txt";
                this.additem(type, Id , name,  price,  filepath);
                break;
            case 3:
                System.out.println("Please enter what type the item is(main,side,dessert or drink): ");
                String type3 = sc.nextLine();
                System.out.println("Please enter item Id of item to be edited: ");
                int Idedit = sc.nextInt();
                System.out.println("Please enter item new Id: ");
                int newId = sc.nextInt();
                System.out.println("Please enter item new name: ");
                String newname= sc.nextLine();
                System.out.println("Please enter item new price: ");
                int newprice= sc.nextInt();
                String filepath3 = type3+".txt";
                this.editmenu(Idedit,newId,  newname,  newprice,  filepath3);
                break;
            case 4:
                System.out.println("Please enter what type the item is(main,side,dessert or drink): ");
                String type4 = sc.nextLine();
                System.out.println("Please enter item Id of item to be deleted: ");
                int Iddelete = sc.nextInt();
                String filepath4 = type4+".txt";
                this.deleteitem(filepath4, Iddelete);
                break;
            case 5:
                System.out.println("Going back ….");
            }
        } while (choice < 5);
        sc.close();
    }

    private void printmenu(int selection) throws FileNotFoundException{
        switch(selection){
            case 1:
            x = new Scanner(new File("main.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x.close();
            case 2:
            x = new Scanner(new File("sides.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x.close();
            case 3:
            x = new Scanner(new File("dessert.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x.close();
            case 4:
            x = new Scanner(new File("drinks.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x.close();
            case 5:
            x = new Scanner(new File("main.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x = new Scanner(new File("sides.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x = new Scanner(new File("dessert.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x = new Scanner(new File("drinks.txt"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x.close();

            

        }
        
    }

    //Case 2 method
    public void additem(String type,int Id ,String name, int price, String filepath){
        try{
            FileWriter fw = new FileWriter (filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println (Id+","+name+","+price);
            pw.flush();
            pw.close();
            System.out.println("done!");
        }
        catch(Exception e){
            System.out.println("Try again!");
        }
    }

    //Case 3 method
    public void editmenu(int Idedit, int newId, String newname, int newprice, String filepath){
        String tempfile = "tempfile.txt";
        File oldfile = new File(filepath);
        File newFile = new File(tempfile);
        int Id3 = 0; String name3 = "";int price3 = 0; 

        try {
            FileWriter fw = new FileWriter(tempfile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()){
                Id3 = x.nextInt();
                name3 = x.next();
                price3 = x.nextInt();
                if(Id3 == Idedit){
                    pw.println(newId+","+newname+","+newprice);
                }
                else{
                    pw.println(Id3+","+name3+","+price3);
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
    public void deleteitem(String filepath, int Iddelete){
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        int Id = 0; String name = ""; int price = 0;
        try{
            FileWriter fw = new FileWriter(tempFile, true );
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                Id = x.nextInt();
                name = x.next();
                price = x.nextInt();
                if (Id != Iddelete){
                    pw.println(Id+","+name+","+price);
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