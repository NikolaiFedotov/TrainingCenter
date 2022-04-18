package com.trainingcenter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static com.trainingcenter.TCDateTimeUtils.gregorianCalendarDateToString;

public class TCScannerUtils {

    public static String scanReportParameter() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the parameter of report");

        return in.nextLine();
    }

    public static GregorianCalendar scanInputReportDate() {
        Scanner in = new Scanner(System.in);
        LocalDateTime reportDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        System.out.println("Enter the date and time DD/MM/YYYY HH:MM");
        while (reportDate == null) {
            String inputDate = in.nextLine();

            try {
                reportDate = LocalDateTime.parse(inputDate, formatter);
            } catch (DateTimeParseException ex) {
                System.out.println("Enter valid date and time DD/MM/YYYY HH:MM");
            }
        }

        GregorianCalendar inputDate = GregorianCalendar.from(reportDate.atZone(ZoneId.of("Europe/Kiev")));
        System.out.println("Entered date and time " + gregorianCalendarDateToString(inputDate));

        return inputDate;
    }
}
