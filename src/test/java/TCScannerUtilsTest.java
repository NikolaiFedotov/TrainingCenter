import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.trainingcenter.TCDateTimeUtils.gregorianCalendarDateToString;
import static com.trainingcenter.TCScannerUtils.scanInputReportDate;
import static com.trainingcenter.TCScannerUtils.scanReportParameter;

public class TCScannerUtilsTest {

    /*InputStream sysInBackup = System.in;

    @BeforeEach
    public void beforeEach() {
        sysInBackup = System.in;
    }

    @AfterEach
    public void afterEach() {
        //InputStream sysInBackup = System.in;
        System.setIn(sysInBackup);
    }*/

    @Test
    public void testScanReportParameter() {
        ByteArrayInputStream in = new ByteArrayInputStream("00".getBytes());
        System.setIn(in);
        String input =  scanReportParameter();

        Assert.assertEquals("00", input);
    }

    @Test
    public void testScanInputDate() {
        ByteArrayInputStream in = new ByteArrayInputStream("17/04/2022 12:00".getBytes());
        System.setIn(in);
        GregorianCalendar expectedDate = new GregorianCalendar(2022, Calendar.APRIL, 17, 12, 0);

        Assert.assertEquals(gregorianCalendarDateToString(expectedDate), gregorianCalendarDateToString(scanInputReportDate()));
    }
}
