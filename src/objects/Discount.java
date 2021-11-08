package objects;

public class Discount {
    public static double getDiscount(Membership member){
        return 0.1; //10% discount for member
    }
    //extendable - just overload method for more object types and corr. discount
}
