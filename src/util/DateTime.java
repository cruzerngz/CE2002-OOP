package util;
import java.text.SimpleDateFormat;

public class DateTime {
    private long deltaH;
    private long deltaD;
    private long time;

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
     * @return Int time
     */
    public int getTime() {
        update();
        return Integer.parseInt(timeFormat.format(time));
    }

    /**
     * Gets the current date with offsets
     * @return String date
     */
    public String getDate() {
        update();
        return dateFormat.format(time);
    }

    /**
     * Gets the current date and time with offsets
     * @return String date-time
     */
    public String getDateTime() {
        update();
        return dateTimeFormat.format(time);
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
     * Remove all offsets
     */
    public void reset() {
        deltaH = 0;
        deltaD = 0;
    }

    /**
     * Updates the current private variable time
     */
    private void update() {
        time = System.currentTimeMillis();
        time += deltaH;
        time += deltaD;
    }
}
