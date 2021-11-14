import util.*;

import java.util.ArrayList;
import java.util.Scanner;

import ui.*;
import objects.*;
import util.*;


public class Debug2 {
    public static void main(String[] args) {
        MenuSetting temp = new MenuSetting();

        // temp.printOptions();

        ArrayList<String[]> menu = Data.readCSV(Path.menu);
        menu = MenuSetting.formatMenu(menu);
        Data.printArrayList(menu);
    }

}

//for i in array:
//    do sth with i