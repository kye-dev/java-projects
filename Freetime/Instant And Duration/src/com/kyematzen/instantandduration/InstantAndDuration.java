package com.kyematzen.instantandduration;

import java.time.Duration;
import java.time.Instant;
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

public class InstantAndDuration {

    // Starting position for compiler program execution
    public static void main(String[] args) throws InterruptedException {

        // Obtains current time from the system's UTC clock.
        Instant start = Instant.now();

        // Causes program to pause for 3 seconds
        TimeUnit.SECONDS.sleep(3);

        // Obtains current time from the system's UTC clock.
        Instant end = Instant.now();

        // Calculates the duration between two instant objects.
        Duration interval = Duration.between(start, end);

        // Prints interval duration between starting and ending the program.
        System.out.println("Time taken: " + interval.toSeconds() + "s");

        // End of compiler program execution
    }
}