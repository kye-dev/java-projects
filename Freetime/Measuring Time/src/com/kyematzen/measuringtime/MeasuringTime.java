package com.kyematzen.measuringtime;

import java.util.concurrent.TimeUnit;

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

public class MeasuringTime {

    // Starting position for compiler program execution
    public static void main(String[] args) throws InterruptedException {

        // Grabs current system milliseconds (long)
        long currentSystemTime = System.currentTimeMillis();

        // Causes program for 3 seconds
        TimeUnit.SECONDS.sleep(3);

        // Prints elapsed time in milliseconds by subtracting system time - currentSystemTime and diving answer by 1000 and casts to double.
        System.out.println("Time taken: " + ((double) ((System.currentTimeMillis() - currentSystemTime) / 1000)) + "ms");

        // End of compiler program execution
    }
}