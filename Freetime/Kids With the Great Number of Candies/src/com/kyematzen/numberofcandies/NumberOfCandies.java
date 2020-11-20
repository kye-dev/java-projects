package com.kyematzen.numberofcandies;

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

public class NumberOfCandies {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println("Output: " + kidsWithCandies(new int[] {2,3,5,1,3}, 3).toString());
        System.out.println("Output: " + kidsWithCandies(new int[] {4,2,1,1,2}, 1).toString());

        // End of compiler program execution
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> booleanList = new ArrayList<>();
        int highestCandy = 0;

        for (int candy : candies) {
            if (candy > highestCandy) {
                highestCandy = candy;
            }
        }

        for (int candy : candies) {
            booleanList.add(candy + extraCandies >= highestCandy);
        }

        return booleanList;
    }
}