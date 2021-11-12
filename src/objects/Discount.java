package objects;
/**
 * getDiscount is an overloadble method that can be extended for any trait 
 * returns associated discount with trait
 */
public class Discount {
    /**
     * 
     * @param member; an example of a trait that provides certain discount
     * @return 0.1 due to pre-set discount for parameter
     */
    public static double getDiscount(Membership member){
        return 0.1; //10% discount for member
    }
    //extendable - just overload method for more object types and corr. discount
}
