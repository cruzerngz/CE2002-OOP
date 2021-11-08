package util;
//imports
import java.text.SimpleDateFormat;

/**
 * Date-Time object that keeps track of the current date and time,
 * along with any offsets passed
 * @author cruzerngz
 */
public class DateTime {
    private long deltaH;
    private long deltaD;
    private long unixTime;

    //formatters
    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("E dd/MM/yyyy HH:mm");

    public DateTime() {
        deltaH = 0; //hour offset
        deltaD = 0; //day offset
        update();
    }

    /**
     * Gets the current time with offsets
     * @return 24H format time
     */
    public int getTime() {
        update();
        return Integer.parseInt(timeFormat.format(unixTime));
    }

    /**
     * Gets the current date with offsets
     * @return Date in string form DD/MM/YYYY
     */
    public String getDate() {
        update();
        return dateFormat.format(unixTime);
    }

    /**
     * Gets the current date and time with offsets
     * @return Date-time in string form
     */
    public String getDateTime() {
        update();
        return dateTimeFormat.format(unixTime);
    }

    /**
     * Add or subtract hours to the current time
     * @param delta Hour delta
     */
    public void deltaHour(long delta) {
        deltaH += delta * 3600 * 1000;
    }

    /**
     * Add or subtract days to the current time
     * @param delta Day delta
     */
    public void deltaDay(long delta) {
        deltaD += delta * 86400 * 1000;
    }

    /**
     * Remove all hour and day offsets
     */
    public void reset() {
        deltaH = 0;
        deltaD = 0;
    }

    /**
     * Updates the current private variable time
     */
    private void update() {
        unixTime = System.currentTimeMillis();
        unixTime += deltaH;
        unixTime += deltaD;
    }
}
