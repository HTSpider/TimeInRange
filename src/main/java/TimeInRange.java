import java.sql.Time;
import java.time.Instant;
import java.util.Scanner;

public class TimeInRange
{
    static int range1;
    static int range2;
    public static boolean isInTimeRange(String timeRange, Instant time)
    {
        //Converting time to a string then using substring to get relevant information
        String timeS = time.toString().substring(time.toString().lastIndexOf("T" )+1,time.toString().lastIndexOf("T" )+3);
        int hours = Integer.parseInt(timeS);

        //Splitting the time range into two variables
        range1 = Integer.parseInt(timeRange.substring(0,2));
        range2 = Integer.parseInt(timeRange.substring(3,5));

        //Main logic to compare if in range
        if((range1 <= hours && !(hours > 23)) || range2 >= hours)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
