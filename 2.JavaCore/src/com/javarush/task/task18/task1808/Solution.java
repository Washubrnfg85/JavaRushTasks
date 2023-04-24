package com.javarush.task.task18.task1808;

import java.io.*;
import java.util.Arrays;

/* 
Разделение файла
C:\Users\Aleksandr Sergeev\Downloads\test_chars.txt
C:\Users\Aleksandr Sergeev\Downloads\test_chars_2.txt
C:\Users\Aleksandr Sergeev\Downloads\test_chars_3.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String sourceFile = fileNameReader.readLine();
        String fileName2 = fileNameReader.readLine();
        String fileName3 = fileNameReader.readLine();
        fileNameReader.close();

        FileInputStream readingStream = new FileInputStream(sourceFile);
        FileOutputStream writingStream1 = new FileOutputStream(fileName2);
        FileOutputStream writingStream2 = new FileOutputStream(fileName3);

        byte[] allBytes = new byte[readingStream.available()];
        for (int i = 0; i < allBytes.length; i++) {
            allBytes[i] = (byte) readingStream.read();
        }
        readingStream.close();

        int middleByte = allBytes.length % 2 == 0 ? allBytes.length / 2 : (allBytes.length / 2) + 1;

        byte[] firstHalf = Arrays.copyOf(allBytes, middleByte);
        byte[] secondHalf = Arrays.copyOfRange(allBytes, middleByte, allBytes.length);

        writingStream1.write(firstHalf);
        writingStream2.write(secondHalf);
        writingStream1.close();
        writingStream2.close();

    }
}
