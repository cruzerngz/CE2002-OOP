package objects;

import util.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;


public class StaffRoster {
    private static String savePath = "../data/Staffroster.csv";

    public static void showNumStaff(){
        ArrayList<String[]> tempArr = Data.readCSV(savePath);
        System.out.println("There are "+ (tempArr.size()-1) + " staff hired.");
    }

    public static void showHired() {
        System.out.println("This is list of the Staff in the restaurant: ");
        ArrayList<String[]> tempArr = Data.readCSV(savePath);
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArr);
        
        tempMap.remove("password"); //remove password col from display
        Data.printArrayList(Data.parse(tempMap));
    }

    public static void hiring(int ID,String name, String position, String username, String password){
        System.out.println("password is: " + password);
        Staff newStaff = new Staff(ID, name, position, username, password);
        // System.out.println(Arrays.asList(newStaff.toString()));
        newStaff.write();
        
        System.out.println(name+ " is hired as " +position);
        
    }
    public static void firing(String Name){
        Staff removeStaff = new Staff(Name);
        if(removeStaff.remove()) {
            System.out.println("Staff has been fired");
        } else {
            System.out.println("Staff not found");
        }
    }

    public static Boolean login(String Username, String Password){
        ArrayList<String[]> tempArr = Data.readCSV(savePath);
        Staff member = null;

        //find username in file
        for(int i=0; i<tempArr.size(); i++) {
            if(tempArr.get(i)[3].equals(Username)) {
                member = new Staff(tempArr.get(i)[1]);
            }
        }
        //username not found
        if(member == null) {
            System.out.println("Member not found, Please try again!");
            return false;
        }
        //password check
        if(member.verify(Password)) {
            member.makeActive();
            System.out.printf("Successful login for %s\n", member.getName());
            return true;
        } else {
            System.out.println("Incorrect password");
            return false;
        }
    }

    public static Boolean logout() {
        ArrayList<String[]> tempArr = Data.readCSV(savePath);
        Staff member = null;
        //find username in file
        for(int i=0; i<tempArr.size(); i++) {
            if(Boolean.parseBoolean(tempArr.get(i)[5]) == true) {
                member = new Staff(tempArr.get(i)[1]);
            }
        }
        //username not found
        if(member == null) {
            System.out.println("Member not found, Please try again!");
            return false;
        }
        //username found, making inactive
        member.makeInactive();
        System.out.println("Successful logout");
        return true;
    }
}
