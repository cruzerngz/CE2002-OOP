package objects;
public class Table {

    /** Table class. 
     * This is the individual tables.
     * I'm assuming all tables have the same number of seats.
     * Restaurant is open from 1100 to 2000hrs. Last reservation at 2000hrs.
     * @author Koh Yu Ze
    */

    /**
     * The number that is allocated to the table.
     */
    private int tableNo;
    private String date;

    /**
     * Whether the table has been reserved
     */
    private boolean[] reserved = new boolean[] {
        false, false, false, false, false, false, false, false, false, false
    };

    /**
     * Phone number of customer who reserved the table. If not reservation, put -1
     */
    private int[] customerPhone = new int[] {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    /**
     * Name of customer who reserved the table. If not reservation, put 'None'
     */
    private String[] customerName = new String[] {
        "None", "None", "None", "None", "None", "None", "None", "None", "None", "None"
    };

    /**
     * Whether the table has been occupied
     */
    private boolean occupied = false;

    /**
     * Order number that is associated with this table. If not occupied/no order, then put -1
     */
    private int orderID = -1;



    
    public Table(int table_No, String date){ 
        tableNo = table_No;
        this.date = date;
    }

    /**
     * Accessor for table number
     *
     * @return integer containing table number
     */
    public int getTableNo(){ 
        return (tableNo);
    }

    /**
     * A method to check the order number that is associated with this table.
     *
     * @return a int value of the order number that is associated with this table.
     */
    public int getOrderID(){ 
        return (orderID);
    }

    /**
     * A method to check if a table is occupied.
     *
     * @return a boolean value determining whether table is occupied.
     */
    public boolean isOccupied(){ 
        return (occupied);
    }


    /**
     * A method to check if a table has already been reserved
     *
     * @return a boolean value determining whether table has been reserved.
     */
    public void reservedStatus(){ 
        boolean reservation_exists = false;
        for (int i = 0; i < reserved.length; ++i){
            if (reserved[i] == true){ reservation_exists = true;}
        }
        if (reservation_exists){
            System.out.printf("Table "+tableNo+ " is reserved at: " );
            for (int i = 0; i < reserved.length; ++i){
                if (reserved[i] == true){
                    System.out.printf((i+11)*100 + "hrs ");         
                }
            }
            System.out.printf("\n");
        }
        else{
            System.out.println("Table "+tableNo+ " has no reservations" );
        }
    }

    /**
     * Prints out who is reserving this table at the given time.
     * @param time
     */
    public void checkReservation(int time){ 
        if (reserved[time/100 - 11] == true){
            System.out.println("Table " + tableNo 
            + " reserved by " + customerName[time/100 - 11] 
            + " (Phone number: " + customerPhone[time/100 - 11]
            + ")");
        }
    }

    
    /**
     * A method to reserve this table object
     * @param phoneNumber
     * @param time
     * @param cust_name
     */
    public void reserve(int phoneNumber, int time, String cust_name){ 
        if (reserved[time/100 - 11] == true){
            System.out.println("This table is already reserved by " 
            + customerName[time/100 - 11] 
            + " (Phone number: "
            + customerPhone[time/100 - 11]
            + ") at "
            + date + " "
            + time + "hrs.");
        }
        else{
            customerPhone[time/100 - 11] = phoneNumber;
            customerName[time/100 - 11] = cust_name;
            reserved[time/100 - 11] = true;
            System.out.println("Table " + tableNo
            + " reserved at "
            + date + " "
            + time + "hrs for "
            + customerName[time/100 - 11] 
            + " (Phone number: " + customerPhone[time/100 - 11]
            + ")."
            );
        }   
    }

    /**
     * This separate reserve method is used for initializing, when the data from the CSV is read.
     * @param phoneNumber
     * @param time
     * @param cust_name
     */
    public void reserve_csv(int phoneNumber, int time, String cust_name){ 
        if (reserved[time/100 - 11] == true){
            System.out.println("Check csv for overlapping");
        }
        else{
            customerPhone[time/100 - 11] = phoneNumber;
            customerName[time/100 - 11] = cust_name;
            reserved[time/100 - 11] = true;
        }   
    }


    /**
     * A method to assign the table to a customer
     * Customers have no IDs, so when table is assigned, we associate the table with the orderID
     * @param order
     */
    public void assign(int order){ 
        occupied = true;
        orderID = order;
    }

    /**
     * A method to unreserve the table
     * @param time the time that the reservation was made for
     */
    public void unreserve(int time){ 
        reserved[time/100 - 11] = false;
        customerPhone[time/100 - 11] = -1;
        customerName[time/100 - 11] = "None";
        System.out.println("Table " + tableNo
            + " unreserved at "
            + time
            + "hrs"
            );
    }
    
    /**
     * A method to check whether this table is reserved
     * @param time
     * @return a boolean value whether this table is reserved at this time.
     */
    public boolean isReserved(int time){ 
        return (reserved[time/100 - 11]);
    }

     /**
     * A method to unassign the table
     */
    public void unassign(){ 
        occupied = false;
        orderID = -1;
        System.out.println("Table Unassigned.");
    }
    
}
