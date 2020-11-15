package com.kyematzen.removeduplicates;

import java.util.ArrayList;
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

public class RemoveDuplicates {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println(removeDuplicates(new int[] {1,1,2})); // 0,1,2,3,4,5,6

        // End of compiler program execution
    }

    // Removes duplicates and returns size of list.
    public static int removeDuplicates(int[] nums) {

        List<Integer> integerList = new ArrayList<>();

        for (int num : nums) {
            if (!integerList.contains(num)) {
                integerList.add(num);
            }
        }
        return integerList.size();
    }
}