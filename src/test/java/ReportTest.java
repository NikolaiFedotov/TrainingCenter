import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.trainingcenter.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testPrintShortReport() {
        List<Student> studentList = new ArrayList<>();
        Student petrov = new Student("Ivan", "Petrov");
        petrov.setStudentTraining(Curriculum.AQE, new GregorianCalendar(2022, Calendar.APRIL, 11, 1, 0));
        petrov.setStudentTraining(Curriculum.JavaDeveloper, new GregorianCalendar(2022, Calendar.MARCH, 10, 14, 0));
        studentList.add(petrov);
        GregorianCalendar reportDate = new GregorianCalendar(2022, Calendar.APRIL, 13, 12, 0);
        Report.printReport(reportDate, "0", studentList);
        String expected = "Short report\n" +
                "Generating report date - Wednesday 13/4/2022  12:00\n" +
                "Ivan Petrov ( AQE ) Training is not finished. 3 day(s) 0 hour(s) are left until the end.\n" +
                "Ivan Petrov ( JavaDeveloper ) Training completed. 16 day(s) 6 hour(s) have passed since the end.";
        Assert.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testPrintFullReport() {
        List<Student> studentList = new ArrayList<>();
        Student petrov = new Student("Ivan", "Petrov");
        petrov.setStudentTraining(Curriculum.AQE, new GregorianCalendar(2022, Calendar.APRIL, 11, 1, 0));
        petrov.setStudentTraining(Curriculum.JavaDeveloper, new GregorianCalendar(2022, Calendar.MARCH, 10, 14, 0));
        studentList.add(petrov);
        GregorianCalendar reportDate = new GregorianCalendar(2022, Calendar.APRIL, 13, 12, 0);
        Report.printReport(reportDate, "00", studentList);
        String expected = "Full report\n" +
                "Generating report date - Wednesday 13/4/2022  12:00\n" +
                "STUDENT: Ivan Petrov\n" +
                "Working time (from 10 to 18)\n" +
                "CURRICULUM: AQE\n" +
                "DURATION (hrs): 42\n" +
                "Start date: Monday 11/4/2022  1:00\n" +
                "End date: Monday 18/4/2022  12:00\n" +
                "Training is not finished. 3 day(s) 0 hour(s) are left until the end.\n" +
                "CURRICULUM: JavaDeveloper\n" +
                "DURATION (hrs): 56\n" +
                "Start date: Thursday 10/3/2022  14:00\n" +
                "End date: Monday 21/3/2022  14:00\n" +
                "Training completed. 16 day(s) 6 hour(s) have passed since the end.";
        Assert.assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
