package util;
import java.text.ParseException;
//imports
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//exceptions
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Date-Time object that keeps track of the current date and time,
 * along with any offsets passed
 * @author cruzerngz
 */
public class DateTime {
    private long deltaH;
    private long deltaD;
    private long unixTime;
    private String savePath = Path.timedata;

    //formatters
    static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
    static SimpleDateFormat timeStringFormat = new SimpleDateFormat("HH:mm");
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("E dd/MM/yyyy HH:mm");
    static SimpleDateFormat dayDateFormat = new SimpleDateFormat("E dd/MM/yyyy");
    static SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM/yyyy");
    static SimpleDateFormat reverseDateFormat = new SimpleDateFormat("dd MM yyyy");

    /**
     * Specify a path to the data directory
     */
    public DateTime() {
        try {update();}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the number of days passed since 1 Jan 1970
     * @return Int days elapsed since epoch
     */
    public int getDaysSinceEpoch() {
        try {update();}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
     * Converts the year, month and day into number of days elapsed since epoch
     * @param y Year
     * @param m Month
     * @param d Day
     * @return Int days elapsed since epoch
     */
    public int ymdToEpochDay(int y, int m, int d) {
        Date date = new Date();
        try {
            date = reverseDateFormat.parse(String.format("%d %d %d", d+1, m, y));
        } catch (ParseException e) {e.printStackTrace();}
        return (int)(date.getTime() / 1000 / 86400);
    }

    /**
     * Converts the number of days since 1970 to day and date
     * @param daysSinceEpoch Days elapsed since epoch
     * @return Date in string form DAY DD/MM/YYYY
     */
    public String daysToDayDate(int daysSinceEpoch) {
        long time = (long)(daysSinceEpoch) * 86400 * 1000;
        return dayDateFormat.format(time);
    }

    public String daysToMonthYear(int daysSinceEpoch) {
        long time = (long)(daysSinceEpoch) * 86400 * 1000;
        return monthYearFormat.format(time);
    }

    /**
     * Gets the current time with offsets
     * @return 24H format time
     */
    public int getTime() {
        try {update();}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(timeFormat.format(unixTime));
    }

    /**
     * Gets the current time with offsets
     * @return 24H format time as string
     */
    public String getTimeString() {
        try {update();}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return timeStringFormat.format(unixTime);
    }

    /**
     * Gets the current date with offsets
     * @return Date in string form DD/MM/YYYY
     */
    public String getDate() {
        try {update();}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dateFormat.format(unixTime);
    }

    /**
     * Gets the current day and date with offsets
     * @return Date in string form DAY DD/MM/YYYY
     */
    public String getDayDate() {
        try {update();}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  dayDateFormat.format(unixTime);
    }

    /**
     * Gets the current date and time with offsets
     * @return Date-time in string form
     */
    public String getDateTime() {
        try {update();}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dateTimeFormat.format(unixTime);
    }

    /**
     * Add or subtract hours to the current time
     * @param delta Hour delta
     */
    public void deltaHour(long delta) {
        deltaH += delta * 3600 * 1000;
        try {save();}
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add or subtract days to the current time
     * @param delta Day delta
     */
    public void deltaDay(long delta) {
        deltaD += delta * 86400 * 1000;
        try {save();}
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove all hour and day offsets
     */
    public void reset() {
        deltaH = 0;
        deltaD = 0;
        try {save();}
        catch (IOException e) {
            e.printStackTrace();
        }
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
