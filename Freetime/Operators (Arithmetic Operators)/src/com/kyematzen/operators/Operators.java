package com.kyematzen.operators;

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
public class Operators {

    /**
     * Basic Types of operators:
     * + = Addition (example 10 + 2 = 12)
     * - = Subtraction (example 10 - 2 = 8)
     * * = Multiplication (example: 10 * 2 = 20)
     * / = Division (example: 10 / 2 = 5)
     * % = Modulo (example: 10 % 2 = 0)
     */

    // Starting position for compiler program execution
    public static void main(String[] args) {

        // Addition Operator Examples

        System.out.println("Addition Operator Examples:\n");

        // Example 1
        int additionEx1Num1 = 10;
        int additionEx1Num2 = 2;

        System.out.println("10 + 2 = " + (additionEx1Num1 + additionEx1Num2)); // Result 12

        // Example 2
        int additionEx2Num1 = 0;
        int additionEx2Num2 = -1;

        System.out.println("0 + -1 = " + (additionEx2Num1 + additionEx2Num2)); // Result -1

        // Subtraction Operator Examples

        System.out.println("\nSubtraction Operator Examples:\n");

        // Example 1
        int subtractionEx1Num1 = 10;
        int subtractionEx1Num2 = 2;

        System.out.println("10 - 2 = " + (subtractionEx1Num1 - subtractionEx1Num2)); // Result 8

        // Example 2
        int subtractionEx2Num1 = 0;
        int subtractionEx2Num2 = -1;

        System.out.println("0 - -1 = " + (subtractionEx2Num1 - subtractionEx2Num2)); // Result 1

        // Multiplication Operator Examples

        System.out.println("\nMultiplication Operator Examples:\n");

        // Example 1
        int multiplicationEx1Num1 = 10;
        int multiplicationEx1Num2 = 2;

        System.out.println("10 * 2 = " + (multiplicationEx1Num1 * multiplicationEx1Num2)); // Result 20

        // Example 2
        int multiplicationEx2Num1 = 0;
        int multiplicationEx2Num2 = -1;

        System.out.println("0 * -1 = " + (multiplicationEx2Num1 * multiplicationEx2Num2)); // Result 0

        // Division Operator Examples

        System.out.println("\nDivision Operator Examples:\n");

        // Example 1
        int divisionEx1Num1 = 10;
        int divisionEx1Num2 = 2;

        System.out.println("10 / 2 = " + (divisionEx1Num1 / divisionEx1Num2)); // Result 5

        // Example 2
        int divisionEx2Num1 = 0;
        int divisionEx2Num2 = -1;

        System.out.println("0 / -1 = " + (divisionEx2Num1 / divisionEx2Num2)); // Result 0

        // Modulo Operator Examples

        System.out.println("\nModulo Operator Examples:\n");

        // Example 1
        int moduloEx1Num1 = 10;
        int moduloEx1Num2 = 2;

        System.out.println("10 % 2 = " + (moduloEx1Num1 % moduloEx1Num2)); // Result 0 (even number)

        // Example 2
        int moduloEx2Num1 = 10;
        int moduloEx2Num2 = 3;

        System.out.println("10 % 3 = " + (moduloEx2Num1 % moduloEx2Num2)); // Result 1 (meaning odd number, and remainder of 1)

        // End of compiler program execution
    }
}
