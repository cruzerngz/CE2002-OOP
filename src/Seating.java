public class Seating {
    /**
     * Restaurant is made up of many tables
     * 
     * @author Koh Yu Ze
     * 
     * 
     */

    /**
     * There are 20 tables in this restaurant
     */
    private String date = new String();
    private Table[] table = new Table[20];
    private int numEmptyTable = 20;

    /**
     * Numbers the tables from 1 to 20, inclusive
     * @param date
     */
    public Seating(String date) {
        for (int i = 0; i < 20; ++i) {
            table[i] = new Table(i + 1);
        }
        this.date = date; //assigns a date to this Seating
    }

    /**
     * 
     * @return the date attribute of this seating
     */
    public String getDate() {
        return (date);
    }

    /**
     * Prints number of empty tables
     */
    public void showNumEmptyTable() {
        System.out.println("There are " + numEmptyTable + " empty tables");
    }

    /**
     * Prints the assigned tables
     */
    public void showAssignedTable() {
        for (int i = 0; i < table.length; ++i) {
            if (table[i].isOccupied()) {
                System.out.println(
                        "Table " + table[i].getTableNo() + " occupied by order " + table[i].getOrderID() + ".");
            }
        }

    }

    /**
     * Method to assign table. Provide table number and order number.
     */
    public void assignTable(int tableNo, int orderID) {
        if (table[tableNo - 1].isOccupied()) { // the array position is 1 less than actual seatId
            System.out.println("Table already assigned to a customer.");
        } else {
            table[tableNo - 1].assign(orderID);
            numEmptyTable -= 1;
            System.out.println("Table Assigned!");
        }
    }

    /**
     * Method to unassign table
     */
    public void unassignTable(int tableNo) {
        table[tableNo - 1].unassign();
        numEmptyTable += 1;

    }


    /**
     * Method to reserve table. Provide table number, phone and time
     */
    public void reserveTable(int tableNo, int time, String cust_name, int phoneNo) {
            table[tableNo - 1].reserve(phoneNo, time, cust_name);
    }

    /**
     * Method to unassign table
     */
    public void unreserveTable(int tableNo, int time) {
        table[tableNo - 1].unreserve(time);
    }
    
    /**
     * Prints the reservation status
     */
    public void showReservationStatus() {
        for (int i = 0; i < table.length; ++i) {
            table[i].reservedStatus();
        }
    }
}