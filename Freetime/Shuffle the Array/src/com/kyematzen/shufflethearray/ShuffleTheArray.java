package com.kyematzen.shufflethearray;

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

public class ShuffleTheArray {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println("Output: " + Arrays.toString(shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3))); // Output [2,3,5,4,1,7]
        System.out.println("Output: " + Arrays.toString(shuffle(new int[]{1,2,3,4,4,3,2,1}, 4))); // Output [1,4,2,3,3,2,4,1]
        System.out.println("Output: " + Arrays.toString(shuffle(new int[]{1,1,2,2}, 2))); // Output [1,2,1,2]

        // End of compiler program execution
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] xValues = new int[n];
        int[] yValues = new int[n];

        if (n >= 0) System.arraycopy(nums, 0, xValues, 0, n);

        if (nums.length - n >= 0) System.arraycopy(nums, n, yValues, 0, nums.length - n);

        for (int i = 0, value = 0; i < nums.length; i+= 2, value++) {
            nums[i] = xValues[value];
        }

        for (int i = 1, value = 0; i < nums.length; i+= 2, value++) {
            nums[i] = yValues[value];
        }

        return nums;
    }
}