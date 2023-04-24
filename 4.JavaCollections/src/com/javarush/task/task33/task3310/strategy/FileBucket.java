package com.javarush.task.task33.task3310.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() throws IOException {
        this.path = Files.createTempFile("tmp", null);

    }
}
