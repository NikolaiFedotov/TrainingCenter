import com.trainingcenter.Curriculum;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.trainingcenter.TCDateTimeUtils.*;

public class TCDateTimeUtilsTest {

    @Test
    public void testNegativeGetDayOfWeek() {

        Assert.assertEquals("Incorrect number, please, enter the number day of week!", getDayOfWeek(0));
    }

    @Test
    public void testGetDayOfWeek() {
        Assert.assertEquals("Sunday", getDayOfWeek(1));
    }

    @Test
    public void testGetWorkHours() {
        Assert.assertEquals(8, getWorkHours(23));
    }

    @Test
    public void testGetWorkDaysFromHours() {
        int hours = 100;
        int workDays = 100 / 8;
        int expHours = hours % 8;
        Assertions.assertEquals(workDays + " day(s) " + expHours + " hour(s)", getWorkDaysFromHours(hours));
    }

    @Test
    public void testGetCountOfWorkHoursBetweenTwoDates() {
        GregorianCalendar startCourseDateTime =  new GregorianCalendar(2022, Calendar.APRIL, 4,
                17, 55);
        GregorianCalendar reportDate = new GregorianCalendar(2022, Calendar.APRIL, 5,
                17, 55);
        System.out.println(gregorianCalendarDateToString(reportDate));
        System.out.println(gregorianCalendarDateToString(startCourseDateTime));
        Assert.assertEquals(8, getCountOfWorkHoursBetweenTwoDates(reportDate, startCourseDateTime));
    }

    @Test
    public void testGetDateEndOfCourse() {
        GregorianCalendar startCourseDateTime =  new GregorianCalendar(2022, Calendar.APRIL, 1,
                17, 55);
        GregorianCalendar endCourseDate = new GregorianCalendar(2022, Calendar.APRIL, 11,
                11, 55);
        Assert.assertEquals(gregorianCalendarDateToString(endCourseDate),
                gregorianCalendarDateToString(getDateEndOfCourse(startCourseDateTime, Curriculum.AQE)));
    }
}
