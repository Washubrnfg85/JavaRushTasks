package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
D:\Java_Projects\test_chars_2.txt
D:\Java_Projects\test_chars_3.txt
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = fileNameReader.readLine();
        String fileName2 = fileNameReader.readLine();
        fileNameReader.close();

        List<String> firstFile = new ArrayList<>();
        List<String> secondFile = new ArrayList<>();
        try (BufferedReader fileReader1 = new BufferedReader(new FileReader(fileName1));
            BufferedReader fileReader2 = new BufferedReader(new FileReader(fileName2))) {
            while (fileReader1.ready()) {
                firstFile.add(fileReader1.readLine());
            }
            while (fileReader2.ready()) {
                secondFile.add(fileReader2.readLine());
            }
        }

        int stringCounter1 = 0, stringCounter2 = 0;
        while (stringCounter1 < firstFile.size() && stringCounter2 < secondFile.size()) {
            if (firstFile.get(stringCounter1).equals(secondFile.get(stringCounter2))) {
                lines.add(new LineItem(Type.SAME, firstFile.get(stringCounter1)));
                stringCounter1++;
                stringCounter2++;
            } else if (firstFile.get(stringCounter1) != secondFile.get(stringCounter2) &&
                       firstFile.get(stringCounter1 + 1).equals(secondFile.get(stringCounter2))) {
                lines.add(new LineItem(Type.REMOVED, firstFile.get(stringCounter1)));
                stringCounter1++;
            } else if (firstFile.get(stringCounter1) != secondFile.get(stringCounter2) &&
                    firstFile.get(stringCounter1).equals(secondFile.get(stringCounter2 + 1))) {
                lines.add(new LineItem(Type.ADDED, secondFile.get(stringCounter2)));
                stringCounter2++;
            }
        }

        while (stringCounter1 < firstFile.size()) {
            lines.add(new LineItem(Type.REMOVED, firstFile.get(stringCounter1)));
            stringCounter1++;
        }
        
        while (stringCounter2 < secondFile.size()) {
            lines.add(new LineItem(Type.ADDED, secondFile.get(stringCounter2)));
            stringCounter2++;
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        public String toString() {
            return String.valueOf(type) + " " + String.valueOf(line);
        }
    }
}
