package com.javarush.task.task13.task1326;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //C:\Users\Aleksandr Sergeev\Downloads\test.txt
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        FileInputStream inStream = new FileInputStream(filePath);

        byte[] buffer = new byte[inStream.available()];
        inStream.read(buffer, 0, inStream.available());
        inStream.close();

        String[] strArray = new String(buffer, StandardCharsets.UTF_8).split("\n");
        List<Integer> listToSort = new ArrayList<>();
        for(String num : strArray) {
            Integer digit = Integer.parseInt(num);
            if (digit % 2 == 0) {
                listToSort.add(digit);
            }
        }

        listToSort.stream().sorted().forEach(System.out::println);


    }
}
