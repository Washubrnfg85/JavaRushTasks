package com.javarush.task.task13.task1319;

import java.io.*;
import java.util.Scanner;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //C:\Users\Aleksandr Sergeev\Downloads\test.txt
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filePath));

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (!str.equals("exit")) {
                buffWriter.write(str + "\n");
            } else {
                buffWriter.write("exit");
                scanner.close();
                buffWriter.close();
                break;
            }
        }
    }
}
