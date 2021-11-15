package util;

//imported packages
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.lang.Math;
//exceptions
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Collection of data parsing functions 
 * @author cruzerngz
 */
public class Data {

    /**
     * Reads csv to an arrayList of String arrays
     * @param filePath Relative path to file
     * @return ArrayList containing CSV data
     */
    public static ArrayList<String[]> readCSV(String filePath) {
        //read the first line of the csv file
        //write each line as an array to the return arraylists
        ArrayList<String[]> returnArr = new ArrayList<String[]>();

        Scanner sc = null;
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        while(sc.hasNextLine()) {
            returnArr.add(sc.nextLine().split(",", -1));
        }
        
        sc.close();
        return returnArr;
    }

    /**
     * Writes arrayList to a CSV file
     * @param writeArr Data to be written
     * @param filePath Relative path to file
     */
    public static void writeCSV(ArrayList<String[]> writeArr, String filePath) {
        String writeStr = "";

        for(int i=0; i<writeArr.size(); i++) {
            writeStr += String.join(",",writeArr.get(i));
            writeStr += "\n";
        }

        BufferedWriter writer = null;
        try { 
            writer = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            writer.write(writeStr);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

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

        //build the max lengthArr
        for(int i=0; i<cols; i++) {     //per col
            for(int j=0; j<rows; j++) { //per row
                String temp = arr.get(j)[i].replaceAll(regex, ""); //remove special chars when counting
                lengthArr[i] = Math.max(temp.length(), lengthArr[i]);
            }
        }

        //pad all elements to max length
        for(int i=0; i<rows; i++) {     //per row
            for(int j=0; j<cols; j++) { //per col
                String temp = arr.get(i)[j].replaceAll(regex, "");
                
                //padding for coloured text
                int padding = lengthArr[j] - temp.length();
                for(int k=0; k<padding; k++) {
                    arr.get(i)[j] = arr.get(i)[j] + " ";
                }

                arr.get(i)[j] = String.format("%"+(-lengthArr[j])+"s", arr.get(i)[j]);
            }
        }

        return arr;
    }

    /**
     * Print an arrayList.
     * Original arrayList is not modified
     * @param arrIn ArrayList to be printed
     */
    public static void printArrayList(ArrayList<String[]> arrIn) {

        ArrayList<String[]> printedArr = deepCopy(arrIn); //actual array to be printed
        
        int rows = printedArr.size();
        int cols = printedArr.get(0).length;

        //format the arrayList first
        printedArr = formatArrayList(printedArr);

        //print the array
        //each element separated by space + pipe
        //additional decorative elements add here
        for(int i=0; i<rows; i++) {
            if(i==0) { //highlight the header row
                Colour.print(Colour.TEXT_BLACK + Colour.BG_GREEN, "|  ");
                for(int j=0; j<cols; j++) {
                    Colour.print(Colour.TEXT_BLACK + Colour.BG_GREEN, printedArr.get(i)[j]);
                    if(j != (cols - 1)) {
                        Colour.print(Colour.TEXT_BLACK + Colour.BG_GREEN,"  |  ");
                    } else {
                        Colour.print(Colour.TEXT_BLACK + Colour.BG_GREEN,"  |");
                    }
                }
                System.out.print("\n");
            } else { //print the rest as-is
                System.out.print("|  ");
                for(int j=0; j<cols; j++) {
                    System.out.print(printedArr.get(i)[j]);
                    if(j != (cols - 1)) {
                        System.out.print("  |  ");
                    } else {
                        System.out.print("  |");
                    }
                }
                System.out.print("\n");
            }

        }
    }

    /**
     * Sorts arraylist by first column index
     * Assumes that first row are col headers
     * Assumes that the first column contains integers
     * @param arrIn Array list to be sorted
     * @return Sorted array list
     */
    public static ArrayList<String[]> sortArrayList(ArrayList<String[]> arrIn) {
        ArrayList<String[]> returnArr = deepCopy(arrIn);

        //sort by first col
        //assume that first col is number
        for(int i=2; i<returnArr.size(); i++) {
            for(int j=i; j>1; j--) {
                if(Integer.parseInt(returnArr.get(j)[0]) < 
                   Integer.parseInt(returnArr.get(j-1)[0])) {

                    Collections.swap(returnArr, j, j-1);
                }
            }
        }
        return returnArr;
    }

        /**
     * Sorts arraylist by first column. 
     * Assumes that the first column contains integers
     * Assumes ALL col fields are integers
     * @param arrIn Array list to be sorted
     * @return Sorted array list
     */
    public static ArrayList<String[]> sortRevFullArrayList(ArrayList<String[]> arrIn) {
        ArrayList<String[]> returnArr = deepCopy(arrIn);

        //sort by first col
        for(int i=1; i<returnArr.size(); i++) {
            for(int j=i; j>0; j--) {
                if(Integer.parseInt(returnArr.get(j)[0]) > 
                   Integer.parseInt(returnArr.get(j-1)[0])) {

                    Collections.swap(returnArr, j, j-1);
                }
            }
        }
        return returnArr;
    }

    /**
     * Creates a deep copy of all items inside an array list
     * @param arrIn Array to be copied
     * @return Copied array
     */
    public static ArrayList<String[]> deepCopy(ArrayList<String[]> arrIn) {
        ArrayList<String[]> returnArr = new ArrayList<String[]>();

        for(int i=0; i<arrIn.size(); i++) {
            returnArr.add(arrIn.get(i).clone());
        }

        return returnArr;
    }

    /**
     * Convert an ArrayList into a map.
     * The first array (interpreted as column headers) is converted into HashMap keys.
     * The data entries are String arrays behind each header (key).
     * @param arrIn ArrayList to be converted
     * @return Linked HashMap
     */
    public static LinkedHashMap<String, String[]> parse(ArrayList<String[]> arrIn) {

        LinkedHashMap<String, String[]> returnMap = new LinkedHashMap<String, String[]>();
        returnMap.clear();

        String[] colArr = arrIn.get(0); //get the col names, top row
        arrIn.remove(0);

        //build the hashmap
        //every loop needs to initialise a new array
        //or the resulting arrays will all be duplicated
        for(int i=0; i<colArr.length; i++) {

            //build col data first
            String[] colData = new String[arrIn.size()]; //empty array
            for(int j=0; j<colData.length; j++) {
                colData[j] = arrIn.get(j)[i];
            }

            //add to hash map
            returnMap.putIfAbsent(colArr[i], colData);
        }
        return returnMap;
    }

    /**
     * Convert a map into an ArrayList. 
     * Map keys are interpreted as column headers when converted into ArrayList. 
     * @param mapIn Map to be converted
     * @return ArrayList
     */
    public static ArrayList<String[]> parse(LinkedHashMap<String, String[]> mapIn) {

        ArrayList<String[]> returnArr = new ArrayList<String[]>();
        int cols = mapIn.size();
        int rows = 0;
        String[] rowArr = new String[cols];
        int index;

        for(String key: mapIn.keySet()) { //iterating once
            rows = mapIn.get(key).length + 1;
            break;
        }
        
        rowArr = mapIn.keySet().toArray(rowArr);
        returnArr.add(rowArr);

        //every loop needs to initialise a new array
        //or the resulting arrays will all be duplicated
        for(int i=0; i<rows-1; i++) {
            String[] tempArr = new String[cols];
            //construct tempArr first
            index = 0; //reset index
            for(String key: mapIn.keySet()) {
                tempArr[index] = mapIn.get(key)[i];
                index++;
            }
            //add to array list
            returnArr.add(tempArr);
        }
        return returnArr;
    }
}
