import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.sql.Time;
import java.time.Instant;

public class TimeInRangeTest extends TimeInRange
{
    @Test
    public void testTimeInRange()
    {
        Instant now = Instant.now();
        Instant day = Instant.parse(("2020-08-09T10:00:00.00Z"));
        Instant night = Instant.parse(("2020-08-09T02:00:00.00Z"));
        //Testing Substring Values
        TimeInRange.isInTimeRange("12-20",now);
        assertEquals(TimeInRange.range1, 12);
        assertEquals(TimeInRange.range2 , 20);

        //Testing TimeInRange Class

        //Testing against current Time
        assertTrue(TimeInRange.isInTimeRange("00-23", now));
        //Testing against custom time
        assertTrue(TimeInRange.isInTimeRange("09-17", day));
        assertTrue(TimeInRange.isInTimeRange("18-07", night));

    }
}
