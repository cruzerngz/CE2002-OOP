package staff;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
public class staff {
    private int staffid;
    private String status;
    private String name;
    private String position;
    private boolean employstat;
    private String username;
    private String password;
    
    public staff(){}
    public staff(int staff_id){
        staffid = staff_id;
        status = "";
        name = "";
        positon = "";
        username = "";
        password = "";
    }
    public getstaffid(){
        return staffid;
    }
    public String getStaffstatus(){
        return status;
    }
    public String getStaffname(){
        return name;
    }
    public String getStaffposition(){
        return position;
    }

    public String getUserName(){

    }

    public void hire(int staff_ID, String Name, String Position, String Username, String Password){
        staffid = staff_ID;
        name = Name;
        position = Position;
        username = Username;
        password = Password;
        status = "Not working.";
       
        try{
            FileWriter fw = new FileWriter ("loginPassword.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println (name+","+username+","+password);
            pw.flush();
            pw.close();
            System.out.println("done!");
        }
        catch(Exception e){
            System.out.println("Try again!");
        }
        try{
            FileWriter fw = new FileWriter ("staffroster.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println (staffid+","+name+","+position+","+status);
            pw.flush();
            pw.close();
            System.out.println("done!");
        }
        catch(Exception e){
            System.out.println("Try again!");
        }
    }
    public void fire(String  Name){
        String name_fire = Name;
        name = "";
        position = "";
        status = "";
        staffid = 0;
        
        String tempFile = "temp.csv";
        File oldFile = new File("loginPassword.csv");
        File newFile = new File(tempFile);
        String Usern = ""; String pass = ""; String tempname="";
        try{
            FileWriter fw = new FileWriter(tempFile, true );
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                tempname = x.next();
                Usern = x.next();
                pass= x.next();
                if (tempname != name_fire ){
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
    public String status(){
        return status;
    }

    public String status_change(int choice){
        switch (choice){
            case 1:
                return "working";
            case 2:
                return "not working";
            case 3:
                return "sick";
        }
    }


 }
 