package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            InputStream inStream = new FileInputStream(scanner.nextLine());
            BufferedReader buff = new BufferedReader(new InputStreamReader(inStream));
            while (buff.ready()) {
                char content = (char) buff.read();
                System.out.print(content);
            }
            inStream.close();
            buff.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}