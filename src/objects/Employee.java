package objects;
//imports
import util.Password;
//exceptions
import java.security.NoSuchAlgorithmException;

/**
 * Object containing employee information
 * @author cruzerngz
 */
public class Employee {
    int id;
    String name;
    String position;
    int empID;
    private String hash; //password hash
    Boolean available;
    Boolean employed;

    public Employee(String[] arrayIn) {
        id = Integer.parseInt(arrayIn[0]);
        name = arrayIn[1];
        position = arrayIn[2];
        empID = Integer.parseInt(arrayIn[3]);
        hash = arrayIn[4];
        available = Boolean.parseBoolean(arrayIn[5]);
        employed = Boolean.parseBoolean(arrayIn[6]);
    }

    /**
     * Formats the object data into a writable array
     * @return String array to be written to file
     */
    public String[] toArray() {
        String[] returnStr = new String[7];

        returnStr[0] = Integer.toString(id);
        returnStr[1] = name;
        returnStr[2] = position;
        returnStr[3] = Integer.toString(empID);
        returnStr[4] = hash;
        returnStr[5] = Boolean.toString(available);
        returnStr[6] = Boolean.toString(employed);

        return returnStr;
    }

    /**
     * Checks if hashed password matches that of the employee
     * @param hashIn Password hash to be checked
     * @return True / False
     * @throws NoSuchAlgorithmException
     */
    public Boolean isHash(String pwd) throws NoSuchAlgorithmException {

        if(Password.hash(pwd) == hash) return true;
        else return false;
    }
}
