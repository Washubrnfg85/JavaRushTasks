package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
D:\Java_Projects\test_chars_2.txt
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = fileNameReader.readLine();
        fileNameReader.close();

        PrintStream defaultStream = System.out;
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        System.setOut(new PrintStream(array));

        testString.printSomething();
        System.setOut(defaultStream);

        try (FileOutputStream streamToFile = new FileOutputStream(fileName)) {
            streamToFile.write(array.toByteArray());
            array.writeTo(defaultStream);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

