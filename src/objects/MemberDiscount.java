package objects;

public class MemberDiscount extends Discount{
    
    @Override
    public float getDiscount(Object o) {
        
        return 0.15f; //15% discount for member
    }
}