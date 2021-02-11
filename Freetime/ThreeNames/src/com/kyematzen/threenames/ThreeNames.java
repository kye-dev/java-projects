package com.kyematzen.threenames;

import java.util.Scanner;

public class ThreeNames {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("First Name: ");
        String n1 = scan.next();
        System.out.print("Second Name: ");
        String n2 = scan.next();
        System.out.print("Third Name: ");
        String n3 = scan.next();
        double avg = (double) (n1.length() + n2.length() + n3.length()) / 3.0;
        System.out.println(avg + "\n" + n1.charAt(0) + n2.charAt(0) + n3.charAt(0));
    }
}