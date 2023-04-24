package com.javarush.task.task18.task1807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Подсчет запятых
C:\Users\Aleksandr Sergeev\Downloads\test - Copy.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileReader = new FileInputStream(reader.readLine());

        int count = 0;
        while (fileReader.available() > 0) {
            int symbol = fileReader.read();
            if (symbol == 44) count++;
        }

        fileReader.close();
        System.out.println(count);
    }
}
