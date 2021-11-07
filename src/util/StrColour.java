package util;

/**
 * Format strings to display a certain colour in the console
 * @author cruzerngz
 */
public class StrColour {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[91m";
    public static final String TEXT_GREEN = "\u001B[92m";
    public static final String TEXT_BLUE = "\u001B[94m";
    public static final String TEXT_CYAN = "\u001B[96m";

    /**
     * Colour a string red
     * @param str Target string
     * @return Coloured string
     */
    public static String Red(String str) {
        return (TEXT_RED + str + TEXT_RESET);
    }
    /**
     * Colour a string green
     * @param str Target string
     * @return Coloured string
     */
    public static String Green(String str) {
        return (TEXT_GREEN + str + TEXT_RESET);
    }
    /**
     * Colour a string blue
     * @param str Target string
     * @return Coloured string
     */
    public static String Blue(String str) {
        return (TEXT_BLUE + str + TEXT_RESET);
    }
    /**
     * Colour a string cyan
     * @param str Target string
     * @return Coloured string
     */
    public static String Cyan(String str) {
        return (TEXT_CYAN + str + TEXT_RESET);
    }
}
