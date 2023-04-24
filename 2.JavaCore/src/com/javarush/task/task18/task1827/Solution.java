package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Прайсы
C:\Users\Aleksandr Sergeev\Downloads\test_chars_3.txt
*/

public class Solution {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            return;
        }

        if (args[0].equals("-c")) {
            BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
            String filePath = fileNameReader.readLine();
            FileReader streamFromFile = new FileReader(filePath);
            fileNameReader.close();

            String fullProductName = String.format("%-" + 30 + "s", args[1]);
            String price = String.format("%-" + 8 + "s", args[2]);
            String quantity = String.format("%-" + 4 + "s", args[3]);
            String id = String.format("%-" + 8 + "s", generateId(streamFromFile));
            streamFromFile.close();

            String fullStringToAdd = "\n" + id + fullProductName + price + quantity;
            System.out.println(fullStringToAdd);

            FileWriter streamToFile = new FileWriter(filePath, true);
            streamToFile.append(fullStringToAdd);
            streamToFile.flush();
            streamToFile.close();
        }
    }

    public static String generateId(FileReader streamFromFile) throws IOException {
        BufferedReader stringsReader = new BufferedReader(streamFromFile);
        List<Integer> stringsInFile = new ArrayList<>();
        while (stringsReader.ready()) {
            stringsInFile.add(Integer.parseInt(stringsReader.readLine().substring(0, 8).trim()));
        }
        return String.valueOf(Collections.max(stringsInFile) + 1);
    }
}
