package objects;
/**
 * Simple class for restaurant membership
 * object is created for passing into discount, which returns discount rate based on object type passed in
 */
public class Membership {
    private boolean isMember;
    /**
     * constructor to set value to true
     * @param isMember
     */
    public Membership(boolean isMember){
        this.isMember = isMember;
    }

    /**
     * Getter method for membership variable
     * @return True means customer falls under class benefit 
     */
    public boolean getMember(){ //for any callee receiving object to retrieve
        return isMember;
    }
}
