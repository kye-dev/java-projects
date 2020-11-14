package com.kyematzen.twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

public class TwoSum {

    public static TwoSum twoSumInstance;

    // Starting position for compiler program execution
    public static void main(String[] args) {

        // Main class instance registration
        twoSumInstance = new TwoSum();

        System.out.println("Output: " + Arrays.toString(twoSumInstance.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("Output: " + Arrays.toString(twoSumInstance.twoSum(new int[]{3,2,4}, 6)));
        System.out.println("Output: " + Arrays.toString(twoSumInstance.twoSum(new int[]{3,2, 3}, 6)));

        // End of compiler program execution

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> integerIntegerMap = new HashMap<>();
        for (int i = 0; i < nums.length;i++) {
            integerIntegerMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length;i++) {
            int result = target - nums[i];
            if (integerIntegerMap.containsKey(result) && integerIntegerMap.get(result) != i) {
                return new int[] { i, integerIntegerMap.get(result) };
            }
        }

        return new int[2];
    }
}