package com.javarush.task.task16.task1630;

import java.io.*;
import java.util.ArrayList;

/* 
Последовательный вывод файлов
C:\\Users\\Aleksandr Sergeev\\Downloads\\test.txt
C:\\Users\\Aleksandr Sergeev\\Downloads\\test - Copy.txt
*/

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException, IOException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface extends Runnable {

        void setFileName(String fullFileName);

        String getFileContent() throws IOException;

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        String fullPath;
        ArrayList<String> content = new ArrayList<>();
        @Override
        public void setFileName(String fullFileName) {
            fullPath = fullFileName;
        }

        @Override
        public String getFileContent() throws IOException {
            String result = "";
            for(String str : content) {
                result += str + " ";
            }
            return result;
        }

        @Override
        public void run() {
            try {
                FileInputStream inputStream = new FileInputStream(fullPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while (reader.ready()) {
                    content.add(reader.readLine());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
