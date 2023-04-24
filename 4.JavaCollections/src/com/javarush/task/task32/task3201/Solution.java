package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
"D:/Java_Projects/FileJob/test_chars_3.txt" "15" "is now expanded"
*/

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile file = new RandomAccessFile(args[0], "rw");
        long fileLength = file.length();

        if (Long.parseLong(args[1]) < fileLength) {
            file.seek(Long.parseLong(args[1]));
        } else {
            file.seek(fileLength);
        }

        file.write(args[2].getBytes());
        file.close();
    }
}
