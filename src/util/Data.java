package util;
//imported packages
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
//exceptions
import java.io.FileNotFoundException;
import java.io.IOException;

///import Json
/**
 * Collection of data parsing functions  
 * For reading and writing to file
 */
public class Data {

    public static ArrayList<String[]> readCSV(String filePath) throws FileNotFoundException {
        //read the first line of the csv file
        //write each line as an array to the return arraylists
        ArrayList<String[]> returnArr = new ArrayList<String[]>();

        Scanner sc = new Scanner(new File(filePath));

        while(sc.hasNextLine()) {
            returnArr.add(sc.nextLine().split(","));
        }
        
        sc.close();
        return returnArr;
    }


    public static void writeCSV(ArrayList<String[]> writeArr, String path) throws IOException {
        String writeStr = "";

        for(int i=0; i<writeArr.size(); i++) {
            writeStr += String.join(",",writeArr.get(i));
            writeStr += "\n";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(writeStr);
        writer.close();
    }
}
