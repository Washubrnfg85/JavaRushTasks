package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile("tmp", null);
            path.toFile().deleteOnExit();

            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putEntry(Entry entry) {
        try {
            ObjectOutputStream objectWriter = new ObjectOutputStream(Files.newOutputStream(path));
            objectWriter.writeObject(entry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() > 0) {
            try {
                ObjectInputStream objectReader = new ObjectInputStream(Files.newInputStream(path));
                return (Entry) objectReader.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
