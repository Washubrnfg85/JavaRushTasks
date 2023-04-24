package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
D:\Java_Projects\test_chars_3.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = fileNameReader.readLine();
        fileNameReader.close();

        try (BufferedReader contentReader = new BufferedReader(new FileReader(fileName))) {
            while (contentReader.ready()) {
                String line = contentReader.readLine();
                String reversLine = new StringBuilder(line).reverse().toString();
                System.out.println(reversLine);
            }
        }
    }
}
