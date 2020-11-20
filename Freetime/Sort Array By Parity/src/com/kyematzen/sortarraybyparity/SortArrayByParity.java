package com.kyematzen.sortarraybyparity;

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

public class SortArrayByParity {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println("Output: " + Arrays.toString(sortArrayByParity(new int[]{3, 1, 2, 4})));
        System.out.println("Output: " + Arrays.toString(sortArrayByParity(new int[]{2, 4, 3, 1})));

        // End of compiler program execution
    }

    public static int[] sortArrayByParity(int[] A) {
        int[] localArray = new int[A.length];
        int index = 0;

        for (int j : A) {
            if (j % 2 == 0) {
                localArray[index++] = j;
            }
        }

        for (int j : A) {
            if (j % 2 == 1) {
                localArray[index++] = j;
            }
        }

        return localArray;
    }
}