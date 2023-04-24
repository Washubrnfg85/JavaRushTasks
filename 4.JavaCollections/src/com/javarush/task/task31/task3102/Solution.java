package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.*;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Path path = Paths.get(root);
        Iterator iterator = new Iterator();
        Files.walkFileTree(path, iterator);
        return iterator.list;

    }

    public static void main(String[] args) throws IOException {
        List<String> result = getFileTree("D:/Java_Projects/FileJob");
        for (String file : result) {
            System.out.println(file);
        }
    }

    public static class Iterator extends SimpleFileVisitor<Path> {
        List<String> list = new ArrayList<>();
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            list.add(file.toFile().getAbsolutePath());
            return CONTINUE;
        }
    }
}
