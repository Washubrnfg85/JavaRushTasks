package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        File result = new File(args[0]);
        if (!result.exists()) {
            result.createNewFile();
        }

        List<String> fileNames = new ArrayList<>();
        fileNames.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(fileNames);

        List<FileInputStream> inputStreams = new ArrayList<>();
        for (String fileName : fileNames) {
            inputStreams.add(new FileInputStream(fileName));
        }

        try (ZipInputStream concatenatedStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(inputStreams)))) {
            while (true) {
                ZipEntry entry = concatenatedStream.getNextEntry();
                if (entry == null) break;

                try (OutputStream streamToFile = new BufferedOutputStream(new FileOutputStream(result))) {
                    byte[] buffer = new byte[1024];
                    for (int readBytes; (readBytes = concatenatedStream.read(buffer, 0, 1024)) > -1; ) {
                        streamToFile.write(buffer, 0, readBytes);
                    }
                    streamToFile.flush();
                }
            }
        }
    }
}
