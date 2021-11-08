package util;

//imported packages
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.lang.Math;
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
     * Format the arrayList so all elements have an equal number of characters
     * @param arr ArrayList to be formatted
     */
    private static ArrayList<String[]> formatArrayList(ArrayList<String[]> arr) {
        int[] lengthArr = new int[arr.get(0).length];  //array for determining max length of each col
        Arrays.fill(lengthArr, 0);                          //fill the array with 0s

        int rows = arr.size();         //no of rows
        int cols = arr.get(0).length;  //no of cols
        String regex = "\\e\\[[0-9]+m";
        String temp;

        //build the max lengthArr
        for(int i=0; i<cols; i++) {     //per col
            for(int j=0; j<rows; j++) { //per row
                temp = arr.get(j)[i].replaceAll(regex, ""); //remove special chars when counting
                lengthArr[i] = Math.max(temp.length(), lengthArr[i]);
            }
        }

        //pad all elements to max length
        for(int i=0; i<rows; i++) {     //per row
            for(int j=0; j<cols; j++) { //per col
                arr.get(i)[j] = String.format("%"+(-lengthArr[j])+"s", arr.get(i)[j]);
            }
        }

        return arr;
    }

    /**
     * Print an arrayList
     * @param printArr ArrayList to be printed
     */
    public static void printArrayList(ArrayList<String[]> printArr) {

        int rows = printArr.size();
        int cols = printArr.get(0).length;

        //System.out.println(Integer.toString(rows)+ " "+Integer.toString(cols));

        //format the arrayList first
        printArr = formatArrayList(printArr);

        //print the array
        //each element separated by space + pipe
        //additional decorative elements add here
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                System.out.print(printArr.get(i)[j] + " | ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Convert an ArrayList into a map.
     * The first array (interpreted as column headers) is converted into HashMap keys.
     * The data entries are String arrays behind each header (key).
     */
    public static LinkedHashMap<String, String[]> parse(ArrayList<String[]> arrIn) {

        int rows = arrIn.size();        //number of rows
        int cols = arrIn.get(0).length; //number of cols
        LinkedHashMap<String, String[]> returnMap = new LinkedHashMap<String, String[]>();

        String[] colArr = arrIn.get(0); //get the col names, top row
        String[] colData = new String[rows-1];
        //build the hashmap
        for(int i=0; i<colArr.length; i++) {

            //build col data first
            Arrays.fill(colData, ""); //empty array
            for(int j=0; j<colData.length; j++) {
                colData[j] = arrIn.get(j+1)[i];
            }
            //add to hash map
            System.out.print(colArr[i] + " ");
            System.out.print(Arrays.toString(colData) + "\n");
            returnMap.put(colArr[i], colData);
        }

        return returnMap;
    }

    public static ArrayList<String[]> parse(LinkedHashMap<String, String[]> mapIn) {

        ArrayList<String[]> returnArr = new ArrayList<String[]>();
        int cols = mapIn.size();
        int rows = 0;
        String[] rowArr = new String[cols];
        int index;

        for(String key: mapIn.keySet()) { //iterating once
            rows = mapIn.get(key).length + 1;
        }
        
        rowArr = mapIn.keySet().toArray(rowArr);
        returnArr.add(rowArr);

        System.out.println(Integer.toString(rows) + " " + Integer.toString(cols));

        for(int i=0; i<rows-1; i++) {
            //construct rowArr first
            index = 0; //reset index
            Arrays.fill(rowArr, ""); //empty array
            for(String key: mapIn.keySet()) {
                rowArr[index] = mapIn.get(key)[i];
                index++;
            }
            //add to array list
            returnArr.add(rowArr);
        }

        return returnArr;
    }
}