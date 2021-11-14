package util;
//imports
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
//exceptions
import java.security.NoSuchAlgorithmException;

/**
 * Hashing class for passwords
 */
public class Password {
    
    /**
     * Hashes a given string using SHA256
     * @param input String to be hashed
     * @return 256-bit result
     */
    private static byte[] SHA256(String input) {
        MessageDigest msg = null;
        try {
            msg = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        byte[] hash;
        hash = msg.digest(input.getBytes(StandardCharsets.UTF_8));

        return hash;
    }

    /**
     * Converts bytes into a printable string
     * @param hash Byte array
     * @return 64 char string
     */
    private static String toString(byte[] hash) {
        BigInteger x = new BigInteger(1,hash);
        StringBuilder hex = new StringBuilder(x.toString(16));
        while(hex.length() < 32) {
            hex.insert(0,'0');
        }
        return hex.toString();
    }

    /**
     * Hash strings using SHA256
     * @param input String to be hashed
     * @return 64 char string
     */
    public static String hash(String input) {
        return toString(SHA256(input));
    }
}
