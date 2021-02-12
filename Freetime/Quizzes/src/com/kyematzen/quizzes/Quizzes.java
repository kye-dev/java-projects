package com.kyematzen.quizzes;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Quizzes {

    private static final DecimalFormat decimalFormat = new DecimalFormat("###.##");
    private static int questionsAmount = 0;
    private static String[] answerKey;

    public static void main(String[] args) {
        // 1. Implement Scanner
        Scanner localScanner = new Scanner(System.in);

        gradeQuiz(localScanner);
    }

    public static void gradeQuiz(Scanner localScanner) {
        // 1. Ask the user how many questions
        while (questionsAmount == 0) {
            System.out.println("How many questions would you like for the quiz?");
            questionsAmount = localScanner.nextInt();
        }

        getQuizAnswers(localScanner);
    }

    public static void getQuizAnswers(Scanner localScanner) {
        // 2. Ask the user to provide how many quiz answers.
        String answer = "";
        System.out.println("Please provide quiz key.");
        while (answer.isEmpty()) {
            answer = localScanner.nextLine();
        }

        answerKey = answer.split(" ");

        getStudentAnswers(localScanner);
    }

    public static void getStudentAnswers(Scanner localScanner) {
        String answer = "";
        System.out.println("Please provide student's answers.");
        while (answer.isEmpty()) {
            answer = localScanner.nextLine();
        }

        String[] studentKey = answer.split(" ");
        int questionsCorrect = 0;

        for (int i = 0; i < answerKey.length; i++) {
            if (answerKey[i].equals(studentKey[i])) {
                questionsCorrect++;
            }
        }

        System.out.println("\nQuestions Correct: " + questionsCorrect);
        System.out.println("Percent Correct: " + calculatePercentage(questionsCorrect, questionsAmount) + "%");

        String gradeAgain = "";
        System.out.println("\nGrade another quiz (y/n)");
        while (gradeAgain.isEmpty()) {
            gradeAgain = localScanner.nextLine();
        }

        if (gradeAgain.equalsIgnoreCase("y")) {
            getStudentAnswers(localScanner);
        }
    }

    private static String calculatePercentage(int questionsCorrect, int questionsAmount) {
        return decimalFormat.format(( (double) questionsCorrect * 100 / questionsAmount));
    }
}