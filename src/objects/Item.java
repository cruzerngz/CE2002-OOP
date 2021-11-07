package objects;

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
}
