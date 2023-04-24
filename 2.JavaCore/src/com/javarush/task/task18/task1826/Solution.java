package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inStream = new FileInputStream(args[1]);
        FileOutputStream outStream = new FileOutputStream(args[2]);

        switch (args[0]) {
            case "-e" :
                byte[] fileToEcrypt = new byte[inStream.available()];
                byte[] encryptedFile = new byte[fileToEcrypt.length];
                while (inStream.available() > 0) {
                    for (int i = 0; i < fileToEcrypt.length; i++) {
                        fileToEcrypt[i] = (byte) inStream.read();
                    }
                }
                for (int i = 0; i < fileToEcrypt.length; i++) {
                    byte x = (byte) (fileToEcrypt[i] + 1);
                    encryptedFile[i] = x;
                }
                outStream.write(encryptedFile);
                inStream.close();
                outStream.close();
                break;
            case "-d" :
                byte[] fileToDecrypt = new byte[inStream.available()];
                byte[] decryptedFile = new byte[fileToDecrypt.length];
                while (inStream.available() > 0) {
                    for (int i = 0; i < fileToDecrypt.length; i++) {
                        fileToDecrypt[i] = (byte) inStream.read();
                    }
                }
                for (int i = 0; i < fileToDecrypt.length; i++) {
                    byte x = (byte) (fileToDecrypt[i] - 1);
                    decryptedFile[i] = x;
                }
                outStream.write(decryptedFile);
                inStream.close();
                outStream.close();
                break;
        }
    }

}
