package com.trainingcenter;

import java.util.*;

import static com.trainingcenter.TCScannerUtils.scanInputReportDate;
import static com.trainingcenter.TCScannerUtils.scanReportParameter;

public class Main {

    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        Student petrov = new Student("Ivan", "Petrov");
        petrov.setStudentTraining(Curriculum.AQE, new GregorianCalendar(2022, Calendar.FEBRUARY, 14, 17, 55));
        studentList.add(petrov);

        Student sidorov = new Student("Alexandr", "Sidorov");
        sidorov.setStudentTraining(Curriculum.J2EEDeveloper, new GregorianCalendar(2022, Calendar.APRIL, 3, 10, 12));
        sidorov.setStudentTraining(Curriculum.JavaDeveloper, new GregorianCalendar(2022, Calendar.MARCH, 1, 10, 12));
        studentList.add(sidorov);

        Report.printReport(scanInputReportDate(), scanReportParameter(), studentList);
    }
}
