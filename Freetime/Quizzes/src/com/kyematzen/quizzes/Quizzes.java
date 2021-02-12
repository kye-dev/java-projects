package com.kyematzen.quizzes;

import java.util.Arrays;
import java.util.Scanner;

public class Quizzes {

    private static int questionsAmount;
    private String[] questions;
    //private

    public static void main(String[] args) {
        // 1. Implement Scanner
        Scanner localScanner = new Scanner(System.in);

//        // 1. Ask the user how many questions
//        System.out.println("How many questions would you like for the quiz?");
//        questionsAmount = localScanner.nextInt();

        // 2. Ask the user to enter the key.
        String example = "34 7 13 100 81 3 9 10 321 12";
        String[] exampleArray = example.split("");

        System.out.println(Arrays.toString(exampleArray));

        // 34 7 13 100 81 3 9 10 321 12
    }
}