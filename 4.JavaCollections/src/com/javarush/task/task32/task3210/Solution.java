package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String parameter1 = args[0];
        String parameter2 = args[1];
        String parameter3 = args[2];

        try {
            RandomAccessFile file = new RandomAccessFile(parameter1, "rw");
            file.seek(Long.parseLong(parameter2));

            byte[] array = new byte[parameter3.length()];
            file.read(array, 0, array.length);
            file.seek(file.length());

            if (new String(array, StandardCharsets.UTF_8).equals(parameter3)) {
                file.write("true".getBytes());
            } else {
                file.write("false".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
