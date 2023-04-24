package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
D:\Java_Projects\test_chars_3.txt
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("file");
        words.add("view");
        words.add("B");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = fileNameReader.readLine();
        fileNameReader.close();

        try (BufferedReader contentReader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            while (contentReader.ready()) {
                lines.add(contentReader.readLine());
            }

            for (String line : lines) {
                int counter = 0;
                String[] wordsFromLine = line.split(" ");
                for (int i = 0; i < wordsFromLine.length; i++) {
                    for (int j = 0; j < words.size(); j++) {
                        if (wordsFromLine[i].equals(words.get(j))) {
                            counter++;
                        }
                    }
                }
                if (counter == 2) {
                    System.out.println(line);
                }
            }
        }
    }
}
