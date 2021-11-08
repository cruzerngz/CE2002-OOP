package util;

/**
 * Format strings to display a certain colour in the console
 * @author cruzerngz
 */
public class StrColour {

    public static final String TEXT_RESET = "\u001B[0m";
    //text col
    public static final String TEXT_WHITE  = "\u001B[97m";
    public static final String TEXT_BLACK  = "\u001B[30m";
    public static final String TEXT_RED    = "\u001B[91m";
    public static final String TEXT_GREEN  = "\u001B[92m";
    public static final String TEXT_BLUE   = "\u001B[94m";
    public static final String TEXT_CYAN   = "\u001B[96m";
    public static final String TEXT_PURPLE = "\u001B[95m";
    public static final String TEXT_YELLOW = "\u001B[93m";

    //background col
    public static final String BG_WHITE  = "\u001B[47m";
    public static final String BG_BLACK  = "\u001B[100m";
    public static final String BG_RED    = "\u001B[41m";
    public static final String BG_GREEN  = "\u001B[42m";
    public static final String BG_BLUE   = "\u001B[44m";
    public static final String BG_CYAN   = "\u001B[46m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_YELLOW = "\u001B[43m";

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

    /**
     * Colour a string black on white background
     * @param str Target string 
     * @return Coloured string
     */
    public static String contrastBW(String str) {
        return (TEXT_BLACK + BG_WHITE + str + TEXT_RESET);
    }

    /**
     * Colour a string black on green background
     * @param str Target string 
     * @return Coloured string
     */
    public static String contrastBG(String str) {
        return (TEXT_BLACK + BG_GREEN + str + TEXT_RESET);
    }
}
