package com.javarush.task.task33.task3310.strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile("tmp", null);
            File newFile = path.toFile();
            if (newFile.isFile()) {
                FileWriter fileWriter = new FileWriter(newFile, false);
            } else {

            }
            newFile.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //add substitution logic

    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putEntry(Entry entry) {

    }

    public Entry getEntry() {
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
