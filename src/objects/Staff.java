package objects;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import util.Data;
import util.Password;

public class Staff {
    private int staffID;
    private String name;
    private String position;
    private String userName;
    private String password;
    private boolean status; // if person is currently logged in
    private boolean registered; // if person is registered in system (name in file)
    private String savePath = "../data/staffRoster.csv";
    
    /**
     * Create staff with new credentials
     * @param staff_id
     * @param name
     * @param position
     * @param username
     * @param password Hashed password
     */
    public Staff(int Staff_id, String Name, String Position, String Username, String Pass){
        staffID = Staff_id;
        name = Name;
        position = Position;
        userName = Username;
        password = Password.hash(Pass);
        status = false;
        registered = false; //not yet written to file, false
    }

    /**
     * Retrieve staff using staff name.
     * Accesses the Staffroster file
     */
    public Staff(String Name) {
        ArrayList<String[]> tempArr = Data.readCSV(savePath);
        LinkedHashMap<String, String[]> tempMap = Data.parse(tempArr);

        int index = 0;
        for(String person: tempMap.get("Name")) {
            //if match
            if (Name.equals(person)) {
                break;
            } else {index++;}
        }
        //build params
        if(index == tempArr.size()) {
            return;
        } else {
            staffID = Integer.parseInt(tempArr.get(index)[0]);
            name = tempArr.get(index)[1];
            position = tempArr.get(index)[2];
            userName = tempArr.get(index)[3];
            password = tempArr.get(index)[4];
            status = Boolean.parseBoolean(tempArr.get(index)[5]);
            registered = true;
        }
    }

    /**
     * Writes the current staff object to file
     * @return
     */
    public boolean write() {
        ArrayList<String[]> tempArr = Data.readCSV(savePath);
        int i;
        //check if staff is in file
        //add changes if staff is in file
        for(i=0; i<tempArr.size(); i++) {
            if(tempArr.get(i)[1].equals(name)) {
                tempArr.remove(i);
                tempArr.add(stringify());
                tempArr = Data.sortArrayList(tempArr);
                Data.writeCSV(tempArr, savePath);
                registered = true;
                return true; //staff already written to file
            }
        }
        //if not written to file, write
        if(i == tempArr.size()) {
            tempArr.add(stringify());
            tempArr = Data.sortArrayList(tempArr);
            Data.writeCSV(tempArr, savePath);
            registered = true;
            return true;
        }
        
        return false;
    }

    /**
     * Remove the current matching object in file
     * @return Boolean success
     */
    public boolean remove() {
        ArrayList<String[]> tempArr = Data.readCSV(savePath);
        int i;
        for(i=0; i<tempArr.size(); i++) {
            if(tempArr.get(i)[1].equals(name)) {
                tempArr.remove(i);
                Data.writeCSV(tempArr, savePath);
                return true; //staff removed
            }
        }
        return false;
    }

    /**
     * Check if staff member already exists in staffRoster.csv
     */
    public Boolean isRegistered() {
        ArrayList<String[]> tempArr = Data.readCSV(savePath);

        registered = false;

        for(int i=0; i<tempArr.size(); i++) {
            if(tempArr.get(i)[1].equals(name)) {
                registered = true;
            }
        }
        return registered;
    }

    /**
     * Checks if the password passed matches the current staff object
     * @param pass Password to check against
     * @return
     */
    public Boolean verify(String pass) {
        if(Password.hash(pass).equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public void makeActive() {
        status = true;
        write();
    }

    public void makeInactive() {
        status = false;
        write();
    }

    public String getName() {
        return name;
    }

    /**
     * Converts staff object into a writable array
     * @return
     */
    private String[] stringify() {
        String[] returnStr = new String[6];
        returnStr[0] = Integer.toString(staffID);
        returnStr[1] = name;
        returnStr[2] = position;
        returnStr[3] = userName;
        returnStr[4] = password;
        returnStr[5] = Boolean.toString(status);

        return returnStr;
    }

 }
 