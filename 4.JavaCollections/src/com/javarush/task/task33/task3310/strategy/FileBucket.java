package com.javarush.task.task33.task3310.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile("tmp", null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //add substitution logic
        File newFile = path.toFile();
        newFile.deleteOnExit();
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
