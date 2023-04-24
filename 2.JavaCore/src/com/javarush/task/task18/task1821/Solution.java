package com.javarush.task.task18.task1821;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (FileReader reader = new FileReader(args[0])) {
            int[] array = new int[256];
            while (reader.ready()) {
                int current = reader.read();
                array[current]++;
            }

            TreeMap<Character, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < array.length; i++) {
                if (array[i] > 0) {
                    treeMap.put((char) i, array[i]);
                }
            }

            treeMap.forEach((key, value) -> System.out.println(key + " " + value));
        }
    }
}
