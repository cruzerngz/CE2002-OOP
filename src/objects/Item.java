package objects;

import util.Colour;

public class Item {

    int id;
    String name;
    float price;
    String type;
    String allergy;
    Boolean recommend;

    /**
     * Constructor
     * @param arrayIn Array containing item attributes
     */
    public Item(String[] arrayIn) {
        id = Integer.parseInt(arrayIn[0]);
        name = arrayIn[1];
        price = Float.parseFloat(arrayIn[2]);
        type = arrayIn[3];
        allergy = arrayIn[4];
        recommend = Boolean.parseBoolean(arrayIn[5]);
    }

    /**
     * Creates a printable string array for display. 
     * Some parameters are removed
     * @return String array to be displayed
     */
    public String[] toPrintArray() {
        String[] returnStr = new String[5];
        returnStr[0] = Integer.toString(id);
        returnStr[1] = name;
        returnStr[2] = type;
        returnStr[3] = Float.toString(price);
        returnStr[4] = allergy;

        if(recommend) { //Mark in bright green
            returnStr[1] = Colour.Green(returnStr[1]);
        }

        return returnStr;
    }

    /**
     * Creates a writable string array 
     * to be written to file
     * @return String array to be written
     */
    public String[] toWriteArray() {
        String[] returnStr = new String[6];
        returnStr[0] = Integer.toString(id);
        returnStr[1] = name;
        returnStr[2] = Float.toString(price);
        returnStr[3] = type;
        returnStr[4] = allergy;
        returnStr[5] = Boolean.toString(recommend);

        return returnStr;
    }
}
