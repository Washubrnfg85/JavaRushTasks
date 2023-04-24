package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
D:\Java_Projects\Properties.properties
*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        Properties fileProp = new Properties();
        for (Map.Entry<String, String> each : runtimeStorage.entrySet()) {
            fileProp.setProperty(each.getKey(), each.getValue());
        }
        fileProp.store(outputStream, "some comments");
    }

    public static void load(InputStream inputStream) throws IOException {
        Properties fileProp = new Properties();
        fileProp.load(inputStream);
        runtimeStorage = (Map) fileProp;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fos = new FileInputStream(reader.readLine());
             FileOutputStream fis = new FileOutputStream(reader.readLine())) {
            load(fos);
            save(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(runtimeStorage);
    }
}
