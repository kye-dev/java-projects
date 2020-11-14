package com.kyematzen.studentregistration.student;

import com.kyematzen.studentregistration.course.Course;

public class Student {

    private final String name;
    private final int age;
    private Course course;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}