package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultStream = System.out;

        ByteArrayOutputStream arrayForContent = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(arrayForContent);

        System.setOut(new PrintStream(newStream));
        testString.printSomething();
        System.setOut(defaultStream);

        String[] partsOfExpression = arrayForContent.toString().split(" ");

        int result = 0;
        switch (partsOfExpression[1]) {
            case "+" :
                result = Integer.parseInt(partsOfExpression[0]) + Integer.parseInt(partsOfExpression[2]);
                break;
            case "-" :
                result = Integer.parseInt(partsOfExpression[0]) - Integer.parseInt(partsOfExpression[2]);
                break;
            case "*" :
                result = Integer.parseInt(partsOfExpression[0]) * Integer.parseInt(partsOfExpression[2]);
                break;
        }

        System.out.printf("%s %s %s %s %d",partsOfExpression[0], partsOfExpression[1], partsOfExpression[2], partsOfExpression[3], result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

