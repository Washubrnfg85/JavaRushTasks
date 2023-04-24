package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
C:\Users\Aleksandr Sergeev\Downloads\test - Copy.txt
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileReader = new FileInputStream(reader.readLine());

        byte[] allBytes = new byte[fileReader.available()];
        while (fileReader.available() > 0) {
            allBytes = fileReader.readAllBytes();
        }
        fileReader.close();

        TreeSet<Byte> treeSet = new TreeSet<>();
        for (int i = 0; i < allBytes.length; i++) {
            treeSet.add(allBytes[i]);
        }

        for(Byte x : treeSet) {
            System.out.print(x + " ");
        }
    }
}
