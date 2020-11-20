package com.kyematzen.productsum;

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

public class ProductSum {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println("Output: " + subtractProductAndSum(234));
        System.out.println("Output: " + subtractProductAndSum(4421));
        System.out.println("Output: " + subtractProductAndSum(705));

        // End of compiler program execution
    }

    public static int subtractProductAndSum(int n) {
        String number = String.valueOf(n);
        int[] numbers = new int[number.length()];

        for (int i = 0; i < number.length(); i++) {
            numbers[i] = Integer.parseInt(Character.toString(number.charAt(i)));
        }

        int[] solutions = new int[] { numbers[0], numbers[0] };

        for (int i = 1; i < numbers.length;i++) {
            int num = numbers[i];

            solutions[0] *= num;
            solutions[1] += num;
        }

        return solutions[0] - solutions[1];
    }
}