import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import java.sql.Time;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import static org.assertj.core.api.Assertions.*;


public class TimeInRangeTest extends TimeInRange
{
    @Test
    public void testTimeInRange()
    {

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime day = ZonedDateTime.parse(("2020-08-09T11:00:00.00Z"));
        ZonedDateTime night = ZonedDateTime.parse(("2020-08-09T02:00:00.00Z"));

        //Testing TimeInRange Class
        //Testing against current Time
        assertTrue(TimeInRange.isInTimeRange("00-23", now));

        //Testing against custom time
        assertTrue(TimeInRange.isInTimeRange("09-17", day));
        assertTrue(TimeInRange.isInTimeRange("17-04", night));

        // check our utility does what it is supposed to
        assertEquals(makeZonedDateTimeWithHour(9).get(ChronoField.HOUR_OF_DAY), 9 );

        // check our utility behaves as it is supposed to
        catchThrowableOfType(() -> makeZonedDateTimeWithHour(-1), IllegalArgumentException.class);
        catchThrowableOfType(() -> makeZonedDateTimeWithHour(Integer.MAX_VALUE), IllegalArgumentException.class);
        assertThatNoException().isThrownBy(() -> makeZonedDateTimeWithHour(12));

        // check for good times
        assertTrue(TimeInRange.isInTimeRange("09-17", makeZonedDateTimeWithHour(12))); // normal day
        assertTrue(TimeInRange.isInTimeRange("22-06", makeZonedDateTimeWithHour(0))); // overnight
        assertTrue(TimeInRange.isInTimeRange("09-08", makeZonedDateTimeWithHour(10))); // really odd but legal input
        assertTrue(TimeInRange.isInTimeRange("9-09", makeZonedDateTimeWithHour(9))); // totally odd but legal input
        assertTrue(TimeInRange.isInTimeRange("19-0", makeZonedDateTimeWithHour(22))); // midnight at one end
        assertTrue(TimeInRange.isInTimeRange("0-6", makeZonedDateTimeWithHour(3))); // midnight at one end

        // check for bad times
        assertFalse(TimeInRange.isInTimeRange("9-17", makeZonedDateTimeWithHour(7))); // normal day
        assertFalse(TimeInRange.isInTimeRange("22-06", makeZonedDateTimeWithHour(7))); // overnight
        assertFalse(TimeInRange.isInTimeRange("09-08", makeZonedDateTimeWithHour(8))); // really odd but legal input
        assertFalse(TimeInRange.isInTimeRange("9-09", makeZonedDateTimeWithHour(10))); // totally odd but legal input
        assertFalse(TimeInRange.isInTimeRange("19-0", makeZonedDateTimeWithHour(3))); // midnight at one end
        assertFalse(TimeInRange.isInTimeRange("0-6", makeZonedDateTimeWithHour(12))); // midnight at one end

        // check for bad inputs
        ZonedDateTime input = makeZonedDateTimeWithHour(5);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("asdsf", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("000-09", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("1-3-7", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("09,01", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("0901-", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("-1214", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("0-25", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("2-500", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("-0", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("09-A", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("999", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("", input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange(null, input), IllegalArgumentException.class);
        catchThrowableOfType(() -> TimeInRange.isInTimeRange("9-17", null), IllegalArgumentException.class);

    }

    /**
     * makes a ZonedDateTime having the specified HourOfTheDay.
     * The zoned date time is at the local time zone of the JVM.
     * @param hour
     * @return
     */
    private static ZonedDateTime makeZonedDateTimeWithHour(int hour)
    {
        if(hour > 23 || hour < 0)
        {
            throw new IllegalArgumentException("Hour must be 0 <> 23");
        }

        return ZonedDateTime.now().with(ChronoField.HOUR_OF_DAY, hour);
    }

}







