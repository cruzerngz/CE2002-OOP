package util;

//imported packages
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//exceptions
import java.io.FileNotFoundException;
import java.io.IOException;

///import Json
/**
 * Collection of data parsing functions 
 * For reading and writing to file
 * @author cruzerngz
 */
public class Data {

    /**
     * Reads csv to an arrayList of String arrays
     * @param filePath Relative path to file
     * @return ArrayList containing CSV data
     * @throws FileNotFoundException
     */
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

    /**
     * Writes arrayList to a CSV file
     * @param writeArr Data to be written
     * @param filePath Relative path to file
     * @throws IOException
     */
    public static void writeCSV(ArrayList<String[]> writeArr, String filePath) throws IOException {
        String writeStr = "";

        for(int i=0; i<writeArr.size(); i++) {
            writeStr += String.join(",",writeArr.get(i));
            writeStr += "\n";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(writeStr);
        writer.close();
    }

    /**
     * Convert an ArrayList into a map.
     * The first array (interpreted as column headers) is converted into HashMap keys.
     * The data entries are String arrays behind each header (key).
     */
    public static HashMap<String, String[]> parse(ArrayList<String[]> arrIn) {

        int rows = arrIn.size();        //number of rows
        int cols = arrIn.get(0).length; //number of cols
        HashMap<String, String[]> returnMap = new HashMap<String, String[]>();
        String[] tempData = new String[rows-1];
        String tempKey;

        //build the HashMap
        for(int i=0; i<cols; i++) {
            Arrays.fill(tempData, ""); //reset elements in array

            //build column data
            for(int j=0; j<rows-1; j++) {
                tempData[j] = arrIn.get(j+1)[i];
            }
            tempKey = arrIn.get(0)[i];
            System.out.println(tempKey + Arrays.toString(tempData));

            //add to map
            returnMap.put(arrIn.get(0)[i], tempData);
            for(String thing: returnMap.keySet()) {
                System.out.println(thing + " " + Arrays.toString(returnMap.get(thing)));
            }   
        }
        return returnMap;
    }

    public static ArrayList<String[]> parse(HashMap<String, String[]> mapIn) {

        ArrayList<String[]> returnArr = new ArrayList<String[]>();


        return returnArr;
    }
}