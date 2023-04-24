package com.javarush.task.task19.task1923;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Слова с цифрами
D:\Java_Projects\test_chars_3.txt D:\Java_Projects\test_chars_2.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileToRead = args[0];
        String fileToWrite = args[1];

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileToRead));
            FileWriter fileWriter = new FileWriter(fileToWrite)) {

            while (fileReader.ready()) {
                String line = fileReader.readLine();
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].matches(".*\\d.*")) {
                        fileWriter.write(words[i] + " ");
                    }
                }
            }
        }
    }
}
