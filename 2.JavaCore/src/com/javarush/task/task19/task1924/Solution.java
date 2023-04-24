package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
D:\Java_Projects\test_chars_3.txt
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = fileNameReader.readLine();
        fileNameReader.close();

        try (BufferedReader contentReader = new BufferedReader(new FileReader(fileName))) {
            while (contentReader.ready()) {
                String line = contentReader.readLine();
                String[] words = line.split("((?<=\\,)|(?=\\,)|(?<=\\.)|(?=\\.)|\\s)");
                String newLine = "";
                for (int i = 0; i < words.length; i++) {
                    if (i < words.length - 1) {
                        try {
                            int digit = Integer.parseInt(words[i]);
                            if (map.containsKey(digit)) {
                                words[i] = map.get(digit);
                            }
                        } catch (NumberFormatException e) {

                        }
                        if (words[i + 1].equals(",") || (words[i + 1].equals("."))) {
                            newLine += words[i];
                        } else {
                            newLine += words[i] + " ";
                        }
                    } else {
                        newLine += words[i];
                    }
                }
                System.out.println(newLine);
            }
        }
    }
}
