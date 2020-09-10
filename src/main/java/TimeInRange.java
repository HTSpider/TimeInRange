import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class TimeInRange
{
    public static boolean isInTimeRange(String timeRange, ZonedDateTime time)
    {
        // checking if timeRange is null
        if(timeRange == null)
        {
            throw new IllegalArgumentException("IllegalArgumentException, Please make sure the formatting is valid");
        }
        int range1 =0;
        int range2 =0;

        String[] ss = timeRange.split("-");
        String range1S = String.format("%02d", Integer.parseInt(ss[0]));
        String range2S = String.format("%02d", Integer.parseInt(ss[1]));

        timeRange = range1S+"-"+range2S;
        // more error handling
        if(time == null || timeRange.length() != 5 || timeRange.charAt(2) != '-')
        {
            throw new IllegalArgumentException("IllegalArgumentException, Please make sure the formatting is valid");
        }
        else
        {
            range1 = Integer.parseInt(timeRange.substring(0, 2));
            range2 = Integer.parseInt(timeRange.substring(3, 5));
        }

        String timeString = time.toString();

        // getting the hour variable from time
        int hours = ZonedDateTime.parse(timeString).get(ChronoField.HOUR_OF_DAY);

        // Main logic for time in range
        if(range1 == range2 &&  hours ==range1)
        {
            return true;
        }
        else if(range1 > range2)
        {
            int newSmall = range2 ;
            int newBig = range1 -1;
            return!(hours >= newSmall && hours <= newBig);
        }

        return(hours >= range1  && hours < range2);
    }
}