import ui.*;
import util.Data;

import java.util.ArrayList;

import objects.*;

public class debugStaff {
    public static void main(String[] args) {
        // Staff newStaff = new Staff(2, "Ben", "waiter", "ben", "0000");
        // System.out.println(newStaff.isRegistered());
        // newStaff.write();
        // System.out.println(newStaff.isRegistered());
        // newStaff.remove();

        // Staff staff2 = new Staff("Ali");
        // System.out.println(staff2.isRegistered());
        addStaff();
        ArrayList<String[]> tempArr = Data.readCSV("../data/Staffroster.csv");
        Data.printArrayList(tempArr);

    }

    public static void addStaff() {
        Staff[] newMembers = new Staff[5];
        newMembers[0] = new Staff(1,"Ali","cook","ali", "0001");
        newMembers[1] = new Staff(2,"Ben","waiter","ben","0002");
        newMembers[2] = new Staff(3,"Mat","waiter","mat","0003");
        newMembers[3] = new Staff(4,"Ter","cleaner","ter","0004");
        newMembers[4] = new Staff(5,"Tim","dishwasher","tim","0005");

        for(Staff member: newMembers) {
            member.write();
        }
        newMembers[4].makeActive();
        // newMembers[3].remove();
    }
}
