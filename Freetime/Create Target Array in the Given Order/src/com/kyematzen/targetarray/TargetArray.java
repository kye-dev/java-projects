package com.kyematzen.targetarray;

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

public class TargetArray {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println("Output: " + Arrays.toString(createTargetArray(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 2, 1})));
        System.out.println("Output: " + Arrays.toString(createTargetArray(new int[]{1, 2, 3, 4, 0}, new int[]{0, 1, 2, 3, 0})));

        // End of compiler program execution
    }

    public static int[] createTargetArray(int[] nums, int[] index) {
        int[] targetArray = new int[nums.length];

        for (int i = 0; i < index.length; i++) {
            if (index[i] < i) {
                // One liner: if (i - index[i] >= 0) System.arraycopy(targetArray, index[i], targetArray, index[i] + 1, i - index[i]);
                for (int j = i; j > index[i]; j--) {
                    targetArray[j] = targetArray[j - 1];
                }
            }
            targetArray[index[i]] = nums[i];
        }

        return targetArray;
    }
}