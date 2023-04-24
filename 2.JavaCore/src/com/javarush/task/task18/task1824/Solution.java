package com.javarush.task.task18.task1824;

import java.io.*;

/* 
Файлы и исключения
C:\Users\Aleksandr Sergeev\Downloads\test_chars_3.txt
C:\Users\Aleksandr Sergeev\Downloads\test_chars_2.txt
C:\Users\Aleksandr Sergeev\Downloads\test_chars.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;

        try {
            while (true) {
                fileName = fileNameReader.readLine();
                FileInputStream dataWithdraw = new FileInputStream(fileName);
                dataWithdraw.close();
            }
        } catch (IOException e) {
            System.out.println(fileName);
        }
    }
}
