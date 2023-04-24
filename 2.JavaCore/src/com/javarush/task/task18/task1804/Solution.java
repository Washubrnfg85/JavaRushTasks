package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые редкие байты
C:\Users\Aleksandr Sergeev\Downloads\test - Copy.txt
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileReader = new FileInputStream(reader.readLine());

        int[] arrayOfBytes = new int[256];
        try {
            while (fileReader.available() > 0) {
                arrayOfBytes[fileReader.read()] += 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileReader.close();

        int minCount = Integer.MAX_VALUE;
        for(int item : arrayOfBytes) {
            if(item > 0 && item < minCount) minCount = item;
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < arrayOfBytes.length; i++) {
            if (arrayOfBytes[i] == minCount) resultList.add(i);
        }

        for (Integer item : resultList) {
            System.out.print(item + " ");
        }
    }
}
