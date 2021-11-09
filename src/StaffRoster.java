package staff;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.Scanner;

public class StaffRoster {
    staff Staff = new staff();
    private static int numStaff = 0;


    public void ShownumStaff(){
        System.out.println("There are "+ numStaff + " hired.");
    }

    public void Showhired(){
        System.out.println("This is list of the Staff in the restaurant: ");
        x = new Scanner(new File("staffroster.csv"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x.close();
    }
    public void hiring(int ID,String name, String position){

        Staff.hire(ID,name,position,username,password);
        System.out.println(name+ " is hired as " +position);
        numStaff++;
        
    }
    public void firing(int ID){
        staff[ID-1].fire(staff[ID-1].getStaffname);
        System.out.println("Staff has been fired");
        numStaff --;
    }
    public void checkin (int ID){
        staff[ID-1].status_change(1);
    }
    public void checkout (int ID){
        staff[ID-1].status_change(2);
    }
    public void reportsick (int ID){
        staff[ID-1].status_change(3);
    }

    public String login(String Username, String Password){
        boolean Login = false;
        String Usern =""; String Pass = "";String name="";
        try{
            x = new Scanner(new File("loginPassword.csv"));
            x.useDelimiter("[,\n]");

            while(x.hasNext()&&!Login){
                name = x.next();
                Usern = x.next();
                Pass = x.next();
                if(Username == Usern){
                    if(Password == Pass){
                        Login = true;
                        return name;

                    }
                    else{
                        System.out.println("Password incorrect please try again!");
                        return null; 
                    }
                }
            }

        }
        catch(Exception e){
            System.out.println("user not found!");
            return null;
        }
    }
    public String logout(){
        return null;
    }
}
