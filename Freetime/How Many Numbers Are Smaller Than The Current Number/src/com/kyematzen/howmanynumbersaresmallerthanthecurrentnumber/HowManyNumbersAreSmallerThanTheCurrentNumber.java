package com.kyematzen.howmanynumbersaresmallerthanthecurrentnumber;

import java.util.Arrays;

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

public class HowManyNumbersAreSmallerThanTheCurrentNumber {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{7, 7, 7, 7})));

        // End of compiler program execution
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] numsCopy = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int localNum = nums[i];
            int amount = 0;

            for (int j = 0; j < nums.length;j++) {
                if (i != j && localNum > nums[j]) {
                    amount++;
                }
            }

            numsCopy[i] = amount;
        }

        return numsCopy;
    }
}