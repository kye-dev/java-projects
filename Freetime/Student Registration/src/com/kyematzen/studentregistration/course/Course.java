package com.kyematzen.studentregistration.course;

public class Course {

    private final String courseName;
    private final double credits;

    public Course(String courseName, double credits) {
        this.courseName = courseName;
        this.credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCredits() {
        return credits;
    }

}