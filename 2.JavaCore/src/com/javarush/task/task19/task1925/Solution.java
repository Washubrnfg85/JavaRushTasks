package com.javarush.task.task19.task1925;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* 
Длинные слова
D:\Java_Projects\test_chars_3.txt D:\Java_Projects\test_chars_2.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileToRead = args[0];
        String fileToWrite = args[1];

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileToRead));
            FileWriter fileWriter = new FileWriter(fileToWrite)) {
            String bluePrint = "";
            while (fileReader.ready()) {
                String[] words = fileReader.readLine().split(" ");
                for (String word : words) {
                    if (word.length() > 6) {
                        bluePrint += word + ",";
                    }
                }
            }
            String toWrite = bluePrint.substring(0, bluePrint.length() - 1);
            fileWriter.write(toWrite);
        }
    }
}
