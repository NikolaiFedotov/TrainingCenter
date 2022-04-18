package com.trainingcenter;

import java.util.*;

public class Student {
    private String name;
    private String surname;
    private Map<Curriculum, GregorianCalendar> studentTraining = new HashMap<>();

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Map<Curriculum, GregorianCalendar> getStudentTraining() {
        return studentTraining;
    }

    public void setStudentTraining(Curriculum curriculum, GregorianCalendar startCourseDate) {
        studentTraining.put(curriculum, startCourseDate);
    }
}
