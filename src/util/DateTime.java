package util;
import java.io.FileNotFoundException;
import java.io.IOException;
//imports
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Date-Time object that keeps track of the current date and time,
 * along with any offsets passed
 * @author cruzerngz
 */
public class DateTime {
    private long deltaH = 0;
    private long deltaD = 0;
    private long unixTime;
    private String savePath;

    //formatters
    static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
    static SimpleDateFormat timeStringFormat = new SimpleDateFormat("HH:mm");
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("E dd/MM/yyyy HH:mm");

    /**
     * Constructor
     * @param filePath Path to data directory
     * @throws FileNotFoundException
     */
    public DateTime(String filePath) throws FileNotFoundException {
        savePath = filePath + "/timeData.csv";
        update();
    }

    /**
     * Gets the number of days passed since 1 Jan 1970
     * @return Int days elapsed since epoch
     */
    public int getDaysSinceEpoch() throws IOException {
        update();
        return (int)(unixTime / 1000 / 86400);
    }

    /**
     * Converts the number of days since 1970 into date
     * @param daysSinceEpoch Days elapsed since epoch
     * @return Date in string form DD/MM/YYYY
     */
    public String daysToDate(int daysSinceEpoch) {
        long time = (long)(daysSinceEpoch) * 86400 * 1000;
        return dateFormat.format(time);
    }

    /**
     * Gets the current time with offsets
     * @return 24H format time
     * @throws IOException
     */
    public int getTime() throws IOException {
        update();
        return Integer.parseInt(timeFormat.format(unixTime));
    }

    /**
     * Gets the current time with offsets
     * @return 24H format time as string
     * @throws IOException
     */
    public String getTimeString() throws IOException {
        update();
        return timeStringFormat.format(unixTime);
    }

    /**
     * Gets the current date with offsets
     * @return Date in string form DD/MM/YYYY
     */
    public String getDate() throws IOException {
        update();
        return dateFormat.format(unixTime);
    }

    /**
     * Gets the current date and time with offsets
     * @return Date-time in string form
     */
    public String getDateTime() throws IOException {
        update();
        return dateTimeFormat.format(unixTime);
    }

    /**
     * Add or subtract hours to the current time
     * @param delta Hour delta
     */
    public void deltaHour(long delta) throws IOException {
        deltaH += delta * 3600 * 1000;
        save();
    }

    /**
     * Add or subtract days to the current time
     * @param delta Day delta
     */
    public void deltaDay(long delta) throws IOException {
        deltaD += delta * 86400 * 1000;
        save();
    }

    /**
     * Remove all hour and day offsets
     * @throws IOException
     */
    public void reset() throws IOException {
        deltaH = 0;
        deltaD = 0;
        save();
    }

    /**
     * Updates the current private variable time
     * Reads saved modifiers from file
     * @throws FileNotFoundException
     */
    private void update() throws FileNotFoundException {
        unixTime = System.currentTimeMillis();

        ArrayList<String[]> dataArr = Data.readCSV(savePath);
        deltaH = Long.parseLong(dataArr.get(0)[0]); //hour offset
        deltaD = Long.parseLong(dataArr.get(0)[1]); //day offset
        
        unixTime += deltaH;
        unixTime += deltaD;
    }

    /**
     * Saves modifiers to file
     * @throws IOException
     */
    private void save() throws IOException {
        String[] writeArr = new String[2];
        ArrayList<String[]> toWrite = new ArrayList<String[]>();

        writeArr[0] = Long.toString(deltaH);
        writeArr[1] = Long.toString(deltaD);
        toWrite.add(writeArr);

        Data.writeCSV(toWrite, savePath);
    }
}
