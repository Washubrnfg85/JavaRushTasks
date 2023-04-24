package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
D:/Java_Projects
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader folderReader = new BufferedReader(new InputStreamReader(System.in));
        Path folder = Paths.get(folderReader.readLine());

        if (!Files.isDirectory(folder)) {
            System.out.println(folder.toAbsolutePath() + " - не папка");
            //throw new IllegalArgumentException();
        }

        FileWalker fileWalker = new FileWalker();
        Files.walkFileTree(folder, fileWalker);

        System.out.println("Всего папок - " + (fileWalker.numberOfFolders.decrementAndGet()));
        System.out.println("Всего файлов - " + fileWalker.numberOfFiles);
        System.out.println("Общий размер - " + fileWalker.totalSize);
    }

    public static class FileWalker extends SimpleFileVisitor<Path> {
        AtomicInteger numberOfFolders = new AtomicInteger();
        AtomicInteger numberOfFiles = new AtomicInteger();
        AtomicLong totalSize = new AtomicLong();
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            //if (dir.toFile().isDirectory()) {
                numberOfFolders.incrementAndGet();
                totalSize.addAndGet(attrs.size());
            //}

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            //if (file.toFile().isFile()) {
                numberOfFiles.incrementAndGet();
                totalSize.addAndGet(attrs.size());
            //}
            return FileVisitResult.CONTINUE;
        }
    }
}
