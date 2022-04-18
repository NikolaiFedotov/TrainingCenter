package com.trainingcenter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TCDateTimeUtils {

    public static String gregorianCalendarDateToString(GregorianCalendar date) {
        return getDayOfWeek(date.get(Calendar.DAY_OF_WEEK)) + " " + date.get(Calendar.DATE) + "/" +
                (date.get(Calendar.MONTH) + 1)  + "/" + date.get(Calendar.YEAR) + "  " + date.get(Calendar.HOUR_OF_DAY)
                + ":" + ((date.get(Calendar.MINUTE) < 10) ? "0" + date.get(Calendar.MINUTE) : date.get(Calendar.MINUTE)) ;
    }

    public static String getDayOfWeek(int dayNumber) {
        String result;
        switch (dayNumber) {
            case 1:
                result = "Sunday";
                break;
            case 2:
                result = "Monday";
                break;
            case 3:
                result = "Tuesday";
                break;
            case 4:
                result = "Wednesday";
                break;
            case 5:
                result = "Thursday";
                break;
            case 6:
                result = "Friday";
                break;
            case 7:
                result = "Saturday";
                break;
            default:
                result = "Incorrect number, please, enter the number day of week!";
        }

        return result;
    }

    public static int getWorkHours(int hourOfDay) {
        int workHours = 0;
        if (hourOfDay >= 18) {
            workHours = 8;
        } else {
            if (hourOfDay > 10) {
                workHours -= 10;
            }
        }

        return workHours;
    }

    public static String getWorkDaysFromHours(int hours) {
        String result = "";
        if (hours < 0) {
            return "Hours value must be positive number";
        }
        int days = hours / 8;
        int resultHours = hours % 8;
        if (days == 0) {
            result = resultHours + " hours";
        } else {
            result = days + " day(s) " + resultHours + " hour(s)";
        }

        return result;
    }

    /**
     * @param reportDate should be older
     * @param startCourseDate should be younger
     * */
    public static int getCountOfWorkHoursBetweenTwoDates(GregorianCalendar reportDate, GregorianCalendar startCourseDate) {
        int minutes = 0;
        GregorianCalendar betweenDates = new GregorianCalendar(startCourseDate.get(Calendar.YEAR), startCourseDate.get(Calendar.MONTH),
                startCourseDate.get(Calendar.DAY_OF_MONTH), startCourseDate.get(Calendar.HOUR_OF_DAY), startCourseDate.get(Calendar.MINUTE));

        while (betweenDates.before(reportDate)) {
            if (isWorkDay(betweenDates)) {
                if (isWorkHour(betweenDates)) {
                            betweenDates.add(Calendar.MINUTE, 1);
                            minutes++;
                } else {
                    betweenDates.add(Calendar.MINUTE, 1);
                }
            } else {
                betweenDates.add(Calendar.MINUTE, 1);
            }
        }

        return minutes / 60;
    }

    public static GregorianCalendar getDateEndOfCourse(GregorianCalendar startCourseDate, Curriculum curriculum) {
        GregorianCalendar courseEndDate = new GregorianCalendar(startCourseDate.get(Calendar.YEAR), startCourseDate.get(Calendar.MONTH),
                startCourseDate.get(Calendar.DAY_OF_MONTH), startCourseDate.get(Calendar.HOUR_OF_DAY), startCourseDate.get(Calendar.MINUTE));
        long duration = curriculum.getProgramDuration()  * 60;
        while (duration > 0) {
            if (isWorkDay(courseEndDate)) {
                if (isWorkHour(courseEndDate)) {
                    courseEndDate.add(Calendar.MINUTE, 1);
                    duration--;
                } else {
                    courseEndDate.add(Calendar.MINUTE, 1);
                }
            } else {
                courseEndDate.add(Calendar.MINUTE, 1);
            }
        }

        return courseEndDate;
    }

    public static boolean isWorkDay(GregorianCalendar date) {
        return (date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) && (date.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY);
    }

    public static boolean isWorkHour(GregorianCalendar date) {
        return (date.get(Calendar.HOUR_OF_DAY) >= 10) &&
                (date.get(Calendar.HOUR_OF_DAY) < 18);
    }
}