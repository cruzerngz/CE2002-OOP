package objects;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import util.*;

/**
 * Object that contains sale statistics
 * @author cruzerngz
 */
public class SaleStats {
    private ArrayList<String[]> revMatrix;
    private String filePath = Path.revMatrix;
    private DecimalFormat dF = new DecimalFormat("0.00");
    
    /**
     * Loads sale information from data
     * @param path Path to the revenue matrix file
     */
    public SaleStats() {
        read();
    }

    /**
     * Get the revenue a stated day
     * @param epochDay Target day
     * @return Revenue
     */
    public float dayRevenue(int epochDay) {
        float result = 0;

        for(int i=0; i<revMatrix.size(); i++) {
            if(i==0) continue; //skip col headers
            if(epochDay == Integer.parseInt(revMatrix.get(i)[0])) {
                result += Float.parseFloat(revMatrix.get(i)[1]);
                break;
            }
        }
        return result;
    }

    /**
     * Get the revenue for past 30 days, inclusive
     * @param epochDay Target day
     * @return Revenue
     */
    public float monthRevenue(int epochDay) {
        float result = 0;

        for(int i=0; i<revMatrix.size(); i++) {
            if(i==0) continue; //skip col headers
            if(epochDay - Integer.parseInt(revMatrix.get(i)[0]) >= 0 && 
               epochDay - Integer.parseInt(revMatrix.get(i)[0]) < 30) {
                result += Float.parseFloat(revMatrix.get(i)[1]);
            }
        }
        return result;
    }

    /**
     * Get the revenue for a given range of days, inclusive
     * @param epochDayStart Starting day
     * @param epochDayEnd Ending day
     * @return Revenue
     */
    public float rangeRevenue(int epochDayStart, int epochDayEnd) {
        float result = 0;

        for(int i=0; i<revMatrix.size(); i++) {
            if(i==0) continue; //skip col headers
            //if in range, add
            if(Integer.parseInt(revMatrix.get(i)[0]) >= epochDayStart && 
               Integer.parseInt(revMatrix.get(i)[0]) <= epochDayEnd) {
                result += Float.parseFloat(revMatrix.get(i)[1]);
            }
        }
        return result;
    }

    /**
     * Add the revenue for a selected day
     * @param epochDay Target day
     * @param amount Revenue for that day
     * @return true if successful
     */
    public boolean addRevenue(int epochDay, float amount) {
        int i;
        for(i=0; i<revMatrix.size(); i++) { 
            if(i==0) continue; //ignoring col header

            //if match, write
            if(epochDay == Integer.parseInt(revMatrix.get(i)[0])) {
                amount += Float.parseFloat(revMatrix.get(i)[1]);
                revMatrix.get(i)[1] = Float.toString(amount);
                save();
                return true;
            }
        }
        //if no match, create new entry
        if(i==revMatrix.size()) {
            String[] writeStr = new String[]{
                Integer.toString(epochDay),Float.toString(amount)
            };
            revMatrix.add(writeStr);
            sort();
            return true;
        }
        return false;
    }

    /**
     * Subtract the revenue for a selected day
     * @param epochDay Target day
     * @param amount Revenue to subtract
     * @return true if successful
     */
    public boolean subRevenue(int epochDay, float amount) {
        int i;
        for(i=0; i<revMatrix.size(); i++) { 
            if(i==0) continue; //ignoring col header

            //if match, write
            if(epochDay == Integer.parseInt(revMatrix.get(i)[0])) {
                amount = Float.parseFloat(revMatrix.get(i)[1]) - amount;
                revMatrix.get(i)[1] = Float.toString(amount);
                save();
                return true;
            }
        }
        return false; //assume that days without any rev cannot have rev subtracted
    }

    /**
     * Delete ALL revenue for a selected day
     * @param epochDay Target day
     * @return true if successful, false if entry not found
     */
    public boolean delRevenue(int epochDay) {
        int i;
        for(i=0; i<revMatrix.size(); i++) { 
            if(i==0) continue; //ignoring col header

            //if match, remove
            if(epochDay == Integer.parseInt(revMatrix.get(i)[0])) {
                revMatrix.remove(i);
                save();
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a formatted array list for printing
     * @return printable array
     */
    public ArrayList<String[]> getPrintMatrix() {
        ArrayList<String[]> returnArr = new ArrayList<String[]>();
        DateTime dt = new DateTime();
        //build the return array
        for(int i=0; i<revMatrix.size(); i++) {
            if(i==0) {
                returnArr.add(revMatrix.get(0).clone());
            } else {
                returnArr.add(new String[]{
                    dt.daysToDayDate(Integer.parseInt(revMatrix.get(i).clone()[0])),
                    "$" + dF.format(
                        Float.parseFloat(revMatrix.get(i).clone()[1])
                    )
                });
            }
        }
        returnArr.get(0)[0] = "date"; //change header from "day" to "date"

        //sum up all
        float total = 0;
        for(int i=0; i<revMatrix.size(); i++) {
            if(i==0){continue;}
            total += Float.parseFloat(revMatrix.get(i)[1]);
        }
        returnArr.add(new String[]{
            Colour.Green("total"), Colour.Green(String.format("$%.2f", total))
        });
        return returnArr;
    }

    /**
     * Clear all data
     */
    public void reset() {
        ArrayList<String[]> newMatrix = new ArrayList<String[]>();
        newMatrix.add(revMatrix.get(0).clone()); //get the col headers
        //newMatrix.add(new String[]{"0","0"}); //put 1 empty line

        revMatrix = newMatrix;
        save();
    }

    /**
     * Sorts the entries by day, then writes to file
     */
    private void sort() {

        if(revMatrix.size() <= 2) return; //no need to sort

        //using insertionsort
        for(int i=2; i<revMatrix.size(); i++) {
            for(int j=i; j>1; j--) {
                //if index j is smaller than j-1, swap
                if(Integer.parseInt(revMatrix.get(j)[0]) < 
                   Integer.parseInt(revMatrix.get(j-1)[0])) {
                    Collections.swap(revMatrix, j, j-1);
                }
            }
        }
        save();
    }

    /**
     * Reads the data from file
     */
    private void read() {
        revMatrix = Data.readCSV(filePath);
    }

    /**
     * Writes the data to file
     */
    private void save() {
        Data.writeCSV(revMatrix, filePath);
    }
}
