import java.sql.Time;
import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Scanner;

public class TimeInRange
{

    public static boolean isInTimeRange(String timeRange, Instant time)
    {

        //Converting time to a string then using substring to get relevant information
        String timeS = time.toString().substring(time.toString().lastIndexOf("T") + 1, time.toString().lastIndexOf("T") + 3);
        int hours = Integer.parseInt(timeS);
        int range1 =0;
        int range2 =0;

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
