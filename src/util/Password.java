package util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashing class for passwords
 */
public class Password {
    
    /**
     * Hashes a given string using SHA256
     * @param input String to be hashed
     * @return 256-bit result
     * @throws NoSuchAlgorithmException
     */
    private static byte[] SHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
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
     * @throws NoSuchAlgorithmException
     */
    public static String hash(String input) throws NoSuchAlgorithmException {
        return toString(SHA256(input));
    }
}
