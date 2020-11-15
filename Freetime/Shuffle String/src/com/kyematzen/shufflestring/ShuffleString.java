package com.kyematzen.shufflestring;

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

public class ShuffleString {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        System.out.println("Output: " + restoreString("codeleet", new int[] {4,5,6,7,0,2,1,3}));
        System.out.println("\nOutput: " + restoreString("abc", new int[] {0,1,2}));
        System.out.println("\nOutput: " + restoreString("aiohn", new int[] {3,1,4,2,0}));
        System.out.println("\nOutput: " + restoreString("aaiougrt", new int[] {4,0,2,6,7,3,1,5}));
        System.out.println("\nOutput: " + restoreString("art", new int[] {1,0,2}));

        // End of compiler program execution
    }

    public static String restoreString(String s, int[] indices) {
        StringBuilder shuffledStr = new StringBuilder();
        char[] characters = new char[s.length()];

        for (int character = 0; character < s.length(); character++) {
            char localCharacter = s.charAt(character);

            characters[indices[character]] = localCharacter;
        }

        for (char localCharacter : characters) {
            shuffledStr.append(localCharacter);
        }

        return shuffledStr.toString();
    }
}