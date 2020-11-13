package com.kyematzen.variables;

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
public class Variables {

    // Three types of variables in Java: Local, Instance, and Static

    /**
     * 1. Local Variables
     *
     * - Defined within a block or method or constructor is considered this.
     * - Created when called and destroyed after the block, and killed (destroyed) after excited from block or when a function returns from a call.
     * - Scope of variables only exist within the block where the variable is declared, meaning it is only accessible within that block.
     * - Initialization of the variable is mandatory.
     *
     */

    public void getPetAge() {
        // 1. local variable pet age
        int petAge = 1;

        petAge += 5;

        System.out.println("The pet's age is: " + petAge); // The pet's age is: 6

        // If the local variable petAge was defined inside of another method and we attempted to call it.
        // It'd throw a Compilation Error that it is unable to find symbol: petAge.
    }

    /**
     * 2. Instance Variables
     *
     * - A instance are declared in the class and are created when an object of that class is created, and it is destroyed when the object is destroyed.
     * - Unlike a local variable, the initialization of the variable is not mandatory and the default value is 0
     * - Accessed only by creating a object
     * - Changes made using only one object will not get reflected in other objects due to individual copies of exampleInstance, and anotherInstanceExample.
     * (definition of Object: Has a state and behavior, where the states are stored in variables, while functions display the behavior)
     */

    class InstanceVariables {
        int exampleInstance;
        int anotherInstanceExample;
    }

    /**
     * 3. Static Variables
     * - Declared similarly to a instance variable, but the difference is a keyword know as static
     * - Static variables are created at the program execution and destroyed automatically when the program execution ends.
     * - Similar to a instance variable, the initialization of the variable is not mandatory and the default value is 0
     */

    public static double kyeSalary = 50000.00D;

    // Starting position for compiler program execution
    public static void main(String[] args) {

        // Prints salary
        System.out.println("Kye's Salary: " + kyeSalary);

        // A static variable can be accessed outside of local methods, and can be accessed from another class using:
        // className.variableName;
        // Variables.kyeSalary - Would return 50000.00

        // End of compiler program execution
    }
}