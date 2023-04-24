package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
C:\Users\Aleksandr Sergeev\Downloads\test_chars_3.txt
C:\Users\Aleksandr Sergeev\Downloads\test_chars_2.txt
C:\Users\Aleksandr Sergeev\Downloads\test_chars.txt
exit
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader fileNamesReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        synchronized (resultMap) {
            while ((fileName = fileNamesReader.readLine()) != null && !fileName.equals("exit")) {
                new ReadThread(fileName).start();
            }
        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            byte[] arrayOfBytes = new byte[256];
            try (FileInputStream streamFromFile = new FileInputStream(fileName)) {
                while (streamFromFile.available() > 0) {
                    int aByte = streamFromFile.read();
                    arrayOfBytes[aByte]++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int max = 0;
            int theByte = 0;
            for (int i = 0; i < arrayOfBytes.length; i++) {
                if (arrayOfBytes[i] > max) {
                    max = arrayOfBytes[i];
                    theByte = i;
                }
            }
            resultMap.put(fileName, theByte);
        }
    }
}
