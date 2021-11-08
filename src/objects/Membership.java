package objects;

public class Membership {
    private boolean isMember;
    
    public Membership(boolean isMember){
        this.isMember = isMember;
    }

    public boolean getMember(){ //for any callee receiving object to retrieve
    return isMember;
    }
}
