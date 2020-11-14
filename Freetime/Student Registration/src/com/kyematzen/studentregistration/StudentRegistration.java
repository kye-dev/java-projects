package com.kyematzen.studentregistration;

import com.kyematzen.studentregistration.course.Course;
import com.kyematzen.studentregistration.student.Student;

import java.util.*;

/**
 * Copyright 2020, Kye Matzen, http://kyematzen.com
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

public class StudentRegistration {

    // Main class instance
    public static StudentRegistration studentRegistrationInstance;

    // Random instance
    private final SplittableRandom splittableRandom = new SplittableRandom();

    // Courses list
    private final List<Course> courseList = Arrays.asList(
            new Course("Business", 5),
            new Course("English", 5),
            new Course("Science", 5));

    // Student list
    private final List<Student> studentList = new ArrayList<>();

    // Scanner instance
    private Scanner scanner;

    // Starting position for compiler program execution
    public static void main(String[] args) {

        // Initializes class instance
        studentRegistrationInstance = new StudentRegistration();

        // Initializes scanner instance
        studentRegistrationInstance.scanner = new Scanner(System.in);

        // Begins core of student registration where it'll ask the user a question.
        studentRegistrationInstance.ask(true);

        // End of compiler program execution
    }

    // Asks user question
    public void ask(boolean question) {
        // Asks to view or add students
        if (question) {
            System.out.println("\nOptions:\nType 'view' to show enrolled students\nType 'add' to add a student.\n");
        }

        String response = scanner.nextLine();

        if (response.trim().length() == 0) {
            ask(false);
            return;
        }

        if (!response.equalsIgnoreCase("view") && !response.equalsIgnoreCase("add")) {
            System.out.println("\nInvalid option: " + response + ".");
            ask(false);
            return;
        }

        if ("view".equals(response.toLowerCase())) {
            if (studentRegistrationInstance.studentList.size() == 0) {
                System.out.println("\nThere are no enrolled students.");
                ask(true);
                return;
            }

            System.out.println("\nStudents");

            for (Student student : studentRegistrationInstance.studentList) {
                System.out.println(student.getName() + " (age: " + student.getAge() + ", course: " + student.getCourse().getCourseName() + ")");
            }

            ask(true);

            return;
        }

        System.out.println("\nWhat is the student's name?");
        String studentName = scanner.nextLine();

        System.out.println("\nWhat is the student's age?");
        String age = scanner.nextLine();

        int studentAge = 16;

        try {
            studentAge = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            System.out.println("Age entered couldn't be parsed, default at 16.");
        }

        addStudent(studentName, studentAge);

        System.out.println("\nAdded student #" + studentList.size());

        ask(true);
    }

    public void addStudent(String studentName, int studentAge) {
        Student student = new Student(studentName, studentAge);

        student.setCourse(courseList.get(splittableRandom.nextInt(courseList.size())));

        studentList.add(student);
    }
}