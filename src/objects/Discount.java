package objects;
/**
 * getDiscount is an overloadble method that can be extended for any trait 
 * returns associated discount with trait
 * @author Domi
 */
public abstract class Discount {
    public abstract float getDiscount(Object o);
    /**
     * No parameter invokes default method
     * @return 0.1 due to pre-set discount for parameter
     */
    public float getDefaultDiscount(){
        return 0.10f; //10% default
    }
    //extendable - just overload method for more object types and corr. discount
}
