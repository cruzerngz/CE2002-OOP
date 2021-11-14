import ui.*;
import util.Data;

import java.util.ArrayList;

import objects.*;

public class debugStaff {
    public static void main(String[] args) {

        // addStaff();
        // StaffRoster.firing("Ali");
        // StaffRoster.firing("Tan");
        // StaffRoster.firing("Name");

        Staff newS = new Staff("name");

        // StaffRoster.showHired();
        AdminUI admin = new AdminUI();
        admin.printOptions();

    }

    public static void addStaff() {
        Staff[] newMembers = new Staff[6];
        newMembers[0] = new Staff(1,"Ali","cook","ali", "0001");
        newMembers[1] = new Staff(2,"Ben","waiter","ben","0002");
        newMembers[2] = new Staff(3,"Mat","waiter","mat","0003");
        newMembers[3] = new Staff(4,"Ter","cleaner","ter","0004");
        newMembers[4] = new Staff(5,"Tim","dishwasher","tim","0005");
        newMembers[5] = new Staff(6,"Tan","manager","tan","0006");


        for(Staff member: newMembers) {
            member.write();
        }
        
        // System.out.println(newMembers[5].verify("0006"));
    }
}
