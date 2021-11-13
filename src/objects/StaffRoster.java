package objects;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;


public class StaffRoster {
    static Staff Staff = new Staff();
    private static Scanner x;
    private static int numStaff = 0;


    public static void showNumStaff(){
        System.out.println("There are "+ numStaff + " hired.");
    }

    public static void showHired() throws FileNotFoundException{
        System.out.println("This is list of the Staff in the restaurant: ");
        x = new Scanner(new File("staffroster.csv"));
            x.useDelimiter(",");
            while(x.hasNext()){
                System.out.print(x.next());
            }
            x.close();
    }
    public static void hiring(int ID,String name, String position, String username, String password){

        Staff.hire(ID,name,position,username,password);
        System.out.println(name+ " is hired as " +position);
        numStaff++;
        
    }
    public static void firing(String Name){
        Staff.fire(Name);
        System.out.println("Staff has been fired");
        numStaff --;
    }


    public static String login(String Username, String Password){
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
        return name;
    }
    public static String logout(){
        return null;
    }
}
