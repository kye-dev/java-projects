package com.kyematzen.systemprint;

import java.util.Date;
import java.util.Locale;

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

public class SystemPrint {

    // Starting position for compiler program execution
    public static void main(String[] args) {

        // Prints to a new line with the text implemented
        System.out.println("Brand new line");

        // Prints 'This Would Space Out' in a single line which could be useful for printing out names.
        System.out.print("This ");
        System.out.print("Would ");
        System.out.print("Space ");
        System.out.print("Out");

        // Examples of printf:
        // 1. %n - Line separator
        // 2. %s - Formats strings (Capital B for uppercase formatting)
        // 3. %d - Formats decimal integers
        // 4. %f - Formats floating numbers
        // 5. %f - Formats date/time values
        // 6. %b - Formats booleans (Capital B for uppercase formatting)
        // 7. %c - Formats char (Capital B for uppercase formatting)
        // 8. %e - Scientific notation
        System.out.printf("%nExample%nPer%nLine");
        System.out.printf("%nHello %S!", "World");
        System.out.printf("%n%d", 3*5);
        System.out.printf("%n%f", 9.3);

        // Advanced Examples of printf:
        // Pushes to the left x width.
        System.out.printf("%n'%20s'", "Hello World!");

        // Pushes to the right x width.
        System.out.printf("%n'%-30s'", "Hello World!");

        // Country formatting numbers

        System.out.printf(Locale.ITALY, "%n%,d", 10000);
        System.out.printf(Locale.US, "%n%,d", 100000000);

        // Scientific Notation

        System.out.printf("%n%e", 5.1473);

        // Time
        // ‘A' – prints out the full day of the week (lowercase letter, short version)
        // ‘d' – formats a two-digit day of the month
        // ‘B' – is for the full month name
        // ‘m' – formats a two-digit month
        // ‘Y' – outputs a year in four digits
        // 'y' – outputs the last two digits of the year
        // 'Z' - Time zone
        // 'T' - 24 hour time
        // 'r' - 12 hour time

        Date localDate = new Date();

        // Time (military)

        System.out.printf("%n%tT", localDate); // Prints time

        // Precision time (military)

        System.out.printf("%n%tH hours, %tM minutes, and %tS seconds", localDate, localDate, localDate);

        // Month, followed by day number and year.

        System.out.printf("%n%tB %td, %tY", localDate, localDate, localDate);

        // End of compiler program execution
    }
}