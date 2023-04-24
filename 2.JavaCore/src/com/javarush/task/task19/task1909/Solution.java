package com.javarush.task.task19.task1909;

import java.io.*;
import java.util.ArrayList;

/* 
Замена знаков
D:\Java_Projects\test_chars.txt
D:\Java_Projects\test_chars_2.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = fileNameReader.readLine();
        String fileName2 = fileNameReader.readLine();
        fileNameReader.close();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName2, true))) {
            String wholeText = "";
            while (fileReader.ready()) {
                wholeText += fileReader.readLine();
            }
            String[] items = wholeText.split("\\.");

            for (int i = 0; i < items.length; i++) {
                if (i == items.length - 1) {
                    fileWriter.write(items[i]);
                } else {
                    fileWriter.write(items[i] + "!");
                }
            }
        }
    }
}
