package com.kyematzen.runningsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

// Problem: https://leetcode.com/problems/running-sum-of-1d-array/
public class RunningSum {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println(Arrays.toString(runningSum(new int[]{3,1,2,10,1})));

        // End of compiler program execution
    }

    public static int[] runningSum(int[] nums) {
        int[] cache = new int[nums.length];

        cache[0] = nums[0];

        for (int i = 1; i < nums.length;i++) {
            int cached = nums[i];

            for (int numCache : cache) {
                nums[i] += numCache;
            }

            cache[i] = cached;
        }

        return nums;
    }
}