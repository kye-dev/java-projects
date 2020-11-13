package com.kyematzen.variablescopes;

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
public class VariableScopes {

    /**
     * Class Level Scope (Member Variables)
     * Must be declared inside the class but outside any methods where they can be accessed anywhere in the class.
     */

    int exampleInt;
    private String exampleString;
    char exampleChar;

    /**
     * Access Modifiers
     *
     * Default - Used when not mentioning any access modifier, the scope modifier is limited to the package only.
     * More detail: Only the classes in the package 'variablescopes' can access this class.
     * Fun fact: Generally, if used on a  method and another class attempts to access it,
     * a error will be thrown because the method from the type className is not visible
     *
     * Private -The scope modifier is limited to the class only.
     * More detail: Private data members and methods are only accessible from within this class no where else.
     * Fun facts: Class and Interface cannot be declared as private,
     * and if a class's constructor is private then you cannot create the object of that class specifically from outside of the class.
     *
     * Protected - Only accessible by the classes of the same package and the sub classes that are in any package.
     * Similar to default access modifier with one exception having the visibility in sub classes.
     * More detail: Classes cannot be declared this.
     * Fun fact: Generally, it is only used for in a parent child relationship.
     *
     * Public - Can be accessed anywhere for members, methods, and classes. No restrictions unlike other modifiers.
     *
     * ------------+-------+---------+--------------+--------------+--------
     *             | Class | Package | Subclass     | Subclass     |Outside|
     *             |       |         |(same package)|(diff package)|Class  |
     * ————————————+———————+—————————+——————————----+—————————----—+————————
     * public      | Yes   |  Yes    |    Yes       |    Yes       |   Yes |
     * ————————————+———————+—————————+—————————----—+—————————----—+————————
     * protected   | Yes   |  Yes    |    Yes       |    Yes       |   No  |
     * ————————————+———————+—————————+————————----——+————————----——+————————
     * default     | Yes   |  Yes    |    Yes       |    No        |   No  |
     * ————————————+———————+—————————+————————----——+————————----——+————————
     * private     | Yes   |  No     |    No        |    No        |   No  |
     * ------------+-------+---------+--------------+--------------+--------
     */

    // Starting position for compiler program execution
    public static void main(String[] args) {

        // End of compiler program execution
    }
}