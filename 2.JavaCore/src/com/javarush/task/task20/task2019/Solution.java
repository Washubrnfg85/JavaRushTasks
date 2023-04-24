package com.javarush.task.task20.task2019;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Исправить ошибку. Сериализация
D:\Java_Projects\Properties.properties
*/

public class Solution implements Serializable {

    public static void main(String args[]) throws Exception {
        FileOutputStream fileOutput = new FileOutputStream("D:\\Java_Projects\\Properties.properties");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        Solution solution = new Solution();
        outputStream.writeObject(solution);

        fileOutput.close();
        outputStream.close();

        //load
        FileInputStream fiStream = new FileInputStream("D:\\Java_Projects\\Properties.properties");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Solution loadedObject = (Solution) objectStream.readObject();

        fiStream.close();
        objectStream.close();

        //Attention!!
        System.out.println(loadedObject.size());
    }

    private Map<String, String> m = new HashMap<>();

    public Map<String, String> getMap() {
        return m;
    }

    public Solution() {
        m.put("Mickey", "Mouse");
        m.put("Mickey", "Mantle");
        m.put("Mickey", "Wolf");
        m.put("Mickey", "Mantle");
        m.put("Mickey", "Wolf");
    }

    public int size() {
        return m.size();
    }
}
