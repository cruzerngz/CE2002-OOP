package objects;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class pmenu {
    private static Scanner x;
    public pmenu() {
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
                System.out.println("The following is the promotional menu: ");
                this.printmenu();
                break;
            case 2:
                System.out.println("Please enter item Id: ");
                String Id = sc.next();
                System.out.println("Please enter item name: ");
                String name= sc.nextLine();
                System.out.println("Please enter item price: ");
                String price= sc.next();

                this.additem(Id , name,  price);
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
                this.editmenu(Idedit,newId,  newname,  newprice);
                break;
            case 4:
                System.out.println("Please enter what type the item is(main,side,dessert or drink): ");
                System.out.println("Please enter item Id of item to be deleted: ");
                String Iddelete = sc.next();
                this.deleteitem(Iddelete);
                break;
            case 5:
                System.out.println("Going back ….");
            }
        } while (choice < 5);
    }

    private void printmenu() throws FileNotFoundException{
    
        x = new Scanner(new File("promomenu.csv"));
        x.useDelimiter(",");
        while(x.hasNext()){
            System.out.print(x.next());
        }
        x.close();
        
        
    }

    //Case 2 method
    public void additem(String Id ,String name, String price){
        try{
            FileWriter fw = new FileWriter ("promomenu.csv", true);
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
    public void editmenu(String Idedit, String newId, String newname, String newprice){
        String tempfile = "tempfile.txt";
        File oldfile = new File("promomenu.csv");
        File newFile = new File(tempfile);
        String Id3 = ""; String name3 = "";String price3 = ""; 

        try {
            FileWriter fw = new FileWriter(tempfile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File("promomenu.csv"));
            x.useDelimiter("[,\n]");

            while (x.hasNext()){
                Id3 = x.next();
                name3 = x.next();
                price3 = x.next();
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
                File dump  = new File ("promomenu.csv");
                newFile.renameTo(dump);
            }
        } catch (Exception e) {
            System.out.println("Please try again!");
        }
    } 
    //Case 4 method
    public void deleteitem(String Iddelete){
        String tempFile = "temp.txt";
        File oldFile = new File("promomenu.csv");
        File newFile = new File(tempFile);
        String Id = ""; String name = ""; String price = "";
        try{
            FileWriter fw = new FileWriter(tempFile, true );
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File("promomenu.csv"));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                Id = x.next();
                name = x.next();
                price = x.next();
                if (Id != Iddelete){
                    pw.println(Id+","+name+","+price);
                }
            }
           x.close();
           pw.flush();
           pw.close();
           oldFile.delete();
           File dump = new File("promomenu.csv");
           newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println("please try again");
        }
    }
   

}

