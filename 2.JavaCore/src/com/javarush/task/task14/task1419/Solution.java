package com.javarush.task.task14.task1419;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }

        System.out.println("Number of exceptions: " + exceptions.size());

    }

    private static void initExceptions() {   //the first exception
        //Arithmetic
        try {
            float i = 1 / 0;
        } catch (Exception e) {
            exceptions.add(e);
        }

        //IndexOutOfBounds
        try {
            int[] array = new int[]{1, 2, 3};
            System.out.println(array[3]);
        } catch (Exception e) {
            exceptions.add(e);
        }

        //NullPointer
        try {
            String value = null;
            System.out.println(value.charAt(0));
        } catch (Exception e) {
            exceptions.add(e);
        }

        //FileNotFound
        try {
            String path = "C:\\test.txt";
            File file = new File(path);
            FileReader fr = new FileReader(file);
        } catch (Exception e) {
            exceptions.add(e);
        }

        //NumberFormat
        try {
            int digit = Integer.parseInt("Hello");
        } catch (Exception e) {
            exceptions.add(e);
        }

        //IO
        try {
            throw new IOException();
        } catch (Exception e) {
            exceptions.add(e);
        }

        //NoSuchElement
        try {
            Set someSet = new HashSet();
            someSet.iterator().next();
        } catch (Exception e) {
            exceptions.add(e);
        }

        //IllegalArgument
        try {
            int age = 17;
            if (age >= 18) {
                System.out.println("You can drive car");
            } else {
                throw new IllegalArgumentException("Invalid age");
            }
        } catch (Exception e) {
            exceptions.add(e);
        }

        //IllegalState
        try {
            int a = -3;
            int b = 4;
            if (a >= 0 && b >= 0) {
                int sum = a + b;
            } else {
                throw new IllegalStateException("Either one or two arguments are negative");
            }
        } catch (Exception e) {
            exceptions.add(e);
        }

        //ClassNotFound
        try {
            Class.forName("SomeClass");
        } catch (Exception e) {
            exceptions.add(e);
        }

    }
}
