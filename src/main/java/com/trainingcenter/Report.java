package com.trainingcenter;

import java.util.GregorianCalendar;
import java.util.List;

import static com.trainingcenter.TCDateTimeUtils.*;

public class Report {

    public static void printReport(GregorianCalendar dateOfLaunch, String inputParameter, List<Student> studentList) {
        if (inputParameter == null || inputParameter.equals("0")) {
            printShortReport(dateOfLaunch, studentList);
        } else {
            printFullReport(dateOfLaunch, studentList);
        }
    }

    private static void printShortReport(GregorianCalendar dateOfLaunch, List<Student> studentList) {
        System.out.println("Short report");
        System.out.println("Generating report date - " + gregorianCalendarDateToString(dateOfLaunch));
        studentList.stream().forEach(student ->
                student.getStudentTraining().forEach((curriculum, startCourseDate) -> {
                    int difference = (int) (curriculum.getProgramDuration() - getCountOfWorkHoursBetweenTwoDates(dateOfLaunch, startCourseDate));
                    String str = "";
                    if (difference > 0) {
                        str = "Training is not finished. " + getWorkDaysFromHours(difference) + " are left until the end.";
                    } else {
                        str = "Training completed. " + getWorkDaysFromHours(difference * (-1)) + " have passed since the end.";
                    }
                    System.out.println(student.getName() + " " + student.getSurname() + " ( " + curriculum + " ) " + str );
                })
        );
        System.out.println();
    }

    private static void printFullReport(GregorianCalendar dateOfLaunch, List<Student> studentList) {
        System.out.println("Full report");
        System.out.println("Generating report date - " + gregorianCalendarDateToString(dateOfLaunch));
        studentList.stream().forEach(student ->
        {
            System.out.println("STUDENT: " + student.getName() + " " + student.getSurname());
            System.out.println("Working time (from 10 to 18)");
            student.getStudentTraining().forEach((curriculum, startCourseDate) -> {
                System.out.println("CURRICULUM: " + curriculum + "\nDURATION (hrs): " + curriculum.getProgramDuration());
                System.out.println("Start date: " + gregorianCalendarDateToString(startCourseDate));
                GregorianCalendar endCourseDate = getDateEndOfCourse(startCourseDate, curriculum);

                System.out.println("End date: " + gregorianCalendarDateToString(endCourseDate));
                int hoursToEndCourse = (int) (curriculum.getProgramDuration() - getCountOfWorkHoursBetweenTwoDates(dateOfLaunch, startCourseDate));
                String str = null;
                if (hoursToEndCourse > 0) {
                    str = "Training is not finished. " + getWorkDaysFromHours(hoursToEndCourse) + " are left until the end.";
                } else {
                    str = "Training completed. " + getWorkDaysFromHours(hoursToEndCourse * (-1)) + " have passed since the end.";
                }
                System.out.println(str);
            });
            System.out.println();
        });
    }
}
