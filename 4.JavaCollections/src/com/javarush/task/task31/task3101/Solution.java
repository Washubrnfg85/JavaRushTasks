package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

/* 
Проход по дереву файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        File dest = new File(args[1]);
        File allFilesContent = new File(dest.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(dest, allFilesContent);

        List<String> filesToWrite = getContent(path);
        try (FileOutputStream writeToFile = new FileOutputStream(allFilesContent)) {
            for (String file : filesToWrite) {
                FileInputStream readFile = new FileInputStream(file);
                while (readFile.available() > 0) {
                    writeToFile.write(readFile.read());
                }
                writeToFile.write('\n');
            }
        }
    }

    public static List<String> getContent(Path file) throws IOException {
        Iterator iterator = new Iterator();
        Files.walkFileTree(file, iterator);
        return iterator.suitableFiles;
    }

    public static class Iterator extends SimpleFileVisitor<Path> {
        List<String> suitableFiles = new ArrayList<>();
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toFile().length() <= 50) {
                suitableFiles.add(file.toFile().getAbsolutePath());
            }
            return CONTINUE;
        }
    }
}
