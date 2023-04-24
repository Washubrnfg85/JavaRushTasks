package com.javarush.task.task20.task2022;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Переопределение сериализации в потоке
D:\Java_Projects\Properties.properties
*/

public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        //out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
        //in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "D:\\Java_Projects\\Properties.properties";
        Solution instance = new Solution(fileName);
        instance.writeObject("Some String type data");

        ByteArrayOutputStream inBox = new ByteArrayOutputStream();
        ObjectOutputStream saver = new ObjectOutputStream(inBox);
        saver.writeObject(instance);

        ByteArrayInputStream fromBox = new ByteArrayInputStream(inBox.toByteArray());
        ObjectInputStream loading = new ObjectInputStream(fromBox);
        Solution newInstance = (Solution) loading.readObject();

        newInstance.writeObject("Some other String type data");

        try (BufferedReader contentReader = new BufferedReader(new FileReader("D:\\Java_Projects\\Properties.properties"))) {
            List<String> map = new ArrayList<>();
            while (contentReader.ready()) {
                map.add(contentReader.readLine());
            }
            if (map.contains("Some String type data") && map.contains("Some other String type data")) {
                System.out.println("Serializing done well");
            }
        }
    }
}
