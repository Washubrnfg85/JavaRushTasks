package com.javarush.task.task19.task1902;

import java.io.FileOutputStream;
import java.io.IOException;

/* 
Адаптер
*/

public class AdapterFileOutputStream implements AmigoStringWriter {
    private FileOutputStream fileOutputStream;

    public AdapterFileOutputStream(FileOutputStream outputStream) {
        this.fileOutputStream = outputStream;

    }

    public static void main(String[] args) {

    }

    @Override
    public void flush() throws IOException {
        fileOutputStream.flush();
    }

    @Override
    public void writeString(String s) throws IOException {
        byte[] array = s.getBytes();
        fileOutputStream.write(array);
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.close();

    }
}

