package com.kyematzen.jewelsandstones;

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

public class JewelsAndStones {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println("Output: " + numJewelsInStones("aA", "aAAbbbb"));
        System.out.println("Output: " + numJewelsInStones("z", "ZZ"));

        // End of compiler program execution
    }

    public static int numJewelsInStones(String J, String S) {
        char[] jewsArray = new char[J.length()];
        Map<Character, Integer> stoneMap = new HashMap<>();

        for (int i = 0; i < J.length(); i++) {
            jewsArray[i] = J.charAt(i);
        }

        for (int i = 0; i < S.length();i++) {
            char localStone = S.charAt(i);
            boolean isJewel = false;

            for (Character localJewel : jewsArray) {
                if (localJewel.compareTo(localStone) == 0) {
                    isJewel = true;
                    break;
                }
            }

            if (isJewel) {
                if (stoneMap.containsKey(localStone)) {
                    stoneMap.put(localStone, stoneMap.get(localStone) + 1);
                    continue;
                }
                stoneMap.put(localStone, 1);
            }
        }

        int jewels = 0;

        for (int jewelCount : stoneMap.values()) {
            jewels += jewelCount;
        }

        return jewels;
    }
}