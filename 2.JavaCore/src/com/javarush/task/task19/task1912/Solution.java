package com.javarush.task.task19.task1912;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Ридер обертка 2
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultStream = System.out;

        ByteArrayOutputStream arrayToWriteIn = new ByteArrayOutputStream();
        PrintStream newStream = new PrintStream(arrayToWriteIn);
        System.setOut(newStream);
        testString.printSomething();
        System.setOut(defaultStream);

        String result = arrayToWriteIn.toString().replaceAll("te", "??");
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
