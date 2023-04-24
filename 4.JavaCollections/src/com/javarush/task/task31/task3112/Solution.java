package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://pacemook.com/photos/image1.jpg",
                Paths.get("D:/Java_Projects/FileJob"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        String[] urlParts = urlString.split("/");
        Path target = Paths.get(downloadDirectory.toString() + "/" + urlParts[urlParts.length - 1]);
        try {
            URL url = new URL(urlString);
            InputStream inputStream = url.openStream();

            Path tempFile = Files.createTempFile("temp-", ".txt");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            Files.createFile(target);
            Files.move(tempFile, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Couldn't make it");
        }
        return target;
    }
}
