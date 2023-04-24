package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
"D:/Java_Projects/FileJob/test_digits.txt" "D:/Java_Projects/FileJob/archive.zip"
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        List<Content> entries = getContents(args[1]);

        FileOutputStream zipFile = new FileOutputStream(args[1]);
        ZipOutputStream zip = new ZipOutputStream(zipFile);

        //кладем в него  ZipEntry –«архивный объект»
        File file = new File(args[0]);
        zip.putNextEntry(new ZipEntry("new/" + file.getName()));

        //копируем файл «document-for-archive.txt» в архив под именем «document.txt»
        Files.copy(file.toPath(), zip);

        //копируем все остальные файлы
        for (Content content : entries) {
            if (!content.getFileName().equals("new/" + file.getName())) content.saveToZip(zip);
        }

        // закрываем архив
        zip.close();
    }

    private static List<Content> getContents(String arg) throws IOException {
        List<Content> entries = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(arg))) {
            ZipEntry currentEntry;
            byte[] buffer = new byte[1024];
            while ((currentEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int length = 0;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                entries.add(new Content(currentEntry.getName(), outputStream.toByteArray()));
            }
        }
        return entries;
    }

    static class Content {
        String fileName;
        byte[] body;

        Content(String fileName, byte[] body) {
            this.fileName = fileName;
            this.body = body;
        }

        public String getFileName() {
            return fileName;
        }

        void saveToZip(ZipOutputStream zip) throws IOException {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zip.putNextEntry(zipEntry);
            zip.write(body);
            zip.closeEntry();
        }
    }
}


/*
public class Solution {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        String[] partsOfPath = filePath.split("/");
        String fileName = partsOfPath[partsOfPath.length - 1];
        String archivePath = args[1];

        Map<ZipEntry, byte[]> files = readOutFilesContent(archivePath, fileName);

        try (ZipOutputStream archiveWriter = new ZipOutputStream(new FileOutputStream(archivePath))) {
            for (Map.Entry<ZipEntry, byte[]> each : files.entrySet()) {
                archiveWriter.putNextEntry(each.getKey());
                archiveWriter.write(each.getValue());
            }
            archiveWriter.putNextEntry(new ZipEntry("new/" + fileName));
            Files.copy(new File(filePath).toPath(), archiveWriter);
        }
    }

    public static Map<ZipEntry, byte[]> readOutFilesContent (String archivePath, String fileName) throws IOException {
        Map<ZipEntry, byte[]> map = new HashMap<>();
        try (ZipInputStream archiveReader = new ZipInputStream(new FileInputStream(archivePath))) {
            ZipEntry file;
            while ((file = archiveReader.getNextEntry()) != null) {
                if (!file.getName().equals(fileName)) {
                    byte[] buffer = new byte[2048];
                    int entryLength = archiveReader.read(buffer);
                    map.put(file, buffer);
                }

            }
        }

        return map;
    }
}
*/