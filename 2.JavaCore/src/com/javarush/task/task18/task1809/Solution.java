package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
C:\Users\Aleksandr Sergeev\Downloads\test_digits.txt
C:\Users\Aleksandr Sergeev\Downloads\test_digits2.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = fileNameReader.readLine();
        String fileName2 = fileNameReader.readLine();
        fileNameReader.close();

        FileInputStream fileReader = new FileInputStream(fileName1);
        FileOutputStream fileWriter = new FileOutputStream(fileName2);

        List<Integer> listOfBytes = new ArrayList<>();
        while (fileReader.available() > 0) {
            listOfBytes.add(fileReader.read());
        }

        Collections.reverse(listOfBytes);
        for (int i : listOfBytes) {
            fileWriter.write(i);
        }

        fileReader.close();
        fileWriter.close();
    }
}
