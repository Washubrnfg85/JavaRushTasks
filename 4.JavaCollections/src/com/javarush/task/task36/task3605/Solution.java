package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        TreeSet<String> set = new TreeSet<>();

        while (reader.ready()) {
            String symbol = String.valueOf((char) reader.read()).toLowerCase();
            if (symbol.matches("[a-z]")) {
                set.add(symbol);
            }
        }
        reader.close();

        List<String> comfortableList = new ArrayList<>(set);
        if (comfortableList.size() < 5) {
            for (int i = 0; i < comfortableList.size(); i++) {
                System.out.print(comfortableList.get(i));
            }
        } else {
            for (int i = 0; i < 5; i++) {
                System.out.print(comfortableList.get(i));
            }
        }
    }
}
