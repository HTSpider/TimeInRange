import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

public class TimeInRange
{
    public static boolean isInTimeRange(String timeRange, Instant time)
    {
        String timeString = time.toString();
        int range1 =0;
        int range2 =0;
        // getting the hour variable from time
        int hours = Instant.parse(timeString).atZone(ZoneId.of("UTC")).get((ChronoField.HOUR_OF_DAY));

        try
        {
            //Splitting the time range into two variables
             range1 = Integer.parseInt(timeRange.substring(0, 2));
             range2 = Integer.parseInt(timeRange.substring(3, 5));
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println("IllegalArgumentException, Please make sure the formatting is valid");
        }

        // Main logic for time in range
        if (range1 <= hours || range2 >= hours) ;
        {
            return true;
        }
    }
}