package objects;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.regex.PatternSyntaxException;

import util.*;

/**
 * Object that contains sale statistics
 * @author cruzerngz
 */
public class SaleStats {
    private ArrayList<String[]> revMatrix;
    private ArrayList<String[]> menuArr;
    private String filePath = Path.revMatrix;
    private DecimalFormat dF = new DecimalFormat("0.00");
    
    /**
     * Loads sale information from data
     */
    public SaleStats() {
        read();
    }

    /**
     * Get the revenue a stated day
     * @param epochDay Target day
     * @return Revenue
     */
    public ArrayList<String[]> dayRevenue(int epochDay) {
        //set the headers for returnArr
        ArrayList<String[]> returnArr = new ArrayList<String[]>();
        returnArr.add(new String[]{
            "item","count","sales"
        });

        String[] row = null;
        for(int i=0; i<revMatrix.size(); i++) {
            if(i==0) continue; //skip col headers
            if(epochDay == Integer.parseInt(revMatrix.get(i)[0])) {
                row = revMatrix.get(i);
                break;
            }
        }
        if(row == null) return null; //return nothing if no match
        ArrayList<String[]> dataArr = parseStringToArrayList(row[1]);
        dataArr = Data.sortRevFullArrayList(dataArr);
        // Data.printArrayList(dataArr);

        //building the return arr
        for(String[] dataRow: dataArr) {
            String[] menuRow = getMenuItem(dataRow[1]);
            String[] returnRow = new String[3];
            returnRow[0] = menuRow[1];
            returnRow[1] = dataRow[0];
            returnRow[2] = Float.toString(
              Float.parseFloat(menuRow[2]) * Integer.parseInt(returnRow[1]) * 1.17f
            );
            returnArr.add(returnRow);
        }
        // Data.printArrayList(returnArr);
        
        //adding total row to bottom
        String[] totalRow = new String[3];
        float total = 0f;
        for(int i=0; i<returnArr.size(); i++) {
            if(i==0) {continue;}
            total += Float.parseFloat(returnArr.get(i)[2]);
        }
        totalRow[0] = "total";
        totalRow[1] = "";
        totalRow[2] = Float.toString(total);
        returnArr.add(totalRow);

        // Data.printArrayList(returnArr);

        return formatReport(returnArr);
    }

    /**
     * Get the revenue for a given range of days, inclusive
     * @param epochDayStart Starting day
     * @param epochDayEnd Ending day
     * @return Revenue
     */
    public ArrayList<String[]> rangeRevenue(int epochDayStart, int epochDayEnd) {
        //setting headers for returnArr
        ArrayList<String[]> returnArr = new ArrayList<String[]>();
        returnArr.add(new String[]{
            "item","count","sales"
        });
        String itemStr = null;

        for(int i=0; i<revMatrix.size(); i++) {
            if(i==0) continue; //skip col headers
            //if in range, add to item string
            if(Integer.parseInt(revMatrix.get(i)[0]) >= epochDayStart && 
               Integer.parseInt(revMatrix.get(i)[0]) <= epochDayEnd) {

                    if(itemStr == null) {
                        ArrayList<String[]> tempArrList = parseStringToArrayList(revMatrix.get(i)[1]);
                        ArrayList<String> tempArr = new ArrayList<String>();
                        for(String[] row: tempArrList) {
                            tempArr.add(String.join("*", row));
                        }
                        itemStr = String.join(".", tempArr);
                    } else {
                        itemStr = addTo(itemStr, revMatrix.get(i)[1]);
                    }
                    
            }
        }
        // System.out.println(itemStr);
        //return null if no entry
        if(itemStr == null) {return null;}
        //continue if have entries
        ArrayList<String[]> dataArr = parseStringToArrayList(itemStr);
        dataArr = Data.sortRevFullArrayList(dataArr);


        //building the return arr
        for(String[] dataRow: dataArr) {
            String[] menuRow = getMenuItem(dataRow[1]);
            String[] returnRow = new String[3];
            returnRow[0] = menuRow[1]; //item
            returnRow[1] = dataRow[0]; //count
            returnRow[2] = Float.toString( //sales for that item
              Float.parseFloat(menuRow[2]) * Integer.parseInt(dataRow[0]) * 1.17f
            );

            returnArr.add(returnRow);
            
        }
        
        //adding total row to bottom
        String[] totalRow = new String[3];
        float total = 0f;
        for(int i=0; i<returnArr.size(); i++) {
            if(i==0) {continue;}
            total += Float.parseFloat(returnArr.get(i)[2]);
        }
        totalRow[0] = "total";
        totalRow[1] = "";
        totalRow[2] = Float.toString(total);
        returnArr.add(totalRow);

        return formatReport(returnArr);
    }

    /**
     * Add the revenue for a selected day
     * @param epochDay Target day
     * @param amount Revenue for that day
     * @return true if successful
     */
    public boolean addRevenue(int epochDay, String items) {
        int i;
        for(i=0; i<revMatrix.size(); i++) { 
            if(i==0) continue; //ignoring col header

            //if match, write
            if(epochDay == Integer.parseInt(revMatrix.get(i)[0])) {
                String[] row = revMatrix.get(i);
                row[1] = addTo(row[1], items);
                save();
                return true;
            }
        }
        //if no match, create new entry
        if(i==revMatrix.size()) {
            ArrayList<String[]> temp = parseStringToArrayList(items);
            ArrayList<String> tempItems = new ArrayList<String>();
            for(String[] row: temp) {
                tempItems.add(String.join("*", row));
            }

            String[] writeStr = new String[]{
                Integer.toString(epochDay), String.join(".",tempItems)
            };
            revMatrix.add(writeStr);
            sort();
            return true;
        }
        return false;
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
     * @Depreceated
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
     * Adds orders to the target
     * @param target
     * @param addition
     * @return Formatted target
     */
    private String addTo(String target, String addition) {
        ArrayList<String[]> addArr = parseStringToArrayList(addition);
        ArrayList<String[]> targetArr = parseStringToArrayList(target);

        for(String[] item: addArr) {
            Boolean complete = false;
            int i;
            //increment the count if match
            for(i=0; i<targetArr.size(); i++) {
                if(item[1].equals(targetArr.get(i)[1])) {
                    String[] temprow = targetArr.get(i);
                    int x = Integer.parseInt(temprow[0]);
                    x += Integer.parseInt(item[0]);
                    temprow[0] = Integer.toString(x);

                    targetArr.set(i, temprow);
                    complete = true;
                }
            }
            //add new line if no match
            if(!complete) {
                targetArr.add(item);
            }
        }

        ArrayList<String> temp = new ArrayList<String>();
        for(String[] row: targetArr) {
            temp.add(String.join("*", row));
        }
        return String.join(".", temp);
    }

    /**
     * Convert dot-separated elements into an arraylist
     * @param stringIn
     * @return
     */
    public static ArrayList<String[]> parseStringToArrayList(String stringIn) {
        ArrayList<String[]> returnArr = new ArrayList<String[]>();
        for(String item:stringIn.split("\\.")) {
            String[] temp;
            //add to arrayList
            if(item.split("\\*").length == 1) {
                temp = new String[]{
                    "1",item
                };
            } else {
                temp = item.split("\\*");
            }
            returnArr.add(temp);
        }
        return returnArr;
    }

    private String[] getMenuItem(String itemID) {
        read();
        for(String[] row: menuArr) {
            if(row[0].equals(itemID)) {
                return row;
            }
        }
        return null;
    }

    private ArrayList<String[]> formatReport(ArrayList<String[]> report) {
        ArrayList<String[]> returnArr = Data.deepCopy(report);
        LinkedHashMap<String, String[]> returnMap = Data.parse(returnArr);
        
        String[] salesCol = returnMap.get("sales");

        for(int i=0; i<salesCol.length; i++) {
            float temp = Float.parseFloat(salesCol[i]);
            salesCol[i] = String.format("$%.2f", temp);
        }
        returnMap.put("sales", salesCol);

        int max = returnArr.size() - 1;
        String[] newItem = returnMap.get("item");
        newItem[max] = Colour.Green(newItem[max]);
        returnMap.put("item", newItem);
        String[] newSales = returnMap.get("sales");
        newSales[max] = Colour.Green(newSales[max]);
        returnMap.put("sales", newSales);
        

        return Data.parse(returnMap);
    }

    /**
     * Sorts the entries by day, then writes to file
     */
    private void sort() {
        revMatrix = Data.sortArrayList(revMatrix);
        save();
    }

    /**
     * Reads the data from file
     */
    private void read() {
        revMatrix = Data.readCSV(filePath);
        menuArr = Data.readCSV(Path.menu);
    }

    /**
     * Writes the data to file
     */
    private void save() {
        Data.writeCSV(revMatrix, filePath);
    }
}
