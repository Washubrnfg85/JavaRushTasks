package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;

/* 
Выделяем числа
D:\Java_Projects\test_digits2.txt
D:\Java_Projects\test_digits.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = fileNameReader.readLine();
        String fileName2 = fileNameReader.readLine();
        fileNameReader.close();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName2, true))) {
            String toParse = fileReader.readLine();
            String[] items = toParse.split(" ");

            ArrayList<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < items.length; i++) {
                try {
                    resultList.add(Integer.valueOf(items[i]));
                } catch (Exception e) {}
            }
            for (Integer x : resultList) {
                fileWriter.write(x + " ");
            }
        }
    }
}
