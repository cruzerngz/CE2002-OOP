package objects;

public class MemberDiscount extends Discount{
    
    @Override
    public float getDiscount(Object o) {
        if(o instanceof Membership) //check type in case
        {
            return 0.15f; //15% discount for member
        }
        else return 0f; //if fail just say no discount
    }
}